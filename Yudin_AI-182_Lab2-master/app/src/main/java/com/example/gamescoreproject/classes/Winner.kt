package com.example.gamescoreproject.classes

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Winner(
    val winnerTeam: String,
    val winnerTeamScore: Int
    ) : Parcelable

object WinnerData {
    var winnerList = mutableListOf<Winner>()
}