package com.ictech.mcvideo.activity

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
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
    }

}