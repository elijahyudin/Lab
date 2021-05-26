package com.example.lab5.task3.repo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AirplaneModeReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            if (context is BroadcastReceiverHandler) {
                context.airplaneModeChanged(airplaneModeCheck(intent))
            }
        }
    }

    private fun airplaneModeCheck(intent: Intent?): Boolean {
        return intent?.getBooleanExtra("Airplane mode state", false) ?: false
    }
}