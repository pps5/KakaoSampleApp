package io.github.pps5.kakaosampleapp.feature.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.pps5.kakaosampleapp.R
import io.github.pps5.kakaosampleapp.common.viewmodel.CoroutineScopeViewModel
import io.github.pps5.kakaosampleapp.common.vo.Resource
import io.github.pps5.kakaosampleapp.data.entity.Event
import io.github.pps5.kakaosampleapp.data.repository.ConnpassRepository
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class SearchViewModel(private val query: String) : CoroutineScopeViewModel(), KoinComponent {

    private val repository: ConnpassRepository by inject { parametersOf(this) }

    private val _events = MutableLiveData<Resource<List<Event>>>()
    val events: LiveData<Resource<List<Event>>>
        get() = _events

    private val _errorMessage = MutableLiveData<Int>()
    val errorMessage: LiveData<Int>
        get() = _errorMessage

    init {
        _events.postValue(Resource.loading())
        launch {
            val results = repository.search(query)
            when (results?.resultsReturned) {
                null -> {
                    _events.value = Resource.failure()
                    _errorMessage.value = R.string.loading_error
                }
                0 -> {
                    _errorMessage.value = R.string.no_event_found
                    _events.value = Resource.failure()
                }
                else -> _events.value = Resource.success(results.events)
            }
        }
    }
}