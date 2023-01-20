package com.gmail.vlaskorobogatov.footballapi.repositories

import com.gmail.vlaskorobogatov.footballapi.model.Match

interface MatchesRepository {

    suspend fun getMatches(): Result<List<Match>>
}