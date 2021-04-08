package com.example.gamescoreproject.activities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.gamescoreproject.R
import com.example.gamescoreproject.classes.Winner
import com.example.gamescoreproject.classes.WinnerData
import com.example.gamescoreproject.databinding.ScoreActivityBinding

class ScoreActivity : AppCompatActivity() {
    private lateinit var binding: ScoreActivityBinding
    private lateinit var countDownTimer: CountDownTimer
    private var timerPaused = false
    private var timeToEnd = 0L
    private var winnerName = ""
    private var winnerScore = 0
    private var loserName = ""
    private var loserScore = 0
    private var firstTeamScore = 0
    private var secondTeamScore = 0
    private var isResume = true

    companion object {
        var draw = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupListeners()
    }

    override fun onResume() {
        super.onResume()
        isResume = true
    }

    override fun onStop() {
        super.onStop()
        isResume = false
    }

    private fun setupBinding() {
        binding = ScoreActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupListeners() {
        timeToEnd = intent.getIntExtra("seconds", 0).toLong()
        setupGameInfo()
        setupTimer()
        increaseTeamsResult()
        decreaseTeamsResult()
        cancelGame()
        continueOrStopGame()
        goToWinner()
        goToWinnerList()
    }

    private fun setupGameInfo() {
        binding.tvFirstTeamName.text = intent.getStringExtra("firstTeamName")
        binding.tvSecondTeamName.text = intent.getStringExtra("secondTeamName")
        binding.tvFirstTeamScore.text = "$firstTeamScore"
        binding.tvSecondTeamScore.text = "$secondTeamScore"
    }

    private fun setupTimer() {
        countDownTimer = object : CountDownTimer(timeToEnd * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if (!timerPaused) {
                    updateUI(millisUntilFinished)
                } else {
                    timeToEnd = millisUntilFinished / 1000
                    ++timeToEnd
                    cancel()
                }
            }

            override fun onFinish() {
                if (isResume) {
                    Toast.makeText(
                        this@ScoreActivity,
                        "Match finished!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    setupNotification()
                }
                binding.btnIncreaseFirstTeamScore.isEnabled = false
                binding.btnIncreaseSecondTeamScore.isEnabled = false
                binding.btnDecreaseFirstTeamScore.isEnabled = false
                binding.btnDecreaseSecondTeamScore.isEnabled = false
                binding.btnContinue.isEnabled = false
                binding.btnStop.isEnabled = false
                binding.btnSeeWinner.isEnabled = true
                binding.btnSeeWinnerList.isEnabled = true
                binding.btnCancelGame.text = getString(R.string.new_game)
                matchResult()
            }

        }.start()
    }

    private fun matchResult() {
        when {
            firstTeamScore > secondTeamScore -> {
                WinnerData.winnerList.add(
                    Winner(
                        binding.tvFirstTeamName.text.toString(),
                        firstTeamScore
                    )
                )
                draw = false
                winnerName = binding.tvFirstTeamName.text.toString()
                winnerScore = firstTeamScore
                loserName = binding.tvSecondTeamName.text.toString()
                loserScore = secondTeamScore
            }
            firstTeamScore < secondTeamScore -> {
                WinnerData.winnerList.add(
                    Winner(
                        binding.tvSecondTeamName.text.toString(),
                        secondTeamScore
                    )
                )
                draw = false
                winnerName = binding.tvSecondTeamName.text.toString()
                winnerScore = secondTeamScore
                loserName = binding.tvFirstTeamName.text.toString()
                loserScore = firstTeamScore
            }
            firstTeamScore == secondTeamScore -> {
                draw = true
            }
        }
    }

    private fun setupNotification() {
        val channelId = "MATCH_RESULT"
        val name = getString(R.string.channel_name)
        val descriptionText = getString(R.string.channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
        val notificationId = 1
        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Match result")
            .setAutoCancel(true)
            .setContentText("Match: ${binding.tvFirstTeamName.text} vs ${binding.tvSecondTeamName.text}\nResult: ${firstTeamScore}:${secondTeamScore}")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, builder.build())
        }
    }

    private fun continueOrStopGame() {
        binding.btnStop.setOnClickListener {
            timerPaused = true
            binding.btnContinue.isEnabled = true
            binding.btnStop.isEnabled = false
            binding.btnIncreaseFirstTeamScore.isEnabled = false
            binding.btnIncreaseSecondTeamScore.isEnabled = false
            binding.btnDecreaseFirstTeamScore.isEnabled = false
            binding.btnDecreaseSecondTeamScore.isEnabled = false
        }
        binding.btnContinue.setOnClickListener {
            timerPaused = false
            binding.btnContinue.isEnabled = false
            binding.btnStop.isEnabled = true
            binding.btnIncreaseFirstTeamScore.isEnabled = true
            binding.btnIncreaseSecondTeamScore.isEnabled = true
            binding.btnDecreaseFirstTeamScore.isEnabled = true
            binding.btnDecreaseSecondTeamScore.isEnabled = true
            setupTimer()
        }
    }

    private fun increaseTeamsResult() {
        binding.btnIncreaseFirstTeamScore.setOnClickListener {
            binding.tvFirstTeamScore.text = "${++firstTeamScore}"
        }

        binding.btnIncreaseSecondTeamScore.setOnClickListener {
            binding.tvSecondTeamScore.text = "${++secondTeamScore}"
        }
    }

    private fun cancelGame() {
        binding.btnCancelGame.setOnClickListener {
            countDownTimer.cancel()
            MainActivity.start(this)
            this.finish()
        }
    }

    private fun goToWinner() {
        binding.btnSeeWinner.setOnClickListener {
            val intent = Intent(this, WinnerActivity::class.java)
            if (!draw) {
                intent.putExtra("winnerName", winnerName)
                intent.putExtra("winnerScore", winnerScore)
                intent.putExtra("loserName", loserName)
                intent.putExtra("loserScore", loserScore)
            } else {
                intent.putExtra("firstTeamName", binding.tvFirstTeamName.text.toString())
                intent.putExtra("secondTeamName", binding.tvSecondTeamName.text.toString())
                intent.putExtra("firstTeamScore", firstTeamScore)
                intent.putExtra("secondTeamScore", secondTeamScore)
            }
            startActivity(intent)
        }
    }

    private fun goToWinnerList() {
        binding.btnSeeWinnerList.setOnClickListener {
            WinnerListActivity.start(this)
        }
    }

    private fun updateUI(millisUntilFinished: Long) {
        var seconds = (millisUntilFinished / 1000).toInt()
        val minutes = seconds / 60
        seconds %= 60
        binding.tvTime.text = String.format("%02d:%02d", minutes, seconds)
    }

    private fun decreaseTeamsResult() {
        binding.btnDecreaseFirstTeamScore.setOnClickListener {
            if (firstTeamScore != 0) {
                binding.tvFirstTeamScore.text = "${--firstTeamScore}"
            }
        }

        binding.btnDecreaseSecondTeamScore.setOnClickListener {
            if (secondTeamScore != 0) {
                binding.tvSecondTeamScore.text = "${--secondTeamScore}"
            }
        }
    }
}