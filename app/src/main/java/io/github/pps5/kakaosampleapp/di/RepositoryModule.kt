package io.github.pps5.kakaosampleapp.di

import io.github.pps5.kakaosampleapp.data.repository.ConnpassRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { ConnpassRepository() }
}