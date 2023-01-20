package com.gmail.vlaskorobogatov.footballapi.network.services

import com.gmail.vlaskorobogatov.footballapi.network.response.MatchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LoadMatchesService {
    @GET("apiv3.apifootball.com/?action=get_events")
    suspend fun getMatches(
        @Query("APIkey") apiKey: String,
        @Query("from") startDate: String,
        @Query("to") endData: String
    ): Result<List<MatchResponse>>
}