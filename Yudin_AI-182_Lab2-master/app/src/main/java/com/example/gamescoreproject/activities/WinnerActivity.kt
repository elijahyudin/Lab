package com.example.gamescoreproject.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.gamescoreproject.R
import com.example.gamescoreproject.activities.ScoreActivity.Companion.draw
import com.example.gamescoreproject.databinding.WinnerActivityBinding

class WinnerActivity : AppCompatActivity() {
    private lateinit var binding: WinnerActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupTextView()
    }

    private fun setupBinding() {
        binding = WinnerActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.share_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.share_item) {
            sendData()
        }
        return true
    }

    private fun setupTextView() {
        if (!draw) {
            val winnerName = intent.getStringExtra("winnerName")
            val winnerScore = intent.getIntExtra("winnerScore", 0)
            val loserName = intent.getStringExtra("loserName")
            val loserScore = intent.getIntExtra("loserScore", 0)
            binding.tvWinnerTeam.text = winnerName
            binding.tvLoserTeam.text = loserName
            binding.tvResult.text = String.format("%d:%d", winnerScore, loserScore)
            binding.tvWinner.text = getString(R.string.winner)
            binding.tvLoser.text = getString(R.string.loser)
        } else {
            val firstTeamName = intent.getStringExtra("firstTeamName")
            val secondTeamName = intent.getStringExtra("secondTeamName")
            val firstTeamScore = intent.getIntExtra("firstTeamScore", 0)
            val secondTeamScore = intent.getIntExtra("secondTeamScore", 0)
            binding.tvWinnerTeam.text = firstTeamName
            binding.tvLoserTeam.text = secondTeamName
            binding.tvResult.text = String.format("%d:%d", firstTeamScore, secondTeamScore)
            binding.tvWinner.text = getString(R.string.first_team)
            binding.tvLoser.text = getString(R.string.second_team)
        }
    }

    private fun sendData() {
        val shareIntent = Intent().apply {
            this.action = Intent.ACTION_SEND
            this.putExtra(
                Intent.EXTRA_TEXT,
                "${binding.tvWinner.text}:${binding.tvWinnerTeam.text}\n${binding.tvLoser.text}:${binding.tvLoserTeam.text}\n"
                        + "Result of match: ${binding.tvResult.text}"
            )
            this.type = "text/plain"
        }
        startActivity(shareIntent)
    }
}