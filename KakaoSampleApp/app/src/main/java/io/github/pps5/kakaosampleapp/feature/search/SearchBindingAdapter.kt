package io.github.pps5.kakaosampleapp.feature.search

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.pps5.kakaosampleapp.common.vo.Resource
import io.github.pps5.kakaosampleapp.data.entity.Event

@BindingAdapter("events")
fun RecyclerView.setEvents(events: Resource<List<Event>>?) {
    if (this.adapter !is SearchResultAdapter) {
        throw IllegalStateException("${this::class.simpleName} should be set adapter of SearchResultsAdapter")
    }
    if (events?.status != Resource.Status.Success || events.value.isNullOrEmpty()) {
        return
    }
    (this.adapter as SearchResultAdapter).setEvents(events.value)
}

@BindingAdapter("errorMessage")
fun TextView.setErrorMessage(resourceId: Int?) {
    resourceId?.let { this.text = context.getString(it) }
}