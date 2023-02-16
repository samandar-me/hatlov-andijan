package com.sdk.hatlovandijon.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.sdk.data.manager.SharedPrefManager
import com.sdk.hatlovandijon.R
import com.sdk.hatlovandijon.ui.auth.LoginActivity
import com.sdk.hatlovandijon.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var sharedPrefManager: SharedPrefManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        Handler(mainLooper).postDelayed({
            if (sharedPrefManager.getToken() != null) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish()
            }
        }, 2000L)
    }
}