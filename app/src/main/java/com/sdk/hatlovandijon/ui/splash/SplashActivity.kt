package com.sdk.hatlovandijon.ui.splash

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.sdk.hatlovandijon.R
import com.sdk.hatlovandijon.ui.auth.LoginActivity
import com.sdk.hatlovandijon.ui.base.BaseActivity
import com.sdk.hatlovandijon.ui.main.MainActivity
import com.sdk.hatlovandijon.ui.no_internet.NoInternetActivity
import com.sdk.hatlovandijon.util.CheckInternetManager
import com.sdk.hatlovandijon.util.Constants.TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        val checkInternetManager = CheckInternetManager()
        val intent = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        registerReceiver(checkInternetManager, intent)

        lifecycleScope.launchWhenResumed {
            viewModel.state.collect {
                when (it) {
                    is SplashState.Idle -> Unit
                    is SplashState.UserAuthed -> {
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        finish()
                    }
                    is SplashState.UserNotAuthed -> {
                        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                        finish()
                    }
                }
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        viewModel.checkForUser()
    }
}