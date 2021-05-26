package com.example.lab5.task3.repo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class LowBatteryStateReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BATTERY_LOW) {
            if (context is BroadcastReceiverHandler) {
                context.lowBattery()
            }
        }
    }
}