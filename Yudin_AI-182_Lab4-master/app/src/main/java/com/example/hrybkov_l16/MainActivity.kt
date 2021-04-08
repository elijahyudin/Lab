package com.example.hrybkov_l16

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hrybkov_l16.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val countriesList = listOf(
            Country("Spain", 46_722_980, 504_645, 0.904),
            Country("Greece", 10_72_599, 131_957, 0.888),
            Country("Italy", 60_317_116, 301_340, 0.892),
            Country("Croatia", 4_058_165, 56_594, 0.851),
            Country("Portugal", 10_295_909, 92_226, 0.864),
            Country("Mali", 20_250_833, 1_240_192, 0.434),
            Country("India",1_352_642_280, 3_287_263, 0.645),
            Country("Finland", 5_528_737, 338_455, 0.938)
        )
        binding.viewPager.adapter = CountryAdapter(this, countriesList)
        TabLayoutMediator(binding.countryTabLayout, binding.viewPager) { tab, position ->
            tab.text = countriesList[position].countryName
        }.attach()
    }
}