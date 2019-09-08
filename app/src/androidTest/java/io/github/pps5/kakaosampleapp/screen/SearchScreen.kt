package io.github.pps5.kakaosampleapp.screen

import android.view.View
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.text.KTextView
import io.github.pps5.kakaosampleapp.R
import org.hamcrest.Matcher

class SearchScreen : BaseScreen<SearchScreen>() {

    val resultRecycler = KRecyclerView(
        { withId(R.id.search_result_recycler) },
        itemTypeBuilder = { itemType(::ResultItem) }
    )

    fun clickFirstItem(): DetailScreen {
        resultRecycler.firstChild<ResultItem> { click() }
        return DetailScreen()
    }

    class ResultItem(parent: Matcher<View>) : KRecyclerItem<ResultItem>(parent) {
        val title = KTextView(parent) { withId(R.id.title) }
    }
}