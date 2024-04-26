package com.mariaahmed.assignment3_take2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment

class ProfileActivity : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_profile, container, false)

        val editprofile = view.findViewById<Button>(R.id.editprofile)

        editprofile.setOnClickListener {
            val intent = Intent(requireContext(), EditProfileActivity::class.java)
            startActivity(intent)
        }

        val booked = view.findViewById<Button>(R.id.booksession)
        booked.setOnClickListener {
            val intent = Intent(requireContext(), BookedActivity::class.java)
            startActivity(intent)
        }

        val back = view.findViewById<ImageButton>(R.id.backarrow)
        back.setOnClickListener {
            val results = requireActivity() as? NavigationActivity
            results?.switchHome()
        }

        return view
    }
}

