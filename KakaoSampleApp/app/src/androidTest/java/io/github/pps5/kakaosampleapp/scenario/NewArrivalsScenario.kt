package io.github.pps5.kakaosampleapp.scenario

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.web.assertion.WebViewAssertions.webMatches
import androidx.test.espresso.web.sugar.Web.onWebView
import androidx.test.espresso.web.webdriver.DriverAtoms.findElement
import androidx.test.espresso.web.webdriver.DriverAtoms.getText
import androidx.test.espresso.web.webdriver.Locator
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.agoda.kakao.screen.Screen.Companion.onScreen
import io.github.pps5.kakaosampleapp.R
import io.github.pps5.kakaosampleapp.common.MainActivity
import io.github.pps5.kakaosampleapp.di.IS_SUCCESS_NEW_ARRIVALS
import io.github.pps5.kakaosampleapp.di.dataStoreModule
import io.github.pps5.kakaosampleapp.di.dispatchersModule
import io.github.pps5.kakaosampleapp.feature.newarrivals.NewArrivalsAdapter
import io.github.pps5.kakaosampleapp.screen.DetailScreen
import io.github.pps5.kakaosampleapp.screen.NewArrivalsScreen
import io.github.pps5.kakaosampleapp.screen.NewArrivalsScreen.NewArrivalItem
import io.github.pps5.kakaosampleapp.screen.utils.assert
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
class NewArrivalsScenario : KoinTest {

    @get:Rule
    val activityScenario = activityScenarioRule<MainActivity>()

    @Before
    fun setUp() = loadKoinModules(listOf(dataStoreModule, dispatchersModule))

    @Test
    fun showDetailFromNewArrivals() {
        loadKoinModules(module(override = true) {
            factory(named(IS_SUCCESS_NEW_ARRIVALS)) { true }
        })

        NewArrivalsScreen()
            .assert { errorMessage { isNotDisplayed() } }
            .assert {
                newArrivalsList {
                    firstChild<NewArrivalItem> { title.hasText("No.0 Title") }
                    lastChild<NewArrivalItem> { title.hasText("No.19 Title") }
                }
            }
            .clickFirstItem()

        DetailScreen()
            .assert { openEventPageButton { isCompletelyDisplayed() } }
            .assert { eventTitle { containsText("No.0 Title") } }
            .assert { summary { containsText("概要") } }
            .navigateUpTo<NewArrivalsScreen>()

        NewArrivalsScreen()
            .assert { errorMessage { isNotDisplayed() } }
    }

    @Test
    fun showDetailFromNewArrivals_espresso() {
        loadKoinModules(module(override = true) {
            factory(named(IS_SUCCESS_NEW_ARRIVALS)) { true }
        })

        // エラーメッセージが表示されていないことを確認
        onView(withText(R.string.loading_error)).check(matches(not(isDisplayed())))

        onView(withId(R.id.new_arrivals_recycler)).let { recyclerView ->
            // はじめのアイテムのタイトルが No.0 Title であることを確認
            recyclerView.perform(scrollToPosition<NewArrivalsAdapter.ViewHolder>(0))
            onView(withText("No.0 Title")).check(matches(isDisplayed()))

            // 最後のアイテムのタイトルが No.19 Title であることを確認
            recyclerView.perform(scrollToPosition<NewArrivalsAdapter.ViewHolder>(19))
            onView(withText("No.19 Title")).check(matches(isDisplayed()))

            // はじめのアイテムをクリック
            recyclerView.perform(
                actionOnItemAtPosition<NewArrivalsAdapter.ViewHolder>(0, click())
            )
        }

        // イベントページを開くボタンが完全に表示されていることを確認
        onView(withText(R.string.open_event_page)).check(matches(isCompletelyDisplayed()))

        // 詳細画面のWebViewで "No.0 Title" が表示されていることを確認
        onWebView(withId(R.id.detail_webview))
            .forceJavascriptEnabled()
            .withElement(findElement(Locator.TAG_NAME, "h1"))
            .check(webMatches(getText(), containsString("No.0 Title")))

        // ツールバーから前の画面に遷移
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())

        // エラーメッセージが表示されていないことを確認
        onView(withText(R.string.loading_error)).check(matches(not(isDisplayed())))
    }

    @Test
    fun showErrorOnFetchFailure() {
        loadKoinModules(module(override = true) {
            factory(named(IS_SUCCESS_NEW_ARRIVALS)) { false }
        })
        onScreen<NewArrivalsScreen> { errorMessage.isDisplayed() }
    }
}
