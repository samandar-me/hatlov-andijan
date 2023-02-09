package com.sdk.hatlovandijon.ui.auth

import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.method.PasswordTransformationMethod
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
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
            etLogin.requestFocus()
            etLogin.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    textLogin.setTextColor(
                        ContextCompat.getColor(this@LoginActivity, R.color.blue)
                    )
                    etLogin.setTextColor(
                        ContextCompat.getColor(this@LoginActivity, R.color.blue)
                    )
                } else {
                    textLogin.setTextColor(
                        ContextCompat.getColor(this@LoginActivity, R.color.gray)
                    )
                    etLogin.setTextColor(
                        ContextCompat.getColor(this@LoginActivity, R.color.gray)
                    )
                }
            }
            etPassword.apply {
                transformationMethod = PasswordTransformationMethod()
                setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) {
                        textPassword.setTextColor(
                            ContextCompat.getColor(this@LoginActivity, R.color.blue)
                        )
                        etPassword.setTextColor(
                            ContextCompat.getColor(this@LoginActivity, R.color.blue)
                        )
                        etLayPass.setPasswordVisibilityToggleTintList(
                            ColorStateList.valueOf(
                                ContextCompat.getColor(this@LoginActivity, R.color.blue)
                            )
                        )
                    } else {
                        textPassword.setTextColor(
                            ContextCompat.getColor(this@LoginActivity, R.color.gray)
                        )
                        etPassword.setTextColor(
                            ContextCompat.getColor(this@LoginActivity, R.color.gray)
                        )
                        etLayPass.setPasswordVisibilityToggleTintList(
                            ColorStateList.valueOf(
                                ContextCompat.getColor(this@LoginActivity, R.color.gray)
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
                    }, 2000)
                }
            }
        }
    }
}