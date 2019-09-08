package io.github.pps5.kakaosampleapp.screen

import android.view.View
import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.text.KTextView
import io.github.pps5.kakaosampleapp.R
import org.hamcrest.Matcher

class NewArrivalsScreen : BaseScreen<NewArrivalsScreen>() {

    val errorMessage = KTextView { withText(R.string.loading_error) }
    val newArrivalsList = KRecyclerView(
        { withId(R.id.new_arrivals_recycler) },
        itemTypeBuilder = { itemType(::NewArrivalItem) }
    )

    val searchButton = KImageView { withId(R.id.search_button) }
    private val searchTextBox = KEditText { withId(R.id.search_src_text) }

    class NewArrivalItem(parent: Matcher<View>) : KRecyclerItem<NewArrivalItem>(parent) {
        val title = KTextView(parent) { withId(R.id.title) }
    }

    fun clickSearch() = apply {
        searchButton.click()
    }

    fun searchEvent(query: String) {
        searchTextBox.replaceText(query)
        searchTextBox.pressImeAction()
    }


    fun clickFirstItem(): DetailScreen {
        newArrivalsList.firstChild<NewArrivalItem> { click() }
        return DetailScreen()
    }

}
