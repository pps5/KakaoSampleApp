package io.github.pps5.kakaosampleapp.data.entity

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "feed")
class Feed(
    @Element(name = "entry")
    val entry: List<Entry>,
    @PropertyElement(name = "updated")
    val updated: String
)