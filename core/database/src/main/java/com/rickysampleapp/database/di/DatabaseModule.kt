package com.rickysampleapp.database.di

import android.app.Application
import androidx.room.Room
import com.rickysampleapp.database.RickyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRickyDatabase(
        application: Application,
    ): RickyDatabase = Room
        .databaseBuilder(application, RickyDatabase::class.java, "Ricky Database")
        .build()

    @Provides
    @Singleton
    fun provideCharacterDao(
        rickyDatabase: RickyDatabase
    ) = rickyDatabase.characterDao()

    @Provides
    @Singleton
    fun provideCharacterDetailDao(
        rickyDatabase: RickyDatabase
    ) = rickyDatabase.characterDetailDao()

    @Provides
    @Singleton
    fun providePaginationInfoDao(
        rickyDatabase: RickyDatabase
    ) = rickyDatabase.paginationInfoDao()

//    @Provides
//    @Singleton
//    fun provideTypeConverter(): TypeTokenConverter = TypeTokenConverter()

}