package io.github.pps5.kakaosampleapp.di

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import io.github.pps5.kakaosampleapp.data.store.AppDatabase
import io.github.pps5.kakaosampleapp.data.store.ConnpassService
import io.github.pps5.kakaosampleapp.mockdata.createNewArrivalsSuccessResponse
import io.github.pps5.kakaosampleapp.mockdata.createSearchSuccessResponse
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.io.IOException

@Suppress("DeferredIsResult")
private fun <T> T.toDeferred() = GlobalScope.async { this@toDeferred }

const val IS_SUCCESS_NEW_ARRIVALS = "mock_new_arrivals"
const val IS_SUCCESS_SEARCH = "mock_search"

val dataStoreModule = module(override = true) {

    factory(named(IS_SUCCESS_NEW_ARRIVALS)) { true }

    single {
        val connpassService = mockk<ConnpassService>()
        every { connpassService.getNewArrivalsAsync() }
            .answers {
                if (get(named(IS_SUCCESS_NEW_ARRIVALS))) {
                    createNewArrivalsSuccessResponse().toDeferred()
                } else {
                    throw IOException()
                }
            }

        every { connpassService.searchAsync(any()) }
            .answers {
                if (get(named(IS_SUCCESS_SEARCH))) {
                    createSearchSuccessResponse().toDeferred()
                } else {
                    throw IOException()
                }
            }
        connpassService
    }

    single {
        ApplicationProvider.getApplicationContext<Context>().let {
            Room.inMemoryDatabaseBuilder(it, AppDatabase::class.java)
                .build()
        }
    }

}