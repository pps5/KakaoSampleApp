package io.github.pps5.kakaosampleapp.data.converter

import com.tickaroo.tikxml.TypeConverter
import io.github.pps5.kakaosampleapp.common.util.toHtmlSpanned

class EscapedHtmlConverter : TypeConverter<String> {
    override fun write(value: String?) = value

    override fun read(value: String?) = value?.toHtmlSpanned()
}