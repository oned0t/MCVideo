package com.ictech.mcvideo.activity

import android.app.Activity
import com.core.extensions.*
import com.ictech.mcvideo.MCVideo
import com.ictech.mcvideo.R
import com.ictech.mcvideo.databinding.ActivityMainBinding
import com.ictech.mcvideo.model.Meeting
import com.ictech.mcvideo.sharedpref.AppPref
import com.ictech.mcvideo.utils.MeetingUtils
import com.ictech.mcvideo.viewmodel.MainViewModel
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.widget.doOnTextChanged
//import coil.api.load
import coil.load
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.callbacks.onDismiss
import com.afollestad.materialdialogs.customview.customView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
        const val DIALOG_ACTIVITY_REQUEST_CODE = 1
        const val NAME: String = "name"
    }

    private val viewModel by viewModel<MainViewModel>() // Lazy inject ViewModel
    private lateinit var binding: ActivityMainBinding

    private val minMeetingCodeLength = 10
    private var currentUser: FirebaseUser? = null
    private lateinit var createMeetingInterstitialAd: InterstitialAd
    private lateinit var joinMeetingInterstitialAd: InterstitialAd
    private var etName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentUser = FirebaseAuth.getInstance().currentUser
        setProfileIcon()

        // Load ads based on configuration
        if (MCVideo.isAdEnabled) {
            initializeCreateMeetingInterstitialAd()
            loadCreateMeetingInterstitialAd()
            initializeJoinMeetingInterstitialAd()
            loadJoinMeetingInterstitialAd()
        }

        onMeetingToggleChange()
        onCreateMeetingCodeChange()
        onShareMeetingCodeClick()
        onJoinMeetingClick()
        onCreateMeetingClick()
        onMeetingHistoryClick()
        onProfileClick()


//            onCopyMeetingCodeFromClipboardClick(copyText)


    }

    private fun openDialogActivity(){
        val intent = Intent(this, DialogActivity::class.java)
        startActivityForResult(intent, DIALOG_ACTIVITY_REQUEST_CODE)
    }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            if (resultCode == RESULT_OK) {
                if (data != null) {
                    etName = data.getStringExtra(NAME)
//                    Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
                    joinMeeting(getJoinMeetingCode())
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
//                Log.e("Res Cancelled", " Cancelled")
//                name = data!!.getStringExtra(NAME)
                Toast.makeText(this, "Join meeting failed", Toast.LENGTH_SHORT).show()
            }
//            joinMeeting(getJoinMeetingCode())
        }


    private fun setProfileIcon() {
        currentUser?.let {
            if (it.photoUrl != null) {
                binding.ivProfile.load(currentUser?.photoUrl) {
                    placeholder(R.drawable.ic_profile)
                }
            }
        }
    }

    private fun initializeCreateMeetingInterstitialAd() {
        createMeetingInterstitialAd = InterstitialAd(this)
        createMeetingInterstitialAd.adUnitId = getString(R.string.interstitial_ad_id_create_meeting)

        // Reload ad once shown
        createMeetingInterstitialAd.adListener = object : AdListener() {
            override fun onAdClosed() {
                createMeeting(getCreateMeetingCode())
                loadCreateMeetingInterstitialAd()
            }
        }
    }

    private fun loadCreateMeetingInterstitialAd() {
        createMeetingInterstitialAd.loadAd(AdRequest.Builder().build())
    }

    private fun initializeJoinMeetingInterstitialAd() {
        joinMeetingInterstitialAd = InterstitialAd(this)
        joinMeetingInterstitialAd.adUnitId = getString(R.string.interstitial_ad_id_join_meeting)

        // Reload ad once shown
        joinMeetingInterstitialAd.adListener = object : AdListener() {
            override fun onAdClosed() {
//                setUserNickname()
                joinMeeting(getJoinMeetingCode())
                loadJoinMeetingInterstitialAd()
                openDialogActivity()
            }
        }
    }

    private fun loadJoinMeetingInterstitialAd() {
        joinMeetingInterstitialAd.loadAd(AdRequest.Builder().build())
    }

    /*
     * Called when the meeting toggle button check state is changed
     */
    private fun onMeetingToggleChange() {
        binding.tgMeeting.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.btnToggleJoinMeeting   -> {
                        binding.groupCreateMeeting.makeGone()
                        binding.groupJoinMeeting.makeVisible()
                    }
                    R.id.btnToggleCreateMeeting -> {
                        binding.groupJoinMeeting.makeGone()
                        binding.groupCreateMeeting.makeVisible()
                        val meetingCode = generateMeetingCode()
                        binding.etCodeCreateMeeting.setText(meetingCode)
                    }
                }
            }
        }
    }

    /**
     * Called when the meeting code in the EditText of the CREATE MEETING toggle changes
     */
    private fun onCreateMeetingCodeChange() {
        binding.etCodeCreateMeeting.doOnTextChanged { text, _, _, _ ->
            if (text.toString().trim()
                    .replace(" ", "").length >= minMeetingCodeLength
            ) binding.etCodeCreateMeeting.error = null
        }
    }

    private fun generateMeetingCode(): String {
        val allowedChars = ('a'..'z') + ('0'..'9')
        return (1..10)
            .map { allowedChars.random() }
            .joinToString("")
    }

    /**
     * Called when the clipboard icon is clicked in the EditText of the JOIN MEETING toggle
     */
    /*private fun onCopyMeetingCodeFromClipboardClick(text: CharSequence) {
        val clipboardManager: ClipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData: ClipData = ClipData.newPlainText("text", text)
        clipboardManager.setPrimaryClip(clipData)
        if (clipboardManager != null){
            clipboardManager.setPrimaryClip(clipData)
            toast(getString(R.string.main_meeting_code_copied))
        }else {
            toast(getString(R.string.main_empty_clipboard))
        }
    }*/

    /**
     * Called when the share icon is clicked in the EditText of the CREATE MEETING toggle
     */
    private fun onShareMeetingCodeClick() {
        binding.etCodeCreateMeeting.setOnClickListener {

            if (isMeetingCodeValid(getCreateMeetingCode())) {
                binding.etCodeCreateMeeting.error = null
                startShareTextIntent(
                    getString(R.string.main_share_meeting_code_title),
                    binding.etCodeCreateMeeting.text.toString() // Don't remove spaces here for improved readability of meeting code
                )
            } else {
                binding.etCodeCreateMeeting.error =
                    getString(R.string.main_error_meeting_code_length, minMeetingCodeLength)
            }
        }
    }

    /**
     * Called when the JOIN button is clicked of the JOIN MEETING toggle
     */
    private fun onJoinMeetingClick() {
        binding.btnJoinMeeting.setOnClickListener {
            if (isMeetingCodeValid(getJoinMeetingCode())) {

                if (MCVideo.isAdEnabled) {
                    if (joinMeetingInterstitialAd.isLoaded) {
                        joinMeetingInterstitialAd.show()
                    }else

                        openDialogActivity()
//                        joinMeeting(getJoinMeetingCode())
//                        setUserNickname()
                } else {

                    openDialogActivity()
                    joinMeeting(getJoinMeetingCode())
                }
            }
        }
    }

    private fun joinMeeting(meetingCode: String) {


        MeetingUtils.startMeeting(
                this,
                meetingCode,
                R.string.all_joining_meeting,
                etName
            )
         // Start Meeting

        viewModel.addMeetingToDb(
            Meeting(
                meetingCode,
                System.currentTimeMillis()
            )
        ) // Add meeting to db
    }

    /**
     * Returns the meeting code for joining the meeting
     */
    private fun getJoinMeetingCode() =
        binding.etCodeJoinMeeting.text.toString().trim().replace(" ", "")

    /**
     * Called when the CREATE button is clicked of the CREATE MEETING toggle
     */
    private fun onCreateMeetingClick() {
        binding.btnCreateMeeting.setOnClickListener {
            if (isMeetingCodeValid(getCreateMeetingCode())) {
                if (MCVideo.isAdEnabled) {
                    if (createMeetingInterstitialAd.isLoaded) createMeetingInterstitialAd.show() else createMeeting(
                        getCreateMeetingCode()
                    )
                } else {
                    createMeeting(getCreateMeetingCode())
                }
            }
        }
    }

    private fun createMeeting(meetingCode: String) {
        MeetingUtils.startMeeting(
            this,
            meetingCode,
            R.string.all_creating_meeting,
            name = null
        ) // Start Meeting

        viewModel.addMeetingToDb(
            Meeting(
                meetingCode,
                System.currentTimeMillis()
            )
        ) // Add meeting to db
    }

    private fun getCreateMeetingCode() =
        binding.etCodeCreateMeeting.text.toString().trim().replace(" ", "")

    private fun isMeetingCodeValid(meetingCode: String): Boolean {
        return if (meetingCode.length >= minMeetingCodeLength) {
            true
        } else {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
            etCodeJoinMeeting.error = getString(R.string.main_error_meeting_code_length)
            false
        }
    }

    private fun onMeetingHistoryClick() {
        binding.ivMeetingHistory.setOnClickListener {
            MeetingHistoryActivity.startActivity(this)
        }
    }

    private fun onProfileClick() {
        binding.ivProfile.setOnClickListener {
            val profileDialog = MaterialDialog(this, BottomSheet(LayoutMode.WRAP_CONTENT)).show {
                customView(R.layout.dialog_profile)
            }

            profileDialog.apply {
                /*if (currentUser != null) {
                    currentUser!!.photoUrl?.let {
                        ivUserProfileDialog.load(it) {
                            placeholder(R.drawable.ic_profile)
                        }
                    }

                    *//*tvUserName.text = currentUser!!.displayName
                    tvEmail.text = currentUser!!.email
                    btnUserAuthenticationStatus.text = getString(R.string.all_btn_sign_out)*//*

                } else {
                    tvUserName.makeGone()
                    tvEmail.makeGone()
                    tvUserNotAuthenticated.makeVisible()
                    customView(R.layout.dialog_profile)
                    btnUserAuthenticationStatus.text = getString(R.string.all_btn_sign_in)
                }*/

                switchDarkMode.isChecked = !AppPref.isLightThemeEnabled


                // UserAuthenticationStatus button onClick
                /*btnUserAuthenticationStatus.setOnClickListener {
                    dismiss()

                    if (currentUser != null) {
                        // User is currently signed in
                        AuthUI.getInstance().signOut(this@MainActivity).addOnCompleteListener {
                            AuthenticationActivity.startActivity(this@MainActivity)
                            finish()
                        }
                    } else {
                        // User is not signed in
                        AuthenticationActivity.startActivity(this@MainActivity)
                        finish()
                    }
                }*/

                // Dark Mode Switch
                switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
                    dismiss()

                    // Change theme after dismiss to prevent memory leak
                    onDismiss {
                        if (isChecked) setThemeMode(AppCompatDelegate.MODE_NIGHT_YES) else setThemeMode(
                            AppCompatDelegate.MODE_NIGHT_NO
                        )
                    }
                }

                // Send feedback onClick
                tvSendFeedback.setOnClickListener {


                    startEmailIntent(
                        getString(R.string.app_feedback_contact_email),
                        getString(R.string.profile_feedback_email_subject)
                    )
                }

                // Rate app onClick
                tvRateApp.setOnClickListener {
                    openAppInGooglePlay(applicationContext.packageName, R.color.colorSurface)
                }

                // Share app onClick
                tvShareApp.setOnClickListener {
                    startShareTextIntent(
                        getString(R.string.profile_share_app_title),
                        getString(R.string.profile_share_app_text, applicationContext.packageName)
                    )
                }

                tvFaqs.setOnClickListener{
                    openUrl("https://help.mommas.uk/", R.color.colorSurface)
                }

                // Open Source Licenses onClick
                tvOpenSourceLicenses.setOnClickListener {
                    startActivity(Intent(this@MainActivity, OssLicensesMenuActivity::class.java))
                }

                // Privacy Policy onClick
                tvPrivacyPolicy.setOnClickListener {
                    openUrl(getString(R.string.app_privacy_policy_url), R.color.colorSurface)
                }

                // Terms of Service onClick
                tvTermsOfService.setOnClickListener {
                    openUrl(getString(R.string.app_terms_of_service_url), R.color.colorSurface)
                }
            }
        }
    }

    private fun setThemeMode(themeMode: Int) {
        AppCompatDelegate.setDefaultNightMode(themeMode)
        AppPref.isLightThemeEnabled = themeMode == AppCompatDelegate.MODE_NIGHT_NO
    }

    fun disable(view: View) {
        view.isEnabled(false);
    }
}

private fun TextInputLayout.setText(clipboardText: CharSequence) {

}

private fun View.isEnabled(b: Boolean) {

}
