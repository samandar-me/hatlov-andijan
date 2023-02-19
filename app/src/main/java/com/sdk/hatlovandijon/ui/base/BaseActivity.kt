package com.sdk.hatlovandijon.ui.base

import android.os.Bundle
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.sdk.hatlovandijon.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {
    fun snack(text: String, isSuccess: Boolean) {
        val snackBar =
            Snackbar.make(findViewById(android.R.id.content), text, Snackbar.LENGTH_SHORT)
        val view = snackBar.view
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        params.setMargins(40, 80, 40, 40)
        view.layoutParams = params
        val back = if (isSuccess) R.drawable.snack_success else R.drawable.snack_error
        val textColor = if (isSuccess) R.color.success_text_color else R.color.error_text_color
        view.background = ContextCompat.getDrawable(this, back)
        snackBar.setTextColor(ContextCompat.getColor(this, textColor))
        val textView = view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.textSize = 17f
        snackBar.animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE
        snackBar.show()
    }
}