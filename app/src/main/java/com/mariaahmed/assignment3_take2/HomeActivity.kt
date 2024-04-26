package com.mariaahmed.assignment3_take2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton

class HomeActivity : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_home, container, false)

        val mentorbtn = view.findViewById<Button>(R.id.mentorpage)

        mentorbtn.setOnClickListener {
            val intent = Intent(requireContext(), MentorActivity::class.java)
            startActivity(intent)
        }

        val notif = view.findViewById<ImageButton>(R.id.notif)

        notif.setOnClickListener {
            val intent = Intent(requireContext(), NotificationActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}
