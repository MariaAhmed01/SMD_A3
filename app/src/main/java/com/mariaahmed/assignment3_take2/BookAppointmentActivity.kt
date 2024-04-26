package com.mariaahmed.assignment3_take2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.ImageButton
import java.util.Calendar

class BookAppointmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_appointment)

        val book = findViewById<Button>(R.id.bookappoint)
        book.setOnClickListener {
            val intent = Intent(this, MentorActivity::class.java)
            startActivity(intent)
        }

        val back = findViewById<ImageButton>(R.id.backarrow)
        back.setOnClickListener {
            val intent = Intent(this, MentorActivity::class.java)
            startActivity(intent)
        }
        val chat = findViewById<Button>(R.id.chat)
        chat.setOnClickListener {
            val intent = Intent(this, NavigationActivity::class.java)
            intent.putExtra("FROM_MENTOR_ACTIVITY", true)
            startActivity(intent)

        }
        val call = findViewById<Button>(R.id.call)
        call.setOnClickListener {
            val intent = Intent(this, CallActivity::class.java)
            startActivity(intent)
        }
        val vidcall = findViewById<Button>(R.id.videocall)
        vidcall.setOnClickListener {
            val intent = Intent(this, VideoCallActivity::class.java)
            startActivity(intent)
        }

        val calendarView = findViewById<CalendarView>(R.id.cal)

        // Set Monday as the first day of the week
        calendarView.firstDayOfWeek = Calendar.MONDAY

        // Set the default month to December 2023
        val defaultDate = Calendar.getInstance()
        defaultDate.set(2023, Calendar.DECEMBER, 24)

        // Set the minimum date to February 1, 2017
        val minDate = Calendar.getInstance()
        minDate.set(2023, Calendar.DECEMBER, 1)

        // Set the maximum date to February 28, 2017
        val maxDate = Calendar.getInstance()
        maxDate.set(2023, Calendar.DECEMBER, 31)

        calendarView.minDate = minDate.timeInMillis
        calendarView.maxDate = maxDate.timeInMillis
        calendarView.date = defaultDate.timeInMillis
    }
}