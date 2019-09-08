package io.github.pps5.kakaosampleapp.data.repository

import android.content.SharedPreferences
import android.util.Log
import io.github.pps5.kakaosampleapp.common.coroutines.HasDispatchers
import io.github.pps5.kakaosampleapp.data.entity.Entry
import io.github.pps5.kakaosampleapp.data.entity.SearchResponse
import io.github.pps5.kakaosampleapp.data.extension.lastEntryCachedDate
import io.github.pps5.kakaosampleapp.data.store.AppDatabase
import io.github.pps5.kakaosampleapp.data.store.ConnpassService
import kotlinx.coroutines.coroutineScope
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.threeten.bp.LocalDateTime

class ConnpassRepository : KoinComponent, HasDispatchers {

    companion object {
        private val TAG = ConnpassRepository::class.java.simpleName
    }

    private val connpassService: ConnpassService by inject()
    private val appDatabase: AppDatabase by inject()
    private val preferences: SharedPreferences by inject()

    suspend fun search(keyword: String): SearchResponse? = withIOContext {
        runCatching {
            connpassService.searchAsync(keyword).await()
        }
            .onSuccess { return@withIOContext it }
            .onFailure { return@withIOContext null }
        // dummy return
        return@withIOContext null
    }

    suspend fun getNewArrivals(): List<Entry> = withIOContext {
        val cacheExpiresDateTime = preferences.lastEntryCachedDate.plusHours(1)
        val cachedEntries = appDatabase.entryDao().getAll()
        if (LocalDateTime.now().isAfter(cacheExpiresDateTime) || cachedEntries.isEmpty()) {
            Log.d(TAG, "Fetch from network")
            runCatching { connpassService.getNewArrivalsAsync().await() }
                .onSuccess {
                    appDatabase.cacheEntries(it.entry)
                    return@withIOContext it.entry }
                .onFailure { return@withIOContext listOf<Entry>() }
        }
        return@withIOContext cachedEntries
    }

    private suspend fun AppDatabase.cacheEntries(entries: List<Entry>) = coroutineScope {
        runInTransaction {
            entryDao().let {
                it.deleteAll()
                it.addAll(entries)
            }
            preferences.lastEntryCachedDate = LocalDateTime.now()
        }
    }
}