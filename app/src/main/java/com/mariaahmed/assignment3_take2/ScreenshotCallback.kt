package com.mariaahmed.assignment3_take2

import android.app.Activity
import android.graphics.Bitmap
import android.app.Activity.ScreenCaptureCallback


class ScreenshotCallback : Activity.ScreenCaptureCallback() {
    override fun onScreenCaptured(bitmap: Bitmap) {
        super.onScreenCaptured(bitmap)
        // Handle the captured screenshot data (bitmap) here!
        //  e.g., Log it, display a message, etc.
    }
}
