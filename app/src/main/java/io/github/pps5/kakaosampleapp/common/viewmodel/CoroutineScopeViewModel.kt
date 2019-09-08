package io.github.pps5.kakaosampleapp.common.viewmodel

import androidx.lifecycle.ViewModel
import io.github.pps5.kakaosampleapp.common.coroutines.AppDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class CoroutineScopeViewModel : ViewModel(), CoroutineScope, KoinComponent {

    private val job: Job = Job()
    private val appDispatchers: AppDispatchers by inject()

    override val coroutineContext = appDispatchers.main + job

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}