package io.github.pps5.kakaosampleapp.mockdata

import io.github.pps5.kakaosampleapp.data.entity.Event
import io.github.pps5.kakaosampleapp.data.entity.SearchResponse
import org.threeten.bp.ZonedDateTime

private val events = (0 until 20)
    .map {
        Event(
            eventId = it,
            title = "No.$it Title",
            catch = "サンプルイベントです",
            description = """
                    開催日時: 2019/7/$it 16:$it ~ 17:$it<br>
                    開催場所: Kakao World<br>
                    <br>

                    <h2>概要</h2>
                    アプリのUIテストは実装コストも保守コストも非常に高く、導入に踏み切るのは難しいものがあります。<br>
                    今回はKakaoとPage Object Patternを用いて、コスト削減を図ります。<br>
                """.trimIndent(),
            eventUrl = "https://google.com/q=$it",
            hashTag = "Kakao,UI tesiting,Page Object Pattern",
            startedAt = ZonedDateTime.now().minusHours(it * 2L),
            endedAt = ZonedDateTime.now().minusHours(it.toLong())
        )
    }

fun createSearchSuccessResponse() =
    SearchResponse(
        resultsReturned = events.size,
        resultsAvailable = 200,
        events = events
    )


