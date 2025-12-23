package com.example.winfragmentxml

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.winfragmentxml.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupWindowInsets()
        setupNavigation()
    }

    private fun setupWindowInsets() {
        // 1. Handle the Top (Toolbar)
        ViewCompat.setOnApplyWindowInsetsListener(binding.toolbar) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updatePadding(top = systemBars.top)
            insets
        }

        // 2. Handle the Bottom (Bottom Navigation)
        ViewCompat.setOnApplyWindowInsetsListener(binding.bottomNavContainer) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updatePadding(bottom = systemBars.bottom)
            insets
        }
    }

    private fun setupNavigation() {
        // 1. Get NavController via supportFragmentManager
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        navController = navHostFragment.navController

        // 2. Configure App Bar (Top-level destinations show the Hamburger menu)
        //    Add other top-level fragments here binding.drawerLayout
        appBarConfiguration = AppBarConfiguration(setOf(R.id.chatFragment), binding.drawerLayout)

        // 3. Get and set Toolbar
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)

        // 4. Connect BottomNav and Drawer
        binding.bottomNavContainer.setupWithNavController(navController)
        binding.drawerNavContainer.setupWithNavController(navController)

        // 5. Optimized Visibility Listener
        navController.addOnDestinationChangedListener { _, destination, _ ->
            handleBottomNavVisibility(destination.id)
        }
    }

    private fun handleBottomNavVisibility(destinationId: Int) {
        // Define screens where BottomNav should be HIDDEN
        val hideBottomNavOnDestinations = setOf(
            R.id.setting1Fragment,
            R.id.setting2Fragment
        )

        val shouldShow = destinationId !in hideBottomNavOnDestinations
        binding.bottomNavContainer.isVisible = shouldShow
    }

    override fun onSupportNavigateUp(): Boolean {
        // Handles the hamburger menu click or back arrow
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}