package com.deep.techbank.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.fragment.NavHostFragment
import com.deep.techbank.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Step 1: Add the custom navigator.
        navController.navigatorProvider.addNavigator(ComposeNavigator())

        // Step 2: Set the graph programmatically.
        navController.setGraph(R.navigation.root_nav_graph)
    }
}
