package com.example.hrybkov_l15

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecycleViewAdapter()
        setupListeners()
    }

    private val listOfStrings = mutableListOf<SpannableString>()

    private fun setupRecycleViewAdapter() {
        rV.adapter = RecyclerViewAdapter(listOfStrings)
        rV.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        rV.addItemDecoration(DividerItemDecoration(applicationContext, RecyclerView.VERTICAL))
    }

    private fun setupListeners() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val name = etName.text.toString()
                val lastName = etLastName.text.toString()

                button.isEnabled = name.isNotEmpty() && lastName.isNotEmpty()

                if (name.isNotEmpty() && name[0].isLowerCase() || lastName.isNotEmpty() && lastName[0].isLowerCase()) {
                    button.isEnabled = false
                }
            }

        }

        etName.addTextChangedListener(textWatcher)
        etLastName.addTextChangedListener(textWatcher)

        button.setOnClickListener {
            val name = etName.text.toString()
            val lastName = etLastName.text.toString()

            val spannableString = SpannableString("$lastName $name ")
            val startIndex = spannableString.indexOf(lastName)
            val endIndex = startIndex + lastName.length + 1

            spannableString.setSpan(UnderlineSpan(), startIndex, endIndex, 0)
            spannableString.setSpan(ForegroundColorSpan(Color.MAGENTA), startIndex, endIndex, 0)

            listOfStrings.add(spannableString)
            rV.adapter?.notifyItemInserted(listOfStrings.size)
        }
    }
}