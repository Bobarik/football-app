package com.gmail.vlaskorobogatov.footballapi.model

data class Match(
    val id: Int,
    val leagueName: String,
    val leagueLogo: String,
    val stage: String,
    val date: String,
    val time: String,
    val status: String,
    val isLive: Boolean,
    val homeTeam: Team,
    val awayTeam: Team,
)