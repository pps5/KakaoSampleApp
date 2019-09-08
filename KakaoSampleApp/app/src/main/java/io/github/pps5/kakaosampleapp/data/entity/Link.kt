package io.github.pps5.kakaosampleapp.data.entity

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Xml

@Xml
data class Link (
    @Attribute(name = "href")
    val url: String
)