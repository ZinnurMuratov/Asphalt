package zinnur.iot.rockylabs.asphalt.UI.anko

import android.view.Gravity
import android.view.View
import org.jetbrains.anko.*
import zinnur.iot.rockylabs.asphalt.UI.controllers.TrackingController
import kotlin.ru.rockylabs.kotlintest.R

/**
 * Created by Zinnur on 15.02.17.
 */

class TrackingControllerLayout : ViewBinder<TrackingController>{
    override fun bind(ui: TrackingController): View {
        return ui.activity!!.UI {
            ui.activity!!.window.setBackgroundDrawableResource(R.color.colorPrimary)
            frameLayout {
                lparams(width = matchParent, height = matchParent) {
                    padding = dimen(R.dimen.activity_horizontal_margin)
                }


                verticalLayout {
                    textView {
                        lparams(width = wrapContent, height = wrapContent){
                            gravity = Gravity.CENTER
                        }
                        text = "hello."
                        setFont("fonts/fThin.ttf")
                        textColorResource = R.color.white
                        textSize = sp(36).toFloat()
                    }

                }.lparams(width = matchParent, height = wrapContent) {
                    gravity = Gravity.CENTER
                }

            }
        }.view
    }

    override fun unbind(t: TrackingController) {
    }

}