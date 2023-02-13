package com.sdk.hatlovandijon.ui.auth

import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.method.PasswordTransformationMethod
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.sdk.hatlovandijon.R
import com.sdk.hatlovandijon.databinding.ActivityLoginBinding
import com.sdk.hatlovandijon.databinding.ActivityMainBinding
import com.sdk.hatlovandijon.ui.main.MainActivity

class LoginActivity : AppCompatActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
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
                    pr.isVisible = true
                    btnLogin.isVisible = false
                    Handler(mainLooper).postDelayed({
                        pr.isVisible = false
                        btnLogin.isVisible = true
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    }, 2000)
                }
            }
        }
    }
    private fun getCl(@ColorRes color: Int): Int {
        return ContextCompat.getColor(this, color)
    }
}