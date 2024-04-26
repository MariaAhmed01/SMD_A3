package com.mariaahmed.assignment3_take2

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class SignupActivity : AppCompatActivity() {

    private val countries = arrayOf("Select Country", "Pakistan", "Malaysia", "Singapore", "Taiwan")
    private var cities = arrayOf("Select City")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val signupbtn = findViewById<Button>(R.id.signupbtn)
        signupbtn.setOnClickListener {

            val name = findViewById<EditText>(R.id.name).text.toString()
            val email = findViewById<EditText>(R.id.email).text.toString()
            var contact = findViewById<EditText>(R.id.contact).text.toString()
            val country = findViewById<Spinner>(R.id.country).selectedItem.toString()
            val city = findViewById<Spinner>(R.id.city).selectedItem.toString()
            val password = findViewById<EditText>(R.id.pass).text.toString()

            // Input validation
            var isValid = true
            val validationMessage = StringBuilder()

            if (name.isEmpty()) {
                isValid = false
                validationMessage.append("Name cannot be empty\n")
            }

            if (email.isEmpty()) {
                isValid = false
                validationMessage.append("Email cannot be empty\n")
            }

            if (contact.isEmpty()) {
                contact = "0" // Send "0" if empty
            }

            if (country.isEmpty()) {
                isValid = false
                validationMessage.append("Country cannot be empty\n")
            }

            if (city.isEmpty()) {
                isValid = false
                validationMessage.append("City cannot be empty\n")
            }

            if (password.isEmpty()) {
                isValid = false
                validationMessage.append("Password cannot be empty\n")
            } else if (password.length < 8) {
                isValid = false
                validationMessage.append("Password must be at least 8 characters long\n")
            }

            if (!isValid) {
                // Show error message to the user
                Toast.makeText(this, validationMessage.toString(), Toast.LENGTH_SHORT).show()
            }
            sendDataToServer(name, password, email, contact, city, country)

            val intent = android.content.Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

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
                val cityAdapter = ArrayAdapter(this@SignupActivity, R.layout.spinner_design, cities)
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

    }

    fun sendDataToServer(name: String, password: String, email: String, phone: String, city: String, country: String) {
        val url = "http://192.168.100.234/assignment3/addinfo(signup).php"

        val requestQueue = Volley.newRequestQueue(this)
        val stringRequest = object : StringRequest(Method.POST, url,
            Response.Listener { response ->
                // Handle the response from the server
                Log.d("Response", response)
                // You can implement logic here to handle success or error messages
            },
            Response.ErrorListener { error ->
                // Handle errors here
                Log.e("Error", error.toString())
            }) {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["name"] = name
                params["password"] = password
                params["email"] = email
                params["phone"] = phone
                params["city"] = city
                params["country"] = country
                return params
            }
        }
        requestQueue.add(stringRequest)
    }


    fun login(view: View) {
        val intent = android.content.Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
