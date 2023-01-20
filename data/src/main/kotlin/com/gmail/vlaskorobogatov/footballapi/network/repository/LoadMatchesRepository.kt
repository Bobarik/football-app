package com.gmail.vlaskorobogatov.footballapi.network.repository

import com.gmail.vlaskorobogatov.footballapi.model.Match
import com.gmail.vlaskorobogatov.footballapi.model.Team
import com.gmail.vlaskorobogatov.footballapi.network.NetworkModule
import com.gmail.vlaskorobogatov.footballapi.network.services.LoadMatchesService
import com.gmail.vlaskorobogatov.footballapi.repositories.MatchesRepository
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.GregorianCalendar
import javax.inject.Inject

class LoadMatchesRepository @Inject constructor() : MatchesRepository {

    companion object {
        private const val apiKey = "1d398de2d8e89f496de29f0d5ecad0c5e10f499575d025c2d93dda6ceb1fb254"
        private const val DATE_PATTERN = "yyyy-MM-dd"
    }

    private val service: LoadMatchesService = NetworkModule.provideRetrofit()
        .create(LoadMatchesService::class.java)

    @Suppress("SimpleDateFormat")
    override suspend fun getMatches(): Result<List<Match>> {
        val date = GregorianCalendar.getInstance().time
        DateFormat.MEDIUM
        val current = SimpleDateFormat(DATE_PATTERN).format(date)

        val result = service.getMatches(apiKey, current, current).map {
            return@map it.map { matchResponse ->
                val homeTeam = Team(
                    name = matchResponse.homeTeamName,
                    score = matchResponse.homeTeamScore,
                    badge = matchResponse.homeTeamBadge
                )
                val awayTeam = Team(
                    name = matchResponse.awayTeamName,
                    score = matchResponse.awayTeamScore,
                    badge = matchResponse.awayTeamBadge
                )
                Match(
                    id = matchResponse.matchId,
                    leagueName = matchResponse.leagueName,
                    leagueLogo = matchResponse.leagueLogo,
                    stage = matchResponse.stage,
                    date = matchResponse.matchDate,
                    time = matchResponse.matchTime,
                    status = matchResponse.matchStatus,
                    isLive = matchResponse.matchLive == "1",
                    homeTeam = homeTeam,
                    awayTeam = awayTeam
                )
            }
        }
        return result
    }
}