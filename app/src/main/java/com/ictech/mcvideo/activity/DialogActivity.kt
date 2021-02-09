package com.ictech.mcvideo.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.view.animation.DecelerateInterpolator
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.ColorUtils
import com.google.android.material.snackbar.Snackbar
import com.ictech.mcvideo.R
import com.ictech.mcvideo.databinding.NameDialogBinding
import kotlinx.android.synthetic.main.name_dialog.*



class DialogActivity : AppCompatActivity(){
    private var darkStatusBar = false
    private lateinit var binding: NameDialogBinding  // ViewBinding
    private var etName: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)
        binding = NameDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val bundle = intent.extras
//        etNickname = bundle?.getString("name", "") ?: ""
        etName = binding.etNickname

//        etName = findViewById(R.id.etNickname)
        // Set the Status bar appearance for different API levels
        if (Build.VERSION.SDK_INT in 19..20) {
            setWindowFlag(this, true)
        }
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        if (Build.VERSION.SDK_INT >= 21) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // If you want dark status bar, set darkStatusBar to true
                if (darkStatusBar) {
                    this.window.decorView.systemUiVisibility =
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
                this.window.statusBarColor = Color.TRANSPARENT
                setWindowFlag(this, false)
            }
        }

//        backgroundFadeAnim()
        // Fade animation for the background of Dialog Window
        val alpha = 1 //between 0-255
        val alphaColor = ColorUtils.setAlphaComponent(Color.parseColor("#86828282"), alpha)
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), Color.TRANSPARENT, alphaColor)
        colorAnimation.duration = 500 // milliseconds
        colorAnimation.addUpdateListener { animator ->
            dialogBackground.setBackgroundColor(animator.animatedValue as Int)
        }
        colorAnimation.start()

        btnSubmit.setOnClickListener {
            if(etName!!.text.toString().isEmpty()) {
                setSnackBar()
            } else {
                onBackPressed()
//                Toast.makeText(applicationContext, "Done", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(MainActivity.NAME, etName!!.text.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }

        }
            /*val etInput = etName.text.toString()
            if (etInput < 1.toString()) {
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(MainActivity.NAME, etInput)
                onBackPressed()
            }

            if (etNickname.text.toString().isEmpty()) {
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(MainActivity.NAME, etNickname.text.toString())
                onBackPressed()

            }*/
    }

    override fun onBackPressed() {
        // Fade animation for the background of Popup Window when you press the back button
        val alpha = 100 // between 0-255
        val alphaColor = ColorUtils.setAlphaComponent(Color.parseColor("#000000"), alpha)
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), alphaColor, Color.TRANSPARENT)
        colorAnimation.duration = 500 // milliseconds
        colorAnimation.addUpdateListener { animator ->
            dialogBackground.setBackgroundColor(
                animator.animatedValue as Int
            )
        }

        // Fade animation for the Popup Window when you press the back button
        popUpWindowBorder.animate().alpha(0f).setDuration(500).setInterpolator(
            DecelerateInterpolator()
        ).start()

        // After animation finish, close the Activity
        colorAnimation.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                finish()
                overridePendingTransition(0, 0)
            }
        })
        colorAnimation.start()
    }

    private fun setWindowFlag(activity: Activity, on: Boolean) {
        val win = activity.window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        } else {
            winParams.flags = winParams.flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS.inv()
        }
        win.attributes = winParams
    }

    private fun setSnackBar(){
        val mSnackbar: Snackbar = Snackbar.make(
                binding.dialogBackground,
                R.string.enter_name_error,
                Snackbar.LENGTH_SHORT)
        val mTextView = mSnackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        val view: View = mSnackbar.view
        mTextView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        mTextView.setTextColor(Color.BLACK)
        view.setBackgroundColor(Color.GRAY)
        mSnackbar.show()
    }

    /*private fun backgroundFadeAnim(){
        // Fade animation for the background of Dialog Window
        val alpha = 100 //between 0-255
        val alphaColor = ColorUtils.setAlphaComponent(Color.parseColor("#000000"), alpha)
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), Color.TRANSPARENT, alphaColor)
        colorAnimation.duration = 500 // milliseconds
        colorAnimation.addUpdateListener { animator ->
            binding.dialogBackground.setBackgroundColor(animator.animatedValue as Int)
        }
        colorAnimation.start()
    }*/
}
