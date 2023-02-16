package com.sdk.hatlovandijon.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.forEach
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.sdk.hatlovandijon.R
import com.sdk.hatlovandijon.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.fragment)
        binding.navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.detailFragment -> {
                    changeBottomNavVisible(false)
                }
                R.id.problemFragment -> {
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
}