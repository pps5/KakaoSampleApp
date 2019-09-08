package io.github.pps5.kakaosampleapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import io.github.pps5.kakaosampleapp.data.converter.EscapedHtmlConverter
import io.github.pps5.kakaosampleapp.data.converter.ZonedDateTimeConverter
import org.threeten.bp.ZonedDateTime

@Xml
@Entity
data class Entry(
    @PropertyElement(name = "id")
    @PrimaryKey(autoGenerate = false)
    var id: String,

    @PropertyElement(name = "summary", converter = EscapedHtmlConverter::class)
    var summary: String,

    @PropertyElement(name = "title")
    var title: String,

    @Element(name = "link")
    var link: Link,

    @PropertyElement(name = "published", converter = ZonedDateTimeConverter::class)
    var published: ZonedDateTime,

    @PropertyElement(name = "updated", converter = ZonedDateTimeConverter::class)
    var updated: ZonedDateTime
)