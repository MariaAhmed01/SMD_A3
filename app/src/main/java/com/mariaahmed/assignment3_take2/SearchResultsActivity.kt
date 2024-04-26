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

class SearchResultsActivity : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.activity_search_results, container, false)

        val back = view.findViewById<ImageButton>(R.id.backarrow)
        back.setOnClickListener {
            val results = requireActivity() as? NavigationActivity
            results?.replaceFragment(SearchActivity())
        }
        return view

    }
}