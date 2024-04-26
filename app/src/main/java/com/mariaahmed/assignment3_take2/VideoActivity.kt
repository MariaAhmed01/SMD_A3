package com.mariaahmed.assignment3_take2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class VideoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
    }
    fun camera(view: android.view.View) {
        val intent = android.content.Intent(this, CameraActivity::class.java)
        startActivity(intent)
    }
}