package io.github.pps5.kakaosampleapp.data.extension

import android.content.SharedPreferences
import org.threeten.bp.LocalDateTime

private const val LAST_ENTRY_CACHED_DATE = "last_entry_cached_date"

var SharedPreferences.lastEntryCachedDate: LocalDateTime
    get() = when (val cachedDateString = getString(LAST_ENTRY_CACHED_DATE, null)) {
        null -> LocalDateTime.MIN
        else -> LocalDateTime.parse(cachedDateString)
    }
    set(value) = edit()
        .putString(LAST_ENTRY_CACHED_DATE, value.toString())
        .apply()
