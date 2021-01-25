package com.ictech.mcvideo.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.ColorUtils
import com.ictech.mcvideo.databinding.NameDialogBinding

class DialogActivity : AppCompatActivity() {
    private var etNickname = ""
    private lateinit var binding: NameDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0,0)
        binding = NameDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        etNickname = bundle?.getString("name", "") ?: ""
        etNickname = binding.etNickname.text.toString()
        backgroundFadeAnim()

        binding.btnSubmit.setOnClickListener {
            onBackPressed()
        }
    }

    private fun backgroundFadeAnim(){
        // Fade animation for the background of Dialog Window
        val alpha = 100 //between 0-255
        val alphaColor = ColorUtils.setAlphaComponent(Color.parseColor("#000000"), alpha)
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), Color.TRANSPARENT, alphaColor)
        colorAnimation.duration = 500 // milliseconds
        colorAnimation.addUpdateListener { animator ->
            binding.dialogBackground.setBackgroundColor(animator.animatedValue as Int)
        }
        colorAnimation.start()

        // Fade animation for the Popup Window
        binding.popUpWindowBorder.alpha = 0f
        binding.popUpWindowBorder.animate().alpha(1f).setDuration(500).setInterpolator(
                DecelerateInterpolator()
        ).start()
    }

    override fun onBackPressed() {
        // Fade animation for the background of Popup Window when you press the back button
        val alpha = 100 // between 0-255
        val alphaColor = ColorUtils.setAlphaComponent(Color.parseColor("#000000"), alpha)
        val colorAnimation =
                ValueAnimator.ofObject(ArgbEvaluator(), alphaColor, Color.TRANSPARENT)
        colorAnimation.duration = 500 // milliseconds
        colorAnimation.addUpdateListener { animator ->
            binding.dialogBackground.setBackgroundColor(
                    animator.animatedValue as Int
            )
        }
        // Fade animation for the Popup Window when you press the back button
        binding.popUpWindowBorder.animate().alpha(0f).setDuration(500).setInterpolator(
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

}