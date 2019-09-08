package io.github.pps5.kakaosampleapp.scenario

import androidx.test.ext.junit.rules.activityScenarioRule
import io.github.pps5.kakaosampleapp.common.MainActivity
import io.github.pps5.kakaosampleapp.di.IS_SUCCESS_SEARCH
import io.github.pps5.kakaosampleapp.di.dataStoreModule
import io.github.pps5.kakaosampleapp.di.dispatchersModule
import io.github.pps5.kakaosampleapp.screen.DetailScreen
import io.github.pps5.kakaosampleapp.screen.NewArrivalsScreen
import io.github.pps5.kakaosampleapp.screen.SearchScreen
import io.github.pps5.kakaosampleapp.screen.utils.assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.test.KoinTest

class SearchScenario : KoinTest {

    @get:Rule
    val activityScenario = activityScenarioRule<MainActivity>()

    @Before
    fun setUp() = loadKoinModules(listOf(dataStoreModule, dispatchersModule))

    @Test
    fun searchFromNewArrivals() {
        loadKoinModules(module(override = true) {
            factory(named(IS_SUCCESS_SEARCH)) { true }
        })

        NewArrivalsScreen()
            .assert { searchButton.isDisplayed() }
            .clickSearch()
            .searchEvent("sample query")

        SearchScreen()
            .assert {
                resultRecycler {
                    firstChild<SearchScreen.ResultItem> { title.hasText("No.0 Title") }
                }
            }
            .clickFirstItem()

        DetailScreen()
            .assert { openEventPageButton { isCompletelyDisplayed() } }
            .assert { eventTitle { containsText("No.0 Title") } }
            .assert { summary { containsText("概要") } }
            .navigateUpTo<SearchScreen>()
    }
}