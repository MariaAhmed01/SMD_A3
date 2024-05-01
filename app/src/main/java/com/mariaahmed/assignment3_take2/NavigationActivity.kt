package com.mariaahmed.assignment3_take2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mariaahmed.assignment3_take2.databinding.ActivityNavigationBinding

class NavigationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fromMentorActivity = intent.getBooleanExtra("FROM_MENTOR_ACTIVITY", false)
        if (fromMentorActivity) {
            replaceFragment(CommunityActivity())
            binding.bottomnavigation.selectedItemId = R.id.navigation_chat

        }

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            replaceFragment(AddMentorActivity())
        }

        binding.bottomnavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> replaceFragment(HomeActivity())
                R.id.navigation_search -> replaceFragment(SearchActivity())
                R.id.navigation_chat -> replaceFragment(ChatActivity())
                R.id.navigation_profile -> replaceFragment(ProfileActivity())
            }
            true
        }

        // Set the default fragment
        if (savedInstanceState == null && !fromMentorActivity) {
            replaceFragment(HomeActivity())
        }
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit()
    }


    companion object {
        fun switchFragment(activity: NavigationActivity, fragmentClass: Class<out Fragment>) {
            val fragment = fragmentClass.newInstance()
            activity.replaceFragment(fragment) // Assuming replaceFragment is defined
        }
    }


    public fun switchFragment(fragmentClass: Class<out Fragment>) {
        val fragment = fragmentClass.newInstance()
        replaceFragment(fragment)
    }


    fun switchHome() {
        replaceFragment(HomeActivity())
        binding.bottomnavigation.selectedItemId = R.id.navigation_home
    }

    fun showSearchResults() {
        replaceFragment(SearchResultsActivity())
    }

    fun mentorChat() {
        replaceFragment(MentorChatActivity())
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val loggedIn = intent?.getBooleanExtra("loggedIn", false) ?: false
        if (loggedIn) {
            replaceFragment(HomeActivity())
        }
    }


}
