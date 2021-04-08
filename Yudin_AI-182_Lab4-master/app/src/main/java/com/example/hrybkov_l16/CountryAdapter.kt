package com.example.hrybkov_l16

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class CountryAdapter(activity: AppCompatActivity, private val countriesList: List<Country>) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = countriesList.size

    override fun createFragment(position: Int): Fragment = CountryFragment.newInstance(countriesList[position])
}