package com.github.pedroscott.droidchat.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    private const val PREFERENCE_DATA_STORE_NAME = "app_preferences"

    private val Context.preferenceDataStore: DataStore<Preferences> by preferencesDataStore(
        name = PREFERENCE_DATA_STORE_NAME
    )

    @Provides
    @Singleton
    fun providePreferenceDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = context.preferenceDataStore
}