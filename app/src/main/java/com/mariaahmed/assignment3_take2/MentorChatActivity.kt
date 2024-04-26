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

class MentorChatActivity : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.activity_mentor_chat, container, false)

        val call = view.findViewById<Button>(R.id.callmentor)
        call.setOnClickListener {
            val intent = Intent(requireContext(), CallActivity::class.java)
            startActivity(intent)
        }

        val vidcall = view.findViewById<Button>(R.id.videocallmentor)
        vidcall.setOnClickListener {
            val intent = Intent(requireContext(),VideoCallActivity::class.java)
            startActivity(intent)
        }
        val photo = view.findViewById<ImageButton>(R.id.camerabtn)
        photo.setOnClickListener {
            val intent = Intent(requireContext(), CameraActivity::class.java)
            startActivity(intent)
        }

        val back = view.findViewById<ImageButton>(R.id.backarrow)
        back.setOnClickListener {
            val results = requireActivity() as? NavigationActivity
            results?.replaceFragment(ChatActivity())
        }

        return view
    }
}