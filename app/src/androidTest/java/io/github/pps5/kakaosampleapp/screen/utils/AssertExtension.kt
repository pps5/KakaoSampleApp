package io.github.pps5.kakaosampleapp.screen.utils

import io.github.pps5.kakaosampleapp.screen.BaseScreen

fun <T: BaseScreen<T>> T.assert(block: T.() -> Unit) = apply {
    block()
}