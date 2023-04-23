package com.sdk.hatlovandijon.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.sdk.hatlovandijon.R
import com.sdk.hatlovandijon.ui.no_internet.NoInternetActivity

class CheckInternetManager : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (!hasInternet(context)) {
            context?.startActivity(Intent(context, NoInternetActivity::class.java))
        }
    }

    private fun hasInternet(context: Context?): Boolean {
        val networkHelper = NetworkHelper(context!!)
        return networkHelper.isNetworkConnected()
    }
}