package zinnur.iot.rockylabs.asphalt.presentation.UI.anko

import android.view.Gravity
import android.view.View
import org.jetbrains.anko.*
import zinnur.iot.rockylabs.asphalt.presentation.UI.controllers.OnMapController
import zinnur.iot.rockylabs.asphalt.presentation.UI.controllers.TrackController
import kotlin.ru.rockylabs.kotlintest.R

/**
 * Created by Zinnur on 07.06.17.
 */
class TrackLayout : ViewBinder<TrackController>{
    override fun bind(ui: TrackController): View {
        return ui.activity!!.UI {
            frameLayout {
                lparams(width = matchParent, height = matchParent) {
                }
                ui.mapView = mapView {
                    lparams(width = matchParent, height = matchParent){
                    }
                    tag = "mapView"

                }
                verticalLayout {
                    lparams(width = matchParent, height = dip(58)) {
                        backgroundResource = R.drawable.map_overlay_gradient
                        gravity = Gravity.TOP
                    }
                }

            }
        }.view
    }

    override fun unbind(t: TrackController) {
//        t.mapView = null
    }

}