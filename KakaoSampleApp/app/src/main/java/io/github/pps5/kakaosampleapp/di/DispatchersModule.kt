package io.github.pps5.kakaosampleapp.di

import io.github.pps5.kakaosampleapp.common.coroutines.AppDispatchers
import org.koin.dsl.module

val dispatchersModule = module {
    single { AppDispatchers() }
}