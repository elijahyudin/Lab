package com.example.hrybkov_l15

import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_view.view.*

class RecyclerViewAdapter(private val listOfStrings: List<SpannableString>) :
    RecyclerView.Adapter<RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view, parent, false)

        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(listOfStrings[position])
    }

    override fun getItemCount(): Int {
        return listOfStrings.size
    }

}

class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(text: SpannableString) {
        itemView.rvTextView.text = text
    }
}