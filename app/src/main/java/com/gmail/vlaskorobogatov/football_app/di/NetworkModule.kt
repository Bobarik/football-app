package com.gmail.vlaskorobogatov.football_app.di

import com.gmail.vlaskorobogatov.footballapi.network.repository.LoadMatchesRepository
import com.gmail.vlaskorobogatov.footballapi.repositories.MatchesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    fun bindRepository(repository: LoadMatchesRepository): MatchesRepository
}