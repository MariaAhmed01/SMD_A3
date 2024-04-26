package com.mariaahmed.assignment3_take2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import android.os.AsyncTask

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginBtn = findViewById<Button>(R.id.login)
        val emailEditText = findViewById<EditText>(R.id.email)
        val passwordEditText = findViewById<EditText>(R.id.pass)

        loginBtn.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Input validation
            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Send login data to server
            sendLoginData(email, password)
        }
    }

    private fun sendLoginData(email: String, password: String) {
        LoginTask().execute(email, password)
    }

    private inner class LoginTask : AsyncTask<String, Void, Boolean>() {
        override fun doInBackground(vararg params: String): Boolean {
            val email = params[0]
            val password = params[1]

            val url = URL("http://192.168.100.234/assignment3/login.php")
            val urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.doOutput = true
            urlConnection.setRequestProperty("Content-Type", "application/json")
            urlConnection.requestMethod = "POST"

            try {
                val writer = OutputStreamWriter(urlConnection.outputStream)
                val jsonData = "{ \"name\": \"$email\", \"password\": \"$password\" }"
                writer.write(jsonData)
                writer.flush()

                val responseCode = urlConnection.responseCode
                return responseCode == HttpURLConnection.HTTP_OK
            } catch (e: Exception) {
                e.printStackTrace()
                return false
            } finally {
                urlConnection.disconnect()
            }
        }

        override fun onPostExecute(result: Boolean) {
            if (result) {
                Toast.makeText(this@LoginActivity, "Login successful!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this@LoginActivity, "Login failed: Invalid credentials or error", Toast.LENGTH_SHORT).show()
            }
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
