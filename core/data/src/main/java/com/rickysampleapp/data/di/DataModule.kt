package com.rickysampleapp.data.di

import com.rickysampleapp.data.repository.CharacterRepository
import com.rickysampleapp.data.repository.CharacterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindCharacterRepository(characterRepository: CharacterRepositoryImpl): CharacterRepository

}