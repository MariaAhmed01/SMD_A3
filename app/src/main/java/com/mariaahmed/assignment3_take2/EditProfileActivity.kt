package com.mariaahmed.assignment3_take2

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import org.json.JSONObject
import java.io.BufferedWriter
import java.io.InputStream
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class EditProfileActivity : AppCompatActivity() {
    private val countries = arrayOf("Select Country", "Pakistan", "Malaysia", "Singapore", "Taiwan")
    private var cities = arrayOf("Select City")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val nameEditText = findViewById<EditText>(R.id.name)
        val emailEditText = findViewById<EditText>(R.id.email)
        val phoneEditText = findViewById<EditText>(R.id.contact)

        val back = findViewById<ImageButton>(R.id.backarrow)
        back.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        val profileupdate = findViewById<Button>(R.id.updateprofile)

        val countryAdapter = ArrayAdapter(this, R.layout.spinner_design, countries)
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val countrySpinner = findViewById<Spinner>(R.id.country)
        countrySpinner.adapter = countryAdapter

        val citySpinner = findViewById<Spinner>(R.id.city)
        //adding hint for country spinner
        countrySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCountry = countries[position]
                cities = when (selectedCountry) {
                    "Pakistan" -> arrayOf("Select City", "Rawalpindi", "Islamabad", "Lahore", "Muzaffarabad")
                    "Malaysia" -> arrayOf("Select City", "Kuala Lumpur", "Penang", "Johor Bahru", "Ipoh")
                    "Singapore" -> arrayOf("Select City", "Singapore City", "Jurong West", "Bedok", "Tampines")
                    "Taiwan" -> arrayOf("Select City", "Taipei", "Kaohsiung", "Taichung", "Tainan")
                    else -> arrayOf("Select City")
                }
                val cityAdapter = ArrayAdapter(this@EditProfileActivity, R.layout.spinner_design, cities)
                cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                citySpinner.adapter = cityAdapter
                if(selectedCountry == countries[0]){
                    (view as TextView).setTextColor(Color.parseColor("#A9A9A9"))
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do Something
            }
        }

        //adding hint for city spinner
        citySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val value = parent!!.getItemAtPosition(position).toString()
                if(value == cities[0]){
                    (view as TextView).setTextColor(Color.parseColor("#A9A9A9"))
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do Something
            }
        }

        profileupdate.setOnClickListener {
            val userId = getUserIdFromSharedPref()
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val selectedCountry = countrySpinner.selectedItem.toString()
            val selectedCity = citySpinner.selectedItem.toString()

            updateUserInfoToServer(userId, name, email, phone, selectedCountry, selectedCity)
        }


    }

    private fun getUserIdFromSharedPref(): Int {

        val userId = 3
        return userId
    }


    private fun updateUserInfoToServer(userId: Int, name: String, email: String, phone: String, country: String, city: String) {
        val url = URL("http://192.168.100.234/assignment3/updateuserprofile.php") // Replace with your server URL
        val urlConnection = url.openConnection() as HttpURLConnection
        urlConnection.doOutput = true
        urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded") // Set content type for form data
        urlConnection.requestMethod = "POST"

        val postData = "ID=$userId&name=$name&email=$email&phone=$phone&city=$city&country=$country"

        try {
            val outputStream = urlConnection.outputStream
            val writer = BufferedWriter(OutputStreamWriter(outputStream, "UTF-8"))
            writer.write(postData)
            writer.flush()
            writer.close()
            outputStream.close()

            val responseCode = urlConnection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val responseString = inputStreamToString(urlConnection.inputStream)
                Log.d("EditProfileActivity", "Server Response: $responseString")
            } else {
                Log.e("EditProfileActivity", "Error sending update request: ${responseCode}")
            }
        } catch (e: Exception) {
            Log.e("EditProfileActivity", "Error updating user info: ${e.message}")
        } finally {
            urlConnection.disconnect()
        }
    }

    private fun inputStreamToString(inputStream: InputStream): String {
        val builder = StringBuilder()
        val buffer = ByteArray(8192)
        var bytesRead: Int
        while (inputStream.read(buffer).also { bytesRead = it } != -1) {
            builder.append(String(buffer, 0, bytesRead))
        }
        return builder.toString()
    }



}