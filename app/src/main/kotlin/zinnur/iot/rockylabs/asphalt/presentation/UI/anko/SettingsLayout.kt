package zinnur.iot.rockylabs.asphalt.presentation.UI.anko

import android.os.Build
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
                fitsSystemWindows = true
                lparams(width = matchParent, height = matchParent) {
                    topMargin = actionBarSize()
                    padding = dimen(R.dimen.activity_horizontal_margin)
                }
                clipToPadding = false

                verticalLayout {
                    elevation = 10f
                    backgroundResource = R.color.colorPrimary
                    lparams(width = matchParent, height = wrapContent){
                        padding = dip(16)
                    }
                    ui.logout = textView {
                        backgroundResource = R.color.colorPrimary
                        lparams(width = matchParent, height = wrapContent){
                        }
                        text = "Logout"
                        setFont("fonts/RobotoLight.ttf")
                        textColorResource = R.color.white
                        textSize = sp(6).toFloat()
                        onClick {
                            ui.openLogoutDialog()
                        }
                    }

                }.lparams(width = matchParent, height = wrapContent) {
                    gravity = Gravity.TOP
                }

            }
        }.view
    }

    override fun unbind(t: SettingsController) {
    }

}