package com.mariaahmed.assignment3_take2

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView

class EditProfileActivity : AppCompatActivity() {
    private val countries = arrayOf("Select Country", "Pakistan", "Malaysia", "Singapore", "Taiwan")
    private var cities = arrayOf("Select City")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val back = findViewById<ImageButton>(R.id.backarrow)
        back.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        val profileupdate = findViewById<Button>(R.id.updateprofile)
        profileupdate.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
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

    }
}