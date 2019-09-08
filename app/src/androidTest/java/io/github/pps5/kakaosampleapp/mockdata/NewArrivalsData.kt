package io.github.pps5.kakaosampleapp.mockdata

import io.github.pps5.kakaosampleapp.data.entity.Entry
import io.github.pps5.kakaosampleapp.data.entity.Feed
import io.github.pps5.kakaosampleapp.data.entity.Link
import org.threeten.bp.ZonedDateTime

private val successEntries =
    (0 until 20)
        .map {
            Entry(
                id = "https://google.com/q=$it",
                title = "No.$it Title",
                link = Link("https://google.com/q=$it"),
                published = ZonedDateTime.now().minusHours(it * 2L),
                updated = ZonedDateTime.now().minusHours(it.toLong()),
                summary = """
                    開催日時: 2019/7/$it 16:$it ~ 17:$it<br>
                    開催場所: Kakao World<br>
                    <br>

                    <h2>概要</h2>
                    アプリのUIテストは実装コストも保守コストも非常に高く、導入に踏み切るのは難しいものがあります。<br>
                    今回はKakaoとPage Object Patternを用いて、コスト削減を図ります。<br>
                """.trimIndent()
            )
        }

fun createNewArrivalsSuccessResponse() =
    Feed(
        entry = successEntries,
        updated = ZonedDateTime.now().toString()
    )
