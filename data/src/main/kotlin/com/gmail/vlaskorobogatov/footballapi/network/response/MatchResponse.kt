package com.gmail.vlaskorobogatov.footballapi.network.response

import com.google.gson.annotations.SerializedName

data class MatchResponse(
    @SerializedName("match_id") val matchId: Int,
    @SerializedName("league_name") val leagueName: String,
    @SerializedName("league_logo") val leagueLogo: String,
    @SerializedName("match_date") val matchDate: String,
    @SerializedName("match_time") val matchTime: String,
    @SerializedName("match_status") val matchStatus: String,
    @SerializedName("match_live") val matchLive: String,
    @SerializedName("match_hometeam_name") val homeTeamName: String,
    @SerializedName("match_hometeam_score") val homeTeamScore: String,
    @SerializedName("team_home_badge") val homeTeamBadge: String,
    @SerializedName("match_awayteam_name") val awayTeamName: String,
    @SerializedName("match_awayteam_score") val awayTeamScore: String,
    @SerializedName("team_away_badge") val awayTeamBadge: String,
    @SerializedName("stage_name") val stage: String,
)