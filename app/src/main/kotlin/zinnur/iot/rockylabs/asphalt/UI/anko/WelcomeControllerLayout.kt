package zinnur.iot.rockylabs.asphalt.UI.anko

/**
 * Created by Zinnur on 12.01.17.
 */

import android.support.design.R.id.center
import android.support.design.R.id.center_horizontal
import zinnur.iot.rockylabs.asphalt.UI.controllers.WelcomeController
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import org.jetbrains.anko.*
import zinnur.iot.rockylabs.asphalt.navigator
import kotlin.ru.rockylabs.kotlintest.R

class WelcomeControllerLayout : ViewBinder<WelcomeController> {

    override fun bind(ui: WelcomeController): View {
        return ui.activity!!.UI {
            ui.activity!!.window.setBackgroundDrawableResource(R.drawable.wellcome_bg)
            ui.background = frameLayout {
                fitsSystemWindows = false
                lparams(width = matchParent, height = matchParent) {
                    padding = dimen(R.dimen.activity_horizontal_margin)
                }


                verticalLayout {
                    ui.logo = textView {
                        lparams(width = wrapContent, height = wrapContent){
                            gravity = Gravity.CENTER
                        }
                        text = "ASPHALT"
                        setFont("fonts/Ailerons.ttf")
                        textColorResource = R.color.white
                        textSize = sp(36).toFloat()
                    }
                    ui.slogan = textView{
                        lparams(width = wrapContent, height = wrapContent){
                            gravity = Gravity.CENTER
                        }
                        text = "Make asphalt great again"
                        textColorResource = R.color.grey
                        setFont("fonts/RobotoLight.ttf")
                        textAlignment = center
                        textSize = sp(8).toFloat()
                    }
                }.lparams(width = matchParent, height = wrapContent) {
                    gravity = Gravity.CENTER
                }


                ui.container = verticalLayout {



                    ui.signIn = button {
                        lparams (width = matchParent, height = heightProcent(10) ){
                            gravity = Gravity.CENTER_HORIZONTAL
                            bottomMargin = heightProcent(1)

                        }
                        text = "Sign In"
                        setFont("fonts/RobotoLight.ttf")
                        textSize = sp(6).toFloat()
                        backgroundResource = R.drawable.welcome_btn
                        onClick { ui.navigator.showSignIn()}

                    }

                    ui.signUp =  button {
                        lparams (width = matchParent, height = heightProcent(10) ){
                            gravity = Gravity.CENTER_HORIZONTAL
                            bottomMargin = heightProcent(1)
                        }
                        text = "Sign Up"
                        setFont("fonts/RobotoLight.ttf")
                        textSize = sp(6).toFloat()
                        backgroundResource = R.drawable.welcome_btn
                        onClick { ui.navigator.showSignUp()}

                    }




                }.lparams(width = matchParent, height = wrapContent) {
                    gravity = Gravity.BOTTOM or Gravity.END

                }

            }
        }.view
    }

    override fun unbind(t: WelcomeController) {
        t.background = null
        t.container = null
        t.logo = null
        t.signIn = null
        t.signUp = null
        t.slogan = null
    }

}


