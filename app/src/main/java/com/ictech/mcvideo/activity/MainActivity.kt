package com.ictech.mcvideo.activity

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.app.Dialog
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
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.graphics.ColorUtils
import androidx.core.widget.doOnTextChanged
import coil.api.load
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.callbacks.onDismiss
import com.afollestad.materialdialogs.customview.customView
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.dialog_profile.*
import kotlinx.android.synthetic.main.name_dialog.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val viewModel by viewModel<MainViewModel>() // Lazy inject ViewModel
    private lateinit var binding: ActivityMainBinding

    private val minMeetingCodeLength = 10
    private var currentUser: FirebaseUser? = null
    private lateinit var createMeetingInterstitialAd: InterstitialAd
    private lateinit var joinMeetingInterstitialAd: InterstitialAd

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
        onCopyMeetingCodeFromClipboardClick()
        onShareMeetingCodeClick()

        onJoinMeetingClick()
        onCreateMeetingClick()
        onMeetingHistoryClick()
        onProfileClick()
    }

    /*private fun setUserNickname(){
        val dialog = Dialog(this, R.style.AppTheme)

        dialog.setContentView(R.layout.name_dialog)
        dialog.btnSubmit.setOnClickListener (View.OnClickListener{
            if (etNickname.text.toString().isEmpty()){
                Toast.makeText(applicationContext,
                    "Please enter a name",
                    Toast.LENGTH_SHORT
                ).show()

            }else{
                Toast.makeText(applicationContext, "Name submitted", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
//                joinMeeting(getJoinMeetingCode())
            }
        })
        dialog.show()
    }*/

    private fun openDialogActivity(){
        val intent = Intent(this, DialogActivity::class.java)
        intent.putExtra("name","")
        startActivity(intent)
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
//                openDialogActivity()
//                setUserNickname()
//                joinMeeting(getJoinMeetingCode())
                loadJoinMeetingInterstitialAd()
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
        binding.tilCodeCreateMeeting.etCodeCreateMeeting.doOnTextChanged { text, _, _, _ ->
            if (text.toString().trim()
                    .replace(" ", "").length >= minMeetingCodeLength
            ) binding.tilCodeCreateMeeting.error = null
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
    private fun onCopyMeetingCodeFromClipboardClick() {
        binding.ivClipboard.setOnClickListener {
            val clipboardText = getTextFromClipboard()
            if (clipboardText != null) {
                binding.tilCodeJoinMeeting.setText(clipboardText)
                toast(getString(R.string.main_meeting_code_copied))
            } else {
                toast(getString(R.string.main_empty_clipboard))
            }
        }
    }

    /**
     * Called when the share icon is clicked in the EditText of the CREATE MEETING toggle
     */
    private fun onShareMeetingCodeClick() {
        binding.tilCodeCreateMeeting.setEndIconOnClickListener {

            if (isMeetingCodeValid(getCreateMeetingCode())) {
                binding.tilCodeCreateMeeting.error = null
                startShareTextIntent(
                    getString(R.string.main_share_meeting_code_title),
                    binding.etCodeCreateMeeting.text.toString() // Don't remove spaces here for improved readability of meeting code
                )
            } else {
                binding.tilCodeCreateMeeting.error =
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
                        joinMeeting(getJoinMeetingCode())
                } else {

                    joinMeeting(getJoinMeetingCode())
                }
            }
        }
    }

    private fun joinMeeting(meetingCode: String) {
        MeetingUtils.startMeeting(
            this,
            meetingCode,
            R.string.all_joining_meeting
        ) // Start Meeting

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
            R.string.all_creating_meeting
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
            Snackbar.make(
                binding.constrainLayout,
                getString(R.string.main_error_meeting_code_length, minMeetingCodeLength),
                Snackbar.LENGTH_SHORT
            ).show()
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
                    val openURL = Intent(android.content.Intent.ACTION_VIEW)
                    openURL.data = Uri.parse("https://help.mommas.uk/")
                    startActivity(openURL)
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
