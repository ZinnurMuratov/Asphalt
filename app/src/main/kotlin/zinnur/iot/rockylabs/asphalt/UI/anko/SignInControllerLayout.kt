package zinnur.iot.rockylabs.asphalt.UI.anko

import android.animation.LayoutTransition
import android.graphics.Typeface
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.view.Gravity
import android.view.View
import org.jetbrains.anko.*
import zinnur.iot.rockylabs.asphalt.UI.controllers.SignInController
import kotlin.ru.rockylabs.kotlintest.R

/**
 * Created by Zinnur on 13.02.17.
 */

class SignInControllerLayout : ViewBinder<SignInController> {


    override fun bind(ui: SignInController): View {
        return ui.activity!!.UI {
            ui.activity!!.window.setBackgroundDrawableResource(R.drawable.wellcome_bg)
            ui.background = frameLayout {
                lparams(width = matchParent, height = matchParent)
                ui.container = verticalLayout {
                    backgroundResource = R.color.greyOpacity
                    padding = dip(16)

                    ui.headText = textView{
                        lparams(width = wrapContent, height = wrapContent){
                            gravity = Gravity.CENTER
                            bottomMargin = dip(10)
                        }
                        text = "Sign In"
                        setFont("fonts/RobotoLight.ttf")
                        textColorResource = R.color.white
                        textSize = sp(12).toFloat()
                    }

                    ui.email = editText {
                        lparams(width = matchParent, height = wrapContent){
                            bottomMargin = dip(2)
                        }
                        hint = "email"
                        setFont("fonts/RobotoLight.ttf")
                        inputType =  InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                        textSize = sp(8).toFloat()
                    }

                    ui.password = editText {
                        lparams(width = matchParent, height = wrapContent){
                            bottomMargin = dip(6)
                            setFont("fonts/RobotoLight.ttf")
                        }
                        hint = "password"
                        inputType =  InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD
                        transformationMethod = PasswordTransformationMethod()
                        textSize = sp(8).toFloat()
                    }

                    ui.signIn = button {
                        lparams (width = matchParent, height = heightProcent(10) ){
                            gravity = Gravity.CENTER_HORIZONTAL
                            bottomMargin = heightProcent(1)
                        }
                        text = "go"
                        setFont("fonts/RobotoLight.ttf")
                        textSize = sp(6).toFloat()
                        backgroundResource = R.drawable.regular_btn
                        onClick { ui.onLoginClicked()}

                    }


                    ui.errorView = textView {
                        lparams(width = wrapContent, height = wrapContent){
                            gravity = Gravity.CENTER
                            bottomMargin = dip(6)
                        }
                        text = "bad email or password"
                        setFont("fonts/RobotoLight.ttf")
                        setTypeface(null, Typeface.BOLD)
                        textColorResource = R.color.redError
                        textSize = sp(8).toFloat()
                        visibility = View.GONE
                    }

                    ui.transition = LayoutTransition()
                    ui.transition!!.enableTransitionType(LayoutTransition.CHANGING)
                    ui.transition!!.setStartDelay(LayoutTransition.APPEARING, resources!!.getInteger(android.R.integer.config_mediumAnimTime).toLong())
                    ui.transition!!.setStartDelay(LayoutTransition.CHANGE_APPEARING, resources!!.getInteger(android.R.integer.config_mediumAnimTime).toLong())
                    layoutTransition = ui.transition

                }.lparams(width = matchParent, height = wrapContent) {
                    gravity = Gravity.CENTER
                    margin = dimen(R.dimen.activity_horizontal_margin)
                }

            }

        }.view
    }

    override fun unbind(t: SignInController) {
        t.background = null
        t.container = null
        t.email = null
        t.password = null
        t.signIn = null
        t.headText = null
        t.transition = null
    }


}