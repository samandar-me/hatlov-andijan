package com.sdk.hatlovandijon.ui.auth

import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.method.PasswordTransformationMethod
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.sdk.domain.model.LoginData
import com.sdk.hatlovandijon.R
import com.sdk.hatlovandijon.databinding.ActivityLoginBinding
import com.sdk.hatlovandijon.databinding.ActivityMainBinding
import com.sdk.hatlovandijon.ui.base.BaseActivity
import com.sdk.hatlovandijon.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginActivity : BaseActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        binding.apply {
            etLogin.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    textLogin.setTextColor(
                        getCl(R.color.blue)
                    )
                    etLogin.setTextColor(
                        getCl(R.color.blue)
                    )
                } else {
                    textLogin.setTextColor(
                        getCl(R.color.gray)
                    )
                    etLogin.setTextColor(
                        getCl(R.color.gray)
                    )
                }
            }
            etPassword.apply {
                transformationMethod = PasswordTransformationMethod()
                setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) {
                        textPassword.setTextColor(
                            getCl(R.color.blue)
                        )
                        etPassword.setTextColor(
                            getCl(R.color.blue)
                        )
                        etLayPass.setPasswordVisibilityToggleTintList(
                            ColorStateList.valueOf(
                                getCl(R.color.blue)
                            )
                        )
                    } else {
                        textPassword.setTextColor(
                            getCl(R.color.gray)
                        )
                        etPassword.setTextColor(
                            getCl(R.color.gray)
                        )
                        etLayPass.setPasswordVisibilityToggleTintList(
                            ColorStateList.valueOf(
                                getCl(R.color.gray)
                            )
                        )
                    }
                }
                btnLogin.setOnClickListener {
                    login()
                }
            }
        }
        observeState()
    }

    private fun observeState() {
        lifecycleScope.launch {
            viewModel.state.collectLatest {
                when(it) {
                    is LoginActivityState.Idle -> Unit
                    is LoginActivityState.Loading -> {
                        binding.pr.isVisible = true
                        binding.btnLogin.isVisible = false
                    }
                    is LoginActivityState.Error -> {
                        binding.pr.isVisible = false
                        binding.btnLogin.isVisible = true
                        snack(getString(R.string.error_message), false)
                    }
                    is LoginActivityState.Success -> {
                        binding.pr.isVisible = false
                        binding.btnLogin.isVisible = true
                        snack(getString(R.string.success), true)
                        delay(500L)
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    }
                }
            }
        }
    }

    private fun login() {
        binding.apply {
            val username = binding.etLogin.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            viewModel.onEvent(LoginActivityEvent.OnLoginClicked(LoginData(username,password)))
        }
    }

    private fun getCl(@ColorRes color: Int): Int {
        return ContextCompat.getColor(this, color)
    }
}