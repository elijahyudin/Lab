package com.example.gamescoreproject.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamescoreproject.R
import com.example.gamescoreproject.adapters.WinnerListAdapter
import com.example.gamescoreproject.classes.WinnerData
import com.example.gamescoreproject.databinding.WinnerListActivityBinding

class WinnerListActivity : AppCompatActivity() {
    private lateinit var binding: WinnerListActivityBinding

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, WinnerListActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupRecyclerView()
        checkListForEmpty()
    }

    private fun setupBinding() {
        binding = WinnerListActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun checkListForEmpty() {
        when {
            WinnerData.winnerList.isEmpty() -> {
                binding.tvEmptyWinnerList.visibility = View.VISIBLE
                binding.rvWinners.visibility = View.GONE
            }
            else -> {
                binding.tvEmptyWinnerList.visibility = View.GONE
                binding.rvWinners.visibility = View.VISIBLE
            }
        }
    }

    private fun setupRecyclerView() {
        val adapter = WinnerListAdapter(WinnerData.winnerList)
        binding.rvWinners.adapter = adapter
        binding.rvWinners.layoutManager =
            LinearLayoutManager(this)
        binding.rvWinners.addItemDecoration(
            DividerItemDecoration(
                applicationContext,
                RecyclerView.VERTICAL
            )
        )
        adapter.notifyDataSetChanged()
        binding.btnSort.setOnClickListener {
            if (WinnerData.winnerList.size > 1) {
                AlertDialog.Builder(this)
                    .setTitle("Sorting")
                    .setMessage("Select the type of sort that you want to apply")
                    .setIcon(R.drawable.ic_launcher_foreground)
                    .setPositiveButton("Ascending") { dialog, which ->
                        WinnerData.winnerList.sortBy { it.winnerTeamScore }
                        adapter.notifyDataSetChanged()
                        Toast.makeText(this, "Sort by ascending!", Toast.LENGTH_LONG).show()
                    }
                    .setNeutralButton("Decline") { dialog, which ->
                        Toast.makeText(this, "You decline sorting!", Toast.LENGTH_LONG).show()
                    }
                    .setNegativeButton("Descending") { dialog, which ->
                        WinnerData.winnerList.sortByDescending { it.winnerTeamScore }
                        adapter.notifyDataSetChanged()
                        Toast.makeText(this, "Sort by descending!", Toast.LENGTH_LONG).show()
                    }
                    .show()
            } else {
                Toast.makeText(
                    this,
                    "Sort doesn't have a sense! Only one item or nothing in list of winners!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        binding.btnClear.setOnClickListener {
            when {
                WinnerData.winnerList.isNotEmpty() -> {
                    WinnerData.winnerList.clear()
                    adapter.notifyDataSetChanged()
                    binding.tvEmptyWinnerList.visibility = View.VISIBLE
                    binding.rvWinners.visibility = View.GONE

                }
                else -> Toast.makeText(this,"List has already been cleared or is empty", Toast.LENGTH_LONG).show()
            }
        }
    }
}