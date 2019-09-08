package io.github.pps5.kakaosampleapp.data.converter

import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

class ZonedDateTimeConverter : com.tickaroo.tikxml.TypeConverter<ZonedDateTime> {

    companion object {
        private const val ZONED_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssxxxxx"
    }

    private val formatter = DateTimeFormatter.ofPattern(ZONED_DATE_TIME_FORMAT)

    @com.squareup.moshi.ToJson
    @androidx.room.TypeConverter
    override fun write(value: ZonedDateTime?): String = formatter.format(value)

    @com.squareup.moshi.FromJson
    @androidx.room.TypeConverter
    override fun read(value: String?): ZonedDateTime = ZonedDateTime.parse(value, formatter)
}