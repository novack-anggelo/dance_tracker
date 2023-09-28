package dance_tracker.common.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

private val Context.userPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "user"
)