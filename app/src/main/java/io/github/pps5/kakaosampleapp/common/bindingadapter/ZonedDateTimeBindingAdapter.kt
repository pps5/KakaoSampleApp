package io.github.pps5.kakaosampleapp.common.bindingadapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

private const val PUBLISHED_DATE_TIME_FORMAT = "yyyy年M月d日"
private val formatter = DateTimeFormatter.ofPattern(PUBLISHED_DATE_TIME_FORMAT)

@BindingAdapter(value = ["date"])
fun TextView.setZonedDateTime(published: ZonedDateTime) {
    this.text = formatter.format(published)
}