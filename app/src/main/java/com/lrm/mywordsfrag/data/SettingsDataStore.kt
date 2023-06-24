package com.lrm.mywordsfrag.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private const val LAYOUT_PREFERENCES_NAME = "layout_preferences"

val Context.datastore
: DataStore<Preferences> by preferencesDataStore(name = LAYOUT_PREFERENCES_NAME)

class SettingsDataStore(context: Context) {

    private val LINEAR_LAYOUT = booleanPreferencesKey("linear_layout")

    suspend fun saveLayoutMode(linearLayout: Boolean, context: Context) {
        context.datastore.edit { preferences ->
            preferences[LINEAR_LAYOUT] = linearLayout
        }
    }

    val preferenceFlow: Flow<Boolean> = context.datastore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[LINEAR_LAYOUT] ?: true
        }
}