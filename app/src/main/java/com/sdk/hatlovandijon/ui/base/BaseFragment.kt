package com.sdk.hatlovandijon.ui.base

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.sdk.hatlovandijon.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment(
    private val layoutId: Int
) : Fragment(layoutId) {
    fun View.click(action: (View) -> Unit) {
        this.setOnClickListener {
            action(it)
        }
    }

    fun toast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    fun snack(text: String) {
        Snackbar.make(requireView(), text, Snackbar.LENGTH_SHORT).show()
    }

    fun TextInputEditText.sutUpInput(title: TextView) {
        this.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                title.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.blue)
                )
                this.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.blue)
                )
            } else {
                title.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.gray)
                )
                this.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.gray)
                )
            }
        }
    }
    fun TextView.textColor(@ColorRes color: Int) {
        this.setTextColor(ContextCompat.getColor(requireContext(), color))
    }
}