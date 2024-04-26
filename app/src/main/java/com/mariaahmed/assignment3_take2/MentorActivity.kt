package com.mariaahmed.assignment3_take2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton

class MentorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mentor)

        val reviewbtn = findViewById<Button>(R.id.review)
        reviewbtn.setOnClickListener {
            val intent = Intent(this, MentorReviewActivity::class.java)
            startActivity(intent)
        }

        val booksession = findViewById<Button>(R.id.session)
        booksession.setOnClickListener {
            val intent = Intent(this, BookAppointmentActivity::class.java)
            startActivity(intent)
        }

        val community = findViewById<Button>(R.id.community)
        community.setOnClickListener {
            val intent = Intent(this, NavigationActivity::class.java)
            intent.putExtra("FROM_MENTOR_ACTIVITY", true)
            startActivity(intent)

        }
        val back = findViewById<ImageButton>(R.id.backarrow)
        back.setOnClickListener {
            val intent = Intent(this, NavigationActivity::class.java)
            startActivity(intent)
        }

    }
}
