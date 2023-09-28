package dance_tracker.common.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

data class UserPreferences (
    val shouldHideOnboarding: Boolean
)
class UserPreferencesRepository(private val dataStore: DataStore<Preferences>) {

    private val TAG: String = "UserPreferencesRepo"
    private object PreferencesKeys {
        val SHOULD_HIDE_ONBOARDING = booleanPreferencesKey("should_hide_onboarding")
    }

    val userPreferencesFlow: Flow<UserPreferences> = dataStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Log.e(TAG, "Error reading preferences.", exception)
            } else {
                Log.e(TAG, "Unexpected Error reading preferences.", exception)
            }
            emit(emptyPreferences())
        }
        .map { preferences ->
            mapUserPreferences(preferences)
        }

    private fun mapUserPreferences(preferences: Preferences): UserPreferences {
        return UserPreferences(
            shouldHideOnboarding = preferences[PreferencesKeys.SHOULD_HIDE_ONBOARDING] ?: false
        )
    }

    suspend fun updateShouldHideOnboarding(shouldHide: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.SHOULD_HIDE_ONBOARDING] = shouldHide
        }
    }
}