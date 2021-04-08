package com.example.hrybkov_l16

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Country(
    val countryName: String,
    val countryPopulation: Int,
    val countrySquare: Int,
    val hdi: Double,

    ) : Parcelable {
    val countryPopulationDensity = countryPopulation.toDouble() / countrySquare.toDouble()
}