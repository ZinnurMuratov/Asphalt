package zinnur.iot.rockylabs.asphalt.presentation.UI.anko

import android.view.Gravity
import android.view.View
import org.jetbrains.anko.*
import zinnur.iot.rockylabs.asphalt.presentation.UI.controllers.SettingsController
import kotlin.ru.rockylabs.kotlintest.R

/**
 * Created by Zinnur on 05.04.17.
 */

class SettingsLayout : ViewBinder<SettingsController>{
    override fun bind(ui: SettingsController): View {
        return ui.activity!!.UI {
            ui.activity!!.window.setBackgroundDrawableResource(R.color.colorPrimary)
            frameLayout {
                lparams(width = matchParent, height = matchParent) {
                    padding = dimen(R.dimen.activity_horizontal_margin)
                }
//                clipToPadding = false

                verticalLayout {
                    lparams(width = matchParent, height = wrapContent){
                        gravity = Gravity.TOP
//                        padding = dimen(16)
//                        elevation = dip(16).toFloat()
                    }
                    ui.logout = textView {
                        lparams(width = wrapContent, height = wrapContent){
                            gravity = Gravity.CENTER_VERTICAL
                        }
                        text = "logout"
                        setFont("fonts/RobotoLight.ttf")
                        textColorResource = R.color.white
                        textSize = sp(16).toFloat()
                        onClick {
                            ui.openLogoutDialog()
                        }
                    }

                }.lparams(width = matchParent, height = wrapContent) {
                    gravity = Gravity.CENTER
                }

            }
        }.view
    }

    override fun unbind(t: SettingsController) {
    }

}