package io.github.pps5.kakaosampleapp.data.entity

import com.squareup.moshi.Json
import org.threeten.bp.ZonedDateTime

data class SearchResponse(
    @Json(name = "results_returned") val resultsReturned: Int,
    @Json(name = "results_available") val resultsAvailable: Int,
    @Json(name = "events") val events: List<Event>
)

data class Event(
    @Json(name = "event_id") val eventId: Int,
    @Json(name = "title") val title: String,
    @Json(name = "catch") val catch: String,
    @Json(name = "description") val description: String,
    @Json(name = "event_url") val eventUrl: String,
    @Json(name = "hash_tag") val hashTag: String,
    @Json(name = "started_at") val startedAt: ZonedDateTime,
    @Json(name = "ended_at") val endedAt: ZonedDateTime
)