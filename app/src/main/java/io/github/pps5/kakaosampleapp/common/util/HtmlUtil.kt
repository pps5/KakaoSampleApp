package io.github.pps5.kakaosampleapp.common.util

import android.os.Build
import android.text.Html

fun String.toHtmlSpanned(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
    } else {
        @Suppress("DEPRECATION")
        Html.fromHtml(this).toString()
    }
}