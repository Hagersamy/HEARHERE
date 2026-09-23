package com.example.hearhere.screen

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.hearhere.R
import com.example.hearhere.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private var mainBinding:ActivityMainBinding ? = null
    private var navHostFragment : NavHostFragment ? = null
    private var navController : NavController ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        bottomNavHidde()
    }

    override fun onResume() {
        super.onResume()
        navController!!.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.loginFragment || destination.id == R.id.registerFragment) {
                mainBinding!!.bottomNavigation!!.visibility = View.GONE
            } else {
                mainBinding!!.bottomNavigation!!.visibility = View.VISIBLE
                val menuItem = mainBinding!!.bottomNavigation.menu.findItem(R.id.homeFragment)
                // Change the icon color
                menuItem.icon?.setColorFilter(ContextCompat.getColor(this, R.color.iqrealy), PorterDuff.Mode.SRC_IN)

            }
        }
    }
    private fun bottomNavHidde(){

        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment!!.navController

        //bottomNavigationView = findViewById(R.id.bottom_navigation)
        mainBinding!!.bottomNavigation!!.setupWithNavController(navController!!)


        //  to hidde the bottomNavigationView when start the app
        mainBinding!!.bottomNavigation!!.visibility = View.GONE

        //       BottomNavigationView to see if is visible or not
        navController!!.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.loginFragment || destination.id == R.id.registerFragment) {
                mainBinding!!.bottomNavigation!!.visibility = View.GONE
            } else {
                mainBinding!!.bottomNavigation!!.visibility = View.VISIBLE

            }
        }
    }
}
