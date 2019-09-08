package io.github.pps5.kakaosampleapp.screen

import android.widget.ImageButton
import com.agoda.kakao.common.views.KView
import com.agoda.kakao.screen.Screen
import io.github.pps5.kakaosampleapp.R


abstract class BaseScreen<T : Screen<T>>: Screen<T>() {

    inline fun <reified S: BaseScreen<S>> navigateUpTo(): S {
        KView {
            isDescendantOfA { withId(R.id.toolbar) }
            isInstanceOf(ImageButton::class.java)
        }.click()
        return S::class.java.newInstance()
    }
}
