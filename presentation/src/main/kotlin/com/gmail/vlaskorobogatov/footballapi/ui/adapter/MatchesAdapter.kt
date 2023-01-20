package com.gmail.vlaskorobogatov.footballapi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gmail.vlaskorobogatov.football_app.databinding.MatchCardLayoutBinding
import com.gmail.vlaskorobogatov.footballapi.model.Match

class MatchesAdapter : ListAdapter<Match, MatchViewHolder>(MatchDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(
            MatchCardLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = getItem(position)
        holder.bindItems(match!!)
    }
}

class MatchViewHolder(private val viewBinding: MatchCardLayoutBinding) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bindItems(match: Match) {
        with(viewBinding) {
            matchDate.text = match.date
            matchTime.text = match.time
            matchStatus.text = if (match.status.isNotBlank()) match.status else "Not started."

            leagueName.text = match.leagueName
            stageName.text = match.stage
            if (match.leagueLogo.isBlank())
                leagueIcon.isVisible = false
            else
                leagueIcon.load(match.leagueLogo)

            awayTeamBadge.load(match.awayTeam.badge)
            awayTeamName.text = match.awayTeam.name
            awayTeamScore.text = match.awayTeam.score

            homeTeamBadge.load(match.homeTeam.badge)
            homeTeamName.text = match.homeTeam.name
            homeTeamScore.text = match.homeTeam.score
        }
    }
}

private class MatchDiffCallback : DiffUtil.ItemCallback<Match>() {

    override fun areItemsTheSame(oldItem: Match, newItem: Match): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Match, newItem: Match): Boolean {
        return oldItem == newItem
    }
}