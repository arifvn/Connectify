package com.squareit.connectify.di

import android.content.Context
import com.squareit.connectify.lib.Connectify
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class ConnectifyModule {
    @Singleton
    @Provides
    fun provideConnectify(@ApplicationContext context: Context) = Connectify(context)
}