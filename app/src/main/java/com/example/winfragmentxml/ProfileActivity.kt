package com.example.winfragmentxml

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // get the passed argument
        val name = intent.getStringExtra("name") ?: ""
        val nameTv = findViewById<TextView>(R.id.name_tv)
        // set the argument
        nameTv.text = name


        // receive argument from Privacy fragment
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.privacyMessage.collect { message ->
                    if (message.isNotEmpty()) {
                        nameTv.text = message
                    }
                }
            }
        }

        // get the access to navController
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.profile_container) as NavHostFragment
        val navController = navHostFragment.navController

        // Pass the argument to startDestination Fragment
        val bundle = AccountsFragmentArgs(name = name).toBundle()
        navController.setGraph(R.navigation.profile_graph, bundle)

        // Pass the argument to sibling Fragment
        val privacyButton = findViewById<Button>(R.id.privacy_button)
        privacyButton.setOnClickListener {
            val privacyBundle = PrivacySettingsFragmentArgs(name = name).toBundle()
            navController.navigate(R.id.privacySettingsFragment, privacyBundle)
        }
    }
}