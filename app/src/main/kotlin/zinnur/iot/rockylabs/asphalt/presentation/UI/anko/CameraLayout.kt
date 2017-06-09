package zinnur.iot.rockylabs.asphalt.presentation.UI.anko

import android.view.Gravity
import android.view.View
import android.widget.ImageView
import org.jetbrains.anko.*
import zinnur.iot.rockylabs.asphalt.presentation.UI.controllers.CameraController
import kotlin.ru.rockylabs.kotlintest.R

/**
 * Created by Zinnur on 16.02.17.
 */

class CameraLayout : ViewBinder<CameraController>{
    override fun bind(ui: CameraController): View {
        return ui.activity!!.UI {
            frameLayout {
                lparams(width = matchParent, height = matchParent) {
                }


                ui.locationIsNotAvailableView = verticalLayout {
                    visibility = View.GONE
                    textView {
                        lparams(width = wrapContent, height = wrapContent){
                            gravity = Gravity.CENTER
                        }
                        text = "Location is not available! \n" +
                                "please on location"
                        setFont("fonts/RobotoLight.ttf")
                        textColorResource = R.color.white
                        textSize = sp(8).toFloat()
                    }
                }.lparams(width = matchParent, height = wrapContent) {
                    gravity = Gravity.CENTER
                }


                ui.cameraIsNotAvailableView = verticalLayout {
                    visibility = View.GONE
                    textView {
                        lparams(width = wrapContent, height = wrapContent){
                            gravity = Gravity.CENTER
                        }
                        this.gravity = Gravity.CENTER
                        text = "To take photo of holes, \n" +
                                "allow Asphalt app \n" +
                                "access to your Camera"
                        setFont("fonts/RobotoLight.ttf")
                        textColorResource = R.color.white
                        textSize = sp(8).toFloat()
                    }

                    button {
                        lparams (width = widthProcent(60), height = heightProcent(10) ){
                            gravity = Gravity.CENTER_HORIZONTAL
                            topMargin = dip(10)
                        }
                        text = "Request"
                        setFont("fonts/RobotoLight.ttf")
                        textSize = sp(6).toFloat()
                        backgroundResource = R.drawable.welcome_btn
                        setOnClickListener { ui.requestCamera()}

                    }
                }.lparams(width = matchParent, height = wrapContent) {
                    gravity = Gravity.CENTER
                }

                ui.cameraView = verticalLayout {
                    visibility = View.GONE
                    ui.iv = imageView {
                        lparams(width = matchParent, height = matchParent)
                        scaleType = ImageView.ScaleType.CENTER_CROP
                    }
                }.lparams(width = matchParent, height = matchParent)

                ui.takePhotoBtn = button {
                    visibility = View.GONE
                    text = "Take photo"
                    setFont("fonts/RobotoLight.ttf")
                    textSize = sp(6).toFloat()
                    backgroundResource = R.drawable.welcome_btn
                    setOnClickListener { ui.takePhoto()}

                }.lparams (width = matchParent, height = heightProcent(10) ){
                    gravity = Gravity.BOTTOM
                }

                ui.uploadPhotoBtn = button {
                    visibility = View.GONE
                    text = "Upload photo"
                    setFont("fonts/RobotoLight.ttf")
                    textSize = sp(6).toFloat()
                    backgroundResource = R.drawable.regular_btn
                    setOnClickListener { ui.uploadPhoto()}

                }.lparams (width = matchParent, height = heightProcent(10) ){
                    gravity = Gravity.BOTTOM
                }

            }
        }.view
    }

    override fun unbind(t: CameraController) {
    }

}