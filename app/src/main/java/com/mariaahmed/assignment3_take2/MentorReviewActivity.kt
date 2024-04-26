package com.mariaahmed.assignment3_take2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MentorReviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mentor_review)
        val submitbtn = findViewById<Button>(R.id.feedback)
        submitbtn.setOnClickListener {
            val intent = Intent(this, MentorActivity::class.java)
            startActivity(intent)
        }
        val back = findViewById<ImageButton>(R.id.backarrow)
        back.setOnClickListener {
            val intent = Intent(this, MentorActivity::class.java)
            startActivity(intent)
        }
    }
}