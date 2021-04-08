package com.example.gamescoreproject.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.gamescoreproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupListeners()
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupListeners() {
        inputFieldsIsNotEmpty()
        goToGame()

    }

    private fun inputFieldsIsNotEmpty() {
        binding.etFirstTeam.addTextChangedListener(implementTextWatcher())
        binding.etSecondTeam.addTextChangedListener(implementTextWatcher())
        binding.etSeconds.addTextChangedListener(implementTextWatcher())
    }

    private fun implementTextWatcher() = object : TextWatcher {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            binding.btnGoToGame.isEnabled =
                binding.etFirstTeam.text.isNotEmpty() && binding.etSecondTeam.text.isNotEmpty()
                        && binding.etSeconds.text.isNotEmpty()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
    }

    private fun goToGame() {
        binding.btnGoToGame.setOnClickListener {
            val intent = Intent(this, ScoreActivity::class.java)
            intent.putExtra("firstTeamName", binding.etFirstTeam.text.toString())
            intent.putExtra("secondTeamName", binding.etSecondTeam.text.toString())
            intent.putExtra("seconds", binding.etSeconds.text.toString().toInt())
            startActivity(intent)
            finish()
        }
    }
}