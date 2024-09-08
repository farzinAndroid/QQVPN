package pro.sparrow_team.qq.data.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import pro.sparrow_team.qq.utils.Constants.DATASTORE_NAME
import javax.inject.Inject


val Context.dataStore by preferencesDataStore(DATASTORE_NAME)
class DataStoreRepoImpl @Inject constructor(@ApplicationContext private val context: Context) : DataStoreRepo {
    override suspend fun putString(value: String, key: String) {
        val preferenceKey = stringPreferencesKey(key)
        context.dataStore.edit {
            it[preferenceKey] = value
        }
    }

    override suspend fun getString(key: String): String? {
        return try {
            val preferenceKey = stringPreferencesKey(key)
            val preferences = context.dataStore.data.first()
            preferences[preferenceKey]
        }catch (e:Exception){
//            Log.e("TAG","data store error : ${e.printStackTrace()}")
            null
        }
    }

    override suspend fun putLong(value: Long, key: String) {
        val preferenceKey = longPreferencesKey(key)
        context.dataStore.edit {
            it[preferenceKey] = value
        }
    }

    override suspend fun getLong(key: String): Long? {
        return try {
            val preferenceKey = longPreferencesKey(key)
            val preferences = context.dataStore.data.first()
            preferences[preferenceKey]
        }catch (e:Exception){
//            Log.e("TAG","data store error : ${e.printStackTrace()}")
            null
        }
    }

    override suspend fun putBoolean(value: Boolean, key: String) {
        val preferenceKey = booleanPreferencesKey(key)
        context.dataStore.edit {
            it[preferenceKey] = value
        }
    }

    override suspend fun getBoolean(key: String): Boolean? {
        return try {
            val preferenceKey = booleanPreferencesKey(key)
            val preferences = context.dataStore.data.first()
            preferences[preferenceKey]
        }catch (e:Exception){
//            Log.e("TAG","data store error : ${e.printStackTrace()}")
            null
        }
    }

    override suspend fun putInt(value: Int, key: String) {
        val preferenceKey = intPreferencesKey(key)
        context.dataStore.edit {
            it[preferenceKey] = value
        }
    }

    override suspend fun getInt(key: String): Int? {
        return try {
            val preferenceKey = intPreferencesKey(key)
            val preferences = context.dataStore.data.first()
            preferences[preferenceKey]
        }catch (e:Exception){
//            Log.e("TAG","data store error : ${e.printStackTrace()}")
            null
        }
    }


}