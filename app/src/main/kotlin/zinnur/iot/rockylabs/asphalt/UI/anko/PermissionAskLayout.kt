package zinnur.iot.rockylabs.asphalt.UI.anko

import android.view.Gravity
import android.view.View
import org.jetbrains.anko.*
import zinnur.iot.rockylabs.asphalt.UI.controllers.NoticePermissonsController
import kotlin.ru.rockylabs.kotlintest.R

/**
 * Created by Zinnur on 16.02.17.
 */

class PermissionAskLayout : ViewBinder<NoticePermissonsController>{
    override fun bind(ui: NoticePermissonsController): View {
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
                        text = "hello"
                        setFont("fonts/RobotoLight.ttf")
                        textColorResource = R.color.white
                        textSize = sp(36).toFloat()
                    }

                }.lparams(width = matchParent, height = wrapContent) {
                    gravity = Gravity.CENTER
                }

            }
        }.view
    }

    override fun unbind(t: NoticePermissonsController) {
    }

}