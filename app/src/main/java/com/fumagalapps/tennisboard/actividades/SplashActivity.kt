package com.fumagalapps.tennisboard.actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.fumagalapps.tennisboard.MainActivity
import com.fumagalapps.tennisboard.R
@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {
    // This is the loading time of the splash screen
    private val SPLASH_TIME_OUT:Long = 3000 // 2 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity
            startActivity(Intent(this, MainActivity::class.java))
            // close this activity
            finish()
        }, SPLASH_TIME_OUT)

    }
}