package zinnur.iot.rockylabs.asphalt.UI.anko

/**
 * Created by Zinnur on 12.01.17.
 */

import zinnur.iot.rockylabs.asphalt.UI.controllers.WelcomeController
import android.view.Gravity
import android.view.View
import org.jetbrains.anko.*
import kotlin.ru.rockylabs.kotlintest.R

class WelcomeControllerLayout : ViewBinder<WelcomeController> {

    override fun bind(t: WelcomeController): View {
        return t.activity!!.UI {
            t.background = verticalLayout {
                lparams(width = matchParent, height = matchParent) {
                    padding = dimen(R.dimen.activity_horizontal_margin)
                    backgroundResource = R.drawable.wellcome_bg
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
