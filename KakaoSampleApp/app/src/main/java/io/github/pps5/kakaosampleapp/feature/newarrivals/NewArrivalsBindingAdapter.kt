package io.github.pps5.kakaosampleapp.feature.newarrivals

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.pps5.kakaosampleapp.common.vo.Resource
import io.github.pps5.kakaosampleapp.data.entity.Entry

@BindingAdapter(value = ["newArrivals"])
fun RecyclerView.setNewArrivals(newArrivals: Resource<List<Entry>>?) {
    if (this.adapter !is NewArrivalsAdapter) {
        throw IllegalStateException("${this::class.simpleName} should be set adapter of NewArrivalsAdapter")
    }
    if (newArrivals?.status != Resource.Status.Success || newArrivals.value.isNullOrEmpty()) {
        return
    }
    (this.adapter as NewArrivalsAdapter).setNewArrivals(newArrivals.value)
}