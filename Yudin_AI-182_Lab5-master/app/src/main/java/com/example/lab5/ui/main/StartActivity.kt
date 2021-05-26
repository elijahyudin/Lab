package com.example.lab5.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab5.databinding.StartActivityBinding
import com.example.lab5.task2.ui.main.SecondTaskActivity
import com.example.lab5.task3.ui.main.ThirdTaskActivity

class StartActivity: AppCompatActivity() {

    private lateinit var binding: StartActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupListeners()
    }

    private fun setupBinding() {
        binding = StartActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupListeners() {
        binding.task2.setOnClickListener {
            val intent = Intent(this, SecondTaskActivity::class.java)
            startActivity(intent)
        }
        binding.task3.setOnClickListener {
            val intent = Intent(this, ThirdTaskActivity::class.java)
            startActivity(intent)
        }
    }
}