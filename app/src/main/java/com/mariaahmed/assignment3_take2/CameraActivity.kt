package com.mariaahmed.assignment3_take2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CameraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
    }
    fun video(view: android.view.View) {
        val intent = android.content.Intent(this, VideoActivity::class.java)
        startActivity(intent)
    }
}