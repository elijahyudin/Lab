package com.example.lab5.task3.repo

interface BroadcastReceiverHandler {
    fun airplaneModeChanged(isOn: Boolean)
    fun lowBattery()
    fun openCamera()
}