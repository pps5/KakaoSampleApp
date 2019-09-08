package io.github.pps5.kakaosampleapp.common.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.get

interface HasDispatchers : KoinComponent {

    suspend fun <T> withIOContext(block: suspend CoroutineScope.() -> T) =
        withContext(get<AppDispatchers>().io) {
            block()
        }

}