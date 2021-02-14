package com.ictech.mcvideo.activity

import android.app.Activity
import android.content.Intent
import com.ictech.mcvideo.sharedpref.AppPref
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.ictech.mcvideo.R

class SplashActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this, MainActivity::class.java))
        finish()
        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        if (!AppPref.isAppIntroShown) {
            if (!resources.getBoolean(R.bool.enable_mandatory_authentication)) {
                // Authentication is mandatory
                if (FirebaseAuth.getInstance().currentUser == null) {
                    // Intro shown and user authenticated
                    AppIntroActivity.startActivity(this)
                    finish()
                } else {
                    // Intro shown but user unauthenticated
                    AuthenticationActivity.startActivity(this)
                    finish()
                }
            } else {
                // Authentication is optional. Intro shown but user unauthenticated
                MainActivity.startActivity(this)
                finish()
            }
        } else {
            MainActivity.startActivity(this)
            finish()
        }
    }
}