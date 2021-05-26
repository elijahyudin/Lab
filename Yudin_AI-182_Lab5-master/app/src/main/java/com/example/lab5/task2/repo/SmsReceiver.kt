package com.example.lab5.task2.repo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.widget.Toast

class SmsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
            Telephony.Sms.Intents.getMessagesFromIntent(intent).forEach {
                val message = "From ${it.originatingAddress}: ${it.messageBody}"
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}