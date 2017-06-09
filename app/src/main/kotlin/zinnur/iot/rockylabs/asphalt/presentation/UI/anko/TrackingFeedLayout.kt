package zinnur.iot.rockylabs.asphalt.presentation.UI.anko

import android.view.View
import org.jetbrains.anko.*
import zinnur.iot.rockylabs.asphalt.presentation.UI.controllers.TrackingFeedController

/**
 * Created by Zinnur on 04.06.17.
 */
class TrackingFeedLayout : ViewBinder<TrackingFeedController>{
    override fun bind(ui: TrackingFeedController): View {
        return ui.activity!!.UI {
            frameLayout {
                lparams(width = matchParent, height = matchParent) {
                    topMargin = actionBarSize()
                }
                ui.rv = recycleView {

                    lparams(width = matchParent, height = matchParent){
                        padding = dip(10)
                    }
                }
            }
        }.view
    }

    override fun unbind(t: TrackingFeedController) {
    }

}