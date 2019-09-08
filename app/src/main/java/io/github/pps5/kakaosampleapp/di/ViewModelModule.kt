package io.github.pps5.kakaosampleapp.di

import io.github.pps5.kakaosampleapp.feature.newarrivals.NewArrivalViewModel
import io.github.pps5.kakaosampleapp.feature.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { NewArrivalViewModel() }
    viewModel { (query: String) -> SearchViewModel(query) }
}