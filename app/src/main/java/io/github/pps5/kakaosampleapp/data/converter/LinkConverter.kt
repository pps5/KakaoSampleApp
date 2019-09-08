package io.github.pps5.kakaosampleapp.data.converter

import androidx.room.TypeConverter
import io.github.pps5.kakaosampleapp.data.entity.Link

class LinkConverter {

    @TypeConverter
    fun fromString(value: String?): Link? = value?.let { Link(it) }

    @TypeConverter
    fun linkToString(link: Link?): String? = link?.url
}