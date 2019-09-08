package io.github.pps5.kakaosampleapp.di

import android.os.AsyncTask
import io.github.pps5.kakaosampleapp.common.coroutines.AppDispatchers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import org.koin.dsl.module

val dispatchersModule = module(override = true) {
    single {
        AppDispatchers(
            main = Dispatchers.Main,
            io = AsyncTask.THREAD_POOL_EXECUTOR.asCoroutineDispatcher()
        )
    }
}