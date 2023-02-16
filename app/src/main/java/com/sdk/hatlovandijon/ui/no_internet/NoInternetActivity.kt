package com.sdk.hatlovandijon.ui.no_internet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sdk.hatlovandijon.R
import com.sdk.hatlovandijon.ui.base.BaseActivity

class NoInternetActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_internet)
        snack(getString(R.string.no_internet), false)
    }
}