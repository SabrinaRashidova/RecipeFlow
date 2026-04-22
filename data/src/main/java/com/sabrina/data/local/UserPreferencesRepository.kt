package com.sabrina.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserPreferencesRepository @Inject constructor(
    @ApplicationContext private val context: Context
){
    private val IS_FIRST_TIME = booleanPreferencesKey("is_first_time")

    val isFirstTime: Flow<Boolean> = context.dataStore.data.map { it[IS_FIRST_TIME] ?: true }

    suspend fun setNotFirstTime(){
        context.dataStore.edit { it[IS_FIRST_TIME] = false }
    }
}