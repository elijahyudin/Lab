package com.example.gamescoreproject.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gamescoreproject.R
import com.example.gamescoreproject.classes.Winner
import kotlinx.android.synthetic.main.winner_list_recycler_view.view.*

class WinnerListAdapter(var winner: List<Winner>) :
    RecyclerView.Adapter<WinnerListAdapter.WinnerListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WinnerListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.winner_list_recycler_view, parent, false)
        return WinnerListViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: WinnerListViewHolder, position: Int) {
        holder.itemView.apply {
            tvWinnerName.text = "Team name: ${winner[position].winnerTeam}"
            tvWinnerScore.text = "Score: ${winner[position].winnerTeamScore}"
        }
    }

    override fun getItemCount(): Int {
        return winner.size
    }


    inner class WinnerListViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
