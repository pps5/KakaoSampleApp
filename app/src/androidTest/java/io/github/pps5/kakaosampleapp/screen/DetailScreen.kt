package io.github.pps5.kakaosampleapp.screen

import androidx.test.espresso.web.webdriver.Locator
import com.agoda.kakao.text.KButton
import com.agoda.kakao.web.KWebView
import io.github.pps5.kakaosampleapp.R
import io.github.pps5.kakaosampleapp.screen.utils.WebElement

class DetailScreen : BaseScreen<DetailScreen>() {

    val openEventPageButton = KButton { withText(R.string.open_event_page) }
    val detailWebView = KWebView { withId(R.id.detail_webview) }

    val eventTitle = WebElement(detailWebView, Locator.TAG_NAME, "h1")
    val summary = WebElement(detailWebView, Locator.TAG_NAME, "h2")

    init {
        detailWebView { view.forceJavascriptEnabled() }
    }
}
