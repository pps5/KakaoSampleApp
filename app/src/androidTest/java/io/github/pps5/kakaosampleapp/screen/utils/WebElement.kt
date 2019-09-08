package io.github.pps5.kakaosampleapp.screen.utils

import androidx.test.espresso.web.webdriver.Locator
import com.agoda.kakao.web.KWebView
import com.agoda.kakao.web.WebElementBuilder

class WebElement(
    private val webView: KWebView,
    private val locator: Locator,
    private val value: String
) {
    operator fun invoke(block: (WebElementBuilder.KWebInteraction.() -> Unit)) {
        webView { withElement(locator, value, block)}
    }
}

