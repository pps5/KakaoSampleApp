package io.github.pps5.kakaosampleapp

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import io.github.pps5.kakaosampleapp.di.dataStoreModule
import io.github.pps5.kakaosampleapp.di.dispatchersModule
import io.github.pps5.kakaosampleapp.di.repositoryModule
import io.github.pps5.kakaosampleapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        startKoin {
            androidContext(this@App)
            modules(listOf(
                dataStoreModule, repositoryModule, viewModelModule, dispatchersModule
            ))
        }
    }
}