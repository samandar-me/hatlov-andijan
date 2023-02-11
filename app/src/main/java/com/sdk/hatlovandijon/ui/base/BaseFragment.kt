package com.sdk.hatlovandijon.ui.base

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.sdk.hatlovandijon.R
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.util.*

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
    fun imageFile(uri: Uri): String {
        val date = UUID.randomUUID().toString()
        val contentResolver = requireContext().contentResolver?.openInputStream(uri)
        val file = File(requireContext().filesDir, "$date.jpg")
        val fileOutputStream = FileOutputStream(file)
        contentResolver?.copyTo(fileOutputStream)
        contentResolver?.close()
        fileOutputStream.close()
        return file.absolutePath
    }

    fun showSettingsDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.permission_req))
            .setMessage(getString(R.string.per_desc))
            .setPositiveButton(getText(R.string.open_set)) { dialog, _ ->
                dialog.dismiss()
                openSettings()
            }
            .setCancelable(false)
            .show()
    }
    private fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", requireActivity().packageName, null)
        intent.data = uri
        startActivityForResult(intent, 100)
    }
    fun View.setBackColor(@ColorRes color: Int) {
        this.setBackgroundColor(ContextCompat.getColor(requireContext(), color))
    }
}