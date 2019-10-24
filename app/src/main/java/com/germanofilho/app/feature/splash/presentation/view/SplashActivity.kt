package com.germanofilho.app.feature.splash.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.germanofilho.app.core.extension.fadeAnimation
import com.germanofilho.app.feature.home.presentation.view.activity.HomeActivity
import com.germanofilho.chamaapp.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    companion object{
        const val SPLASH_DURATION = 2000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        showFadeAnimation()
        scheduleSplashScreen()
    }

    private fun showFadeAnimation(){
        img_logo.fadeAnimation(800)
        txt_splash.fadeAnimation(800)
    }


    private fun scheduleSplashScreen() {
        Handler().postDelayed(
            {
                startActivity(Intent(this, HomeActivity::class.java))
                finishAffinity()
            }, SPLASH_DURATION
        )
    }
}
