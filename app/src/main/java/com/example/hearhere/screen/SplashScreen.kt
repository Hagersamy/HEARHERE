package com.example.hearhere.screen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewTreeObserver
import com.example.hearhere.R

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    private val splashDuration: Long = 2000 // Duration in milliseconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        handleOnPreDraw()
    }

    // Set up an OnPreDrawListener to the root view.
    private fun handleOnPreDraw() {
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    // Remove the listener and start the splash screen delay
                    content.viewTreeObserver.removeOnPreDrawListener(this)

                    // Start the MainActivity
                    val intent = Intent(this@SplashScreen, MainActivity::class.java)
                    startActivity(intent)
                    // Finish SplashScreen activity so it doesn't return to it
                    finish()
                    return true
                }
            }
        )
    }

    private fun startSplashScreen() {
        // Handler to start MainActivity after splash screen duration
        Handler(Looper.getMainLooper()).postDelayed({
            // Start the MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            // Finish SplashScreen activity so it doesn't return to it
            finish()
        }, splashDuration)
    }
}

/*window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        },3000)*/