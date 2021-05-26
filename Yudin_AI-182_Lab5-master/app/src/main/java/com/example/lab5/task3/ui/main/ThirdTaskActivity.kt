package com.example.lab5.task3.ui.main

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.lab5.databinding.BroadcastReceiverBinding
import com.example.lab5.task3.repo.AirplaneModeReceiver
import com.example.lab5.task3.repo.BroadcastReceiverHandler
import com.example.lab5.task3.repo.LowBatteryStateReceiver

class ThirdTaskActivity: AppCompatActivity(), BroadcastReceiverHandler {

    private lateinit var binding: BroadcastReceiverBinding
    private lateinit var airplaneModeReceiver: AirplaneModeReceiver
    private lateinit var lowBatteryStateReceiver: LowBatteryStateReceiver
    private lateinit var alertDialogBuilder: AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupListeners()
        airplaneModeReceiver = AirplaneModeReceiver()
        lowBatteryStateReceiver = LowBatteryStateReceiver()
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(
            airplaneModeReceiver, IntentFilter().apply {
                addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            }
        )
        registerReceiver(
            lowBatteryStateReceiver, IntentFilter().apply {
                addAction(Intent.ACTION_BATTERY_LOW)
            }
        )
    }

    override fun airplaneModeChanged(isOn: Boolean) {
        alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Changed airplane mode state")
        alertDialogBuilder.setPositiveButton("OK", null)
        if (isOn) {
            alertDialogBuilder.setMessage("Airplane mode is on now")
        } else {
            alertDialogBuilder.setMessage("Airplane mode is off now")
        }
        alertDialogBuilder.show()
    }

    override fun lowBattery() {
        alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Low battery")
        alertDialogBuilder.setMessage("Low battery. Please, charge!")
        alertDialogBuilder.setPositiveButton("OK", null)
        alertDialogBuilder.show()
    }

    override fun openCamera() {
        alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Camera opening")
        alertDialogBuilder.setMessage("Camera is opening now")
        alertDialogBuilder.setPositiveButton("OK", null)
        alertDialogBuilder.show()
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(airplaneModeReceiver)
        unregisterReceiver(lowBatteryStateReceiver)
    }

    private fun setupBinding() {
        binding = BroadcastReceiverBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupListeners() {
        binding.btnOpenCamera.setOnClickListener {
            Intent().apply {
                action = MediaStore.ACTION_IMAGE_CAPTURE
            }.also { startActivity(it) }
        }
    }
}