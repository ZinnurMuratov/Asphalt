package zinnur.iot.rockylabs.asphalt.presentation.UI.anko

import android.view.View
import org.jetbrains.anko.*
import zinnur.iot.rockylabs.asphalt.presentation.UI.controllers.FeedController
import kotlin.ru.rockylabs.kotlintest.R

/**
 * Created by Zinnur on 11.04.17.
 */

class FeedLayout : ViewBinder<FeedController>{
    override fun bind(ui: FeedController): View {
        return ui.activity!!.UI {
            frameLayout {
                lparams(width = matchParent, height = matchParent) {
                    topMargin = actionBarSize()
                }
                ui.rv = recycleView {
                    lparams(width = matchParent, height = matchParent)
                }
            }
        }.view
    }

    override fun unbind(t: FeedController) {
    }

}