package com.example.lab5.task3.repo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.MediaStore

class TapOnCameraReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == MediaStore.ACTION_IMAGE_CAPTURE ) {
            if (context is BroadcastReceiverHandler) {
                context.openCamera()
            }
        }
    }
}