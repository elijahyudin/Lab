package com.example.lab5.task2.ui.main

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.lab5.databinding.SmsRecieverBinding
import com.example.lab5.task2.repo.SmsReceiver
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Telephony

class SecondTaskActivity : AppCompatActivity() {

    private lateinit var smsReceiver: SmsReceiver
    private lateinit var binding: SmsRecieverBinding

    companion object {
        private const val RECEIVER_SMS_CODE = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        smsReceiver = SmsReceiver()
    }

    override fun onStart() {
        super.onStart()
        if (requestPermissions()) {
            registerReceiver(
                smsReceiver, IntentFilter().apply {
                    addAction(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)
                }
            )
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(smsReceiver)
    }

    private fun setupBinding() {
        binding = SmsRecieverBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun requestPermissions(): Boolean {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.RECEIVE_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.RECEIVE_SMS),
                RECEIVER_SMS_CODE
            )
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        results: IntArray
    ) {
        when (requestCode) {
            RECEIVER_SMS_CODE -> {
                if (results[0] == PackageManager.PERMISSION_GRANTED) {
                    registerReceiver(
                        smsReceiver, IntentFilter().apply {
                            addAction(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)
                        }
                    )
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, results)
        }
    }

}