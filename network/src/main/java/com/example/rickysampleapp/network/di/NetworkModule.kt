package com.example.rickysampleapp.network.di

import com.example.rickysampleapp.network.RickyNetworkDataSource
import com.example.rickysampleapp.network.ktor.RickyKtorClient
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    fun bindRickyNetworkDataSource(rickyKtorClient: RickyKtorClient): RickyNetworkDataSource

}