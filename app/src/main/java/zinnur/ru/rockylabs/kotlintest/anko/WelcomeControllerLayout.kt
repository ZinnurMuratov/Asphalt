package zinnur.ru.rockylabs.kotlintest.anko

/**
 * Created by Zinnur on 12.01.17.
 */

import zinnur.ru.rockylabs.kotlintest.WelcomeController
import android.view.Gravity
import android.view.View
import org.jetbrains.anko.*
import kotlin.ru.rockylabs.kotlintest.R

class WelcomeControllerLayout : ViewBInder<WelcomeController> {

    override fun bind(t: WelcomeController): View {
        return t.activity!!.UI {
            t.background = verticalLayout {
                lparams(width = matchParent, height = matchParent) {
                    padding = dimen(R.dimen.activity_horizontal_margin)
                    backgroundResource = R.color.colorPrimary
                }


                t. actionText = textView {
                    gravity = Gravity.START
                    text = "hello world"
                }.lparams(width = wrapContent, height = 0, weight = 4f)
            }
        }.view
    }

    override fun unbind(t: WelcomeController) {
        t.background = null
        t.actionText = null
    }

}
