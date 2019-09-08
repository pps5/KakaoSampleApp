package io.github.pps5.kakaosampleapp.feature.newarrivals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.pps5.kakaosampleapp.common.util.toHtmlSpanned
import io.github.pps5.kakaosampleapp.data.entity.Entry
import io.github.pps5.kakaosampleapp.databinding.NewArrivalItemBinding

class NewArrivalsAdapter (
    private val onClickItemListener: OnClickItemListener
): RecyclerView.Adapter<NewArrivalsAdapter.ViewHolder>() {

    private val newArrivals = mutableListOf<Entry>()

    fun setNewArrivals(list: List<Entry>) {
        newArrivals.clear()
        newArrivals.addAll(list.map { it.copy(title = it.title.toHtmlSpanned()) })
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NewArrivalItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = newArrivals.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.entry = newArrivals[position]
        holder.binding.listener = onClickItemListener
    }

    class ViewHolder(val binding: NewArrivalItemBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnClickItemListener {
        fun onClick(entry: Entry)
    }
}

