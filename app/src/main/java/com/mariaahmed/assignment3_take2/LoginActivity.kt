package com.mariaahmed.assignment3_take2

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginbtn = findViewById<Button>(R.id.login)
        loginbtn.setOnClickListener {
            val intent = Intent(this, NavigationActivity::class.java)
            startActivity(intent)
        }

   }

    fun signup(view: android.view.View) {
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }
    fun forgotpass(view: android.view.View) {
        val intent = Intent(this, ForgotPassActivity::class.java)
        startActivity(intent)
    }
}