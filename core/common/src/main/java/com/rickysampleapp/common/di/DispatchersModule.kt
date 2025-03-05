package com.rickysampleapp.common.di

import com.rickysampleapp.common.Dispatcher
import com.rickysampleapp.common.RickyDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    @Provides
    @Dispatcher(RickyDispatchers.IO)
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

}