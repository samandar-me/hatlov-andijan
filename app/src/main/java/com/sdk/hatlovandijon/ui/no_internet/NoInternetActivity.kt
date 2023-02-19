package com.sdk.hatlovandijon.ui.no_internet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import com.sdk.hatlovandijon.R
import com.sdk.hatlovandijon.ui.base.BaseActivity
import com.sdk.hatlovandijon.util.NetworkHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NoInternetActivity : BaseActivity() {

    @Inject
    lateinit var networkHelper: NetworkHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_no_internet)
        snack(getString(R.string.no_internet), false)

        findViewById<MaterialButton>(R.id.btnCheck).setOnClickListener {
            if (networkHelper.isNetworkConnected()) {
                finish()
            } else {
                snack(getString(R.string.no_internet), false)
            }
        }
    }
}