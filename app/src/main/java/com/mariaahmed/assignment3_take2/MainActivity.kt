package com.mariaahmed.assignment3_take2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    // Splash screen duration in milliseconds
    private val SPLASH_DELAY: Long = 5000 // 3 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Delayed navigation to HomeActivity
        Handler().postDelayed({
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        }, SPLASH_DELAY)
    }
}
