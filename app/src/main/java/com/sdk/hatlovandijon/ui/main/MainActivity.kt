package com.sdk.hatlovandijon.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.sdk.hatlovandijon.R
import com.sdk.hatlovandijon.databinding.ActivityMainBinding
import com.sdk.hatlovandijon.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val appUpdateManager by lazy { AppUpdateManagerFactory.create(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.fragment)
        binding.navView.setupWithNavController(navController)

        checkForUpdate()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.detailFragment -> {
                    changeBottomNavVisible(false)
                }
                R.id.problemFragment -> {
                    changeBottomNavVisible(false)
                }
                R.id.editAppealFragment -> {
                    changeBottomNavVisible(false)
                }
                R.id.addEditAppealFragment -> {
                    changeBottomNavVisible(false)
                }
                else -> {
                    changeBottomNavVisible(true)
                }
            }
        }
    }

    private fun changeBottomNavVisible(isVisible: Boolean) {
        binding.navView.isVisible = isVisible
    }

    private fun checkedMenu(index: Int) {
        binding.navView.menu[index].isChecked = true
    }

    override fun onResume() {
        super.onResume()
        appUpdateManager.registerListener {
            if (it.installStatus() == InstallStatus.DOWNLOADED) {
                showSnackBar()
            }
        }
    }

    private fun checkForUpdate() {
        appUpdateManager.registerListener {
            if (it.installStatus() == InstallStatus.DOWNLOADED) {
                showSnackBar()
            }
        }
        appUpdateManager.appUpdateInfo.addOnSuccessListener {
            if (it.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && it.isUpdateTypeAllowed(
                    AppUpdateType.FLEXIBLE
                )
            ) {
                appUpdateManager.startUpdateFlowForResult(it, AppUpdateType.IMMEDIATE, this, 100)
            }
        }
    }

    private fun showSnackBar() {
        Snackbar.make(
            binding.root,
            getString(R.string.update_downloaded),
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction(getString(R.string.install)) {
                appUpdateManager.completeUpdate()
            }.show()
    }
}