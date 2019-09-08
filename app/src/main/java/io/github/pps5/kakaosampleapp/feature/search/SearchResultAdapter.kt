package io.github.pps5.kakaosampleapp.feature.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.pps5.kakaosampleapp.data.entity.Event
import io.github.pps5.kakaosampleapp.databinding.EventItemBinding

class SearchResultAdapter(
    private val onClickEventListener: OnClickEventListener
) : RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {

    private val events = mutableListOf<Event>()

    fun setEvents(events: List<Event>) {
        this.events.addAll(events)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(EventItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun getItemCount() = events.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.event = events[position]
        holder.binding.listener = onClickEventListener
    }

    class ViewHolder(val binding: EventItemBinding): RecyclerView.ViewHolder(binding.root)

    interface OnClickEventListener {
        fun onClick(event: Event)
    }
}