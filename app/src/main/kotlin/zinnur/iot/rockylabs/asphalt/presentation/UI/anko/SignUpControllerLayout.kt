package zinnur.iot.rockylabs.asphalt.presentation.UI.anko

import android.animation.LayoutTransition
import android.graphics.Typeface
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.view.Gravity
import android.view.View
import org.jetbrains.anko.*
import zinnur.iot.rockylabs.asphalt.presentation.UI.controllers.SignUpController
import kotlin.ru.rockylabs.kotlintest.R

/**
 * Created by Zinnur on 14.02.17.
 */


class SignUpControllerLayout : ViewBinder<SignUpController>{
    override fun bind(ui: SignUpController): View {
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
                        text = "Sign Up"
                        textColorResource = R.color.white
                        textSizeDimen = R.dimen.font_36sp
                        setFont("fonts/RobotoLight.ttf")
                    }

                    ui.email = editText {
                        lparams(width = matchParent, height = wrapContent){
                            bottomMargin = dip(2)
                        }
                        hint = "email"
                        setFont("fonts/RobotoLight.ttf")
                        inputType =  InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                        textSizeDimen = R.dimen.font_19sp
                    }

                    ui.name = editText {
                        lparams(width = matchParent, height = wrapContent){
                            bottomMargin = dip(2)
                        }
                        hint = "name"
                        setFont("fonts/RobotoLight.ttf")
                        inputType =  InputType.TYPE_CLASS_TEXT
                        textSizeDimen = R.dimen.font_19sp
                    }

                    ui.password = editText {
                        lparams(width = matchParent, height = wrapContent){
                            bottomMargin = dip(6)
                        }
                        hint = "password"
                        setFont("fonts/RobotoLight.ttf")
                        inputType =  InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD
                        transformationMethod = PasswordTransformationMethod()
                        textSizeDimen = R.dimen.font_19sp
                    }

                    ui.repeatPass = editText {
                        lparams(width = matchParent, height = wrapContent){
                            bottomMargin = dip(6)
                        }
                        hint = "confirm password"
                        setFont("fonts/RobotoLight.ttf")
                        inputType =  InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD
                        transformationMethod = PasswordTransformationMethod()
                        textSizeDimen = R.dimen.font_19sp
                    }

                    ui.signUp = button {
                        lparams (width = matchParent, height = heightProcent(10) ){
                            gravity = Gravity.CENTER_HORIZONTAL
                            bottomMargin = heightProcent(1)
                        }
                        text = "go"
                        setFont("fonts/RobotoLight.ttf")
                        textSizeDimen = R.dimen.font_16sp
                        backgroundResource = R.drawable.regular_btn
                        onClick { ui.onSignUpClicked()}

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
                        textSizeDimen = R.dimen.font_16sp
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

    override fun unbind(t: SignUpController) {
        t.background = null
        t.container = null
        t.email = null
        t.name = null
        t.password = null
        t.repeatPass = null
        t.signUp = null
        t.headText = null
        t.transition = null    }

}