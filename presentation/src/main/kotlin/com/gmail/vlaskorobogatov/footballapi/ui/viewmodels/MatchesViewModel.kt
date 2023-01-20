package com.gmail.vlaskorobogatov.footballapi.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.vlaskorobogatov.footballapi.model.Match
import com.gmail.vlaskorobogatov.footballapi.repositories.MatchesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MatchesViewModel
@Inject constructor(
    private val repository: MatchesRepository
) : ViewModel() {

    val matches: Flow<Result<List<Match>>> = flow {
        emit(repository.getMatches())
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = Result.success(listOf())
    )
}