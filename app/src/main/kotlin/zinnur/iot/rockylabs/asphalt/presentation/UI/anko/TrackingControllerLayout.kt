package zinnur.iot.rockylabs.asphalt.presentation.UI.anko

import android.graphics.Color
import android.opengl.Visibility
import android.support.design.R.id.center
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.GridLabelRenderer
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import org.jetbrains.anko.*
import zinnur.iot.rockylabs.asphalt.presentation.UI.controllers.TrackingController
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.MainView
import kotlin.ru.rockylabs.kotlintest.R

/**
 * Created by Zinnur on 15.02.17.
 */

class TrackingControllerLayout : ViewBinder<TrackingController>{
    override fun bind(ui: TrackingController): View {
        return ui.activity!!.UI {
            ui.activity!!.window.setBackgroundDrawableResource(R.color.colorPrimary)
            ui.container = frameLayout {
                lparams(width = matchParent, height = matchParent) {
                    padding = dimen(R.dimen.activity_horizontal_margin)
                }

                ui.permissionsLayout = verticalLayout {

                    visibility = View.GONE

                    imageView {
                        lparams(width = wrapContent, height = wrapContent){
                            gravity = Gravity.CENTER_HORIZONTAL
                        }
                        scaleType = ImageView.ScaleType.CENTER
                        imageResource = R.drawable.location_icon_24dp
                    }
                    textView {
                         lparams(width = wrapContent, height = wrapContent){
                             topMargin = dip(15)
                             gravity = Gravity.CENTER
                         }
                         this.gravity = Gravity.CENTER
                         setFont("fonts/RobotoLight.ttf")
                         textColorResource = R.color.white
                         textSize = sp(9).toFloat()
                         text = "To tracking holes, \n" +
                                 "allow Asphalt app \n" +
                                "access to your location"
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
                        setOnClickListener { ui.requestPermissions()}

                    }
                }.lparams(width = matchParent, height = wrapContent){
                    gravity = Gravity.CENTER
                    padding = dip(16)
                }

                ui.graph = graphView {
                    lparams(width = matchParent, height = dip(300)){
                        gravity = Gravity.BOTTOM
                        leftMargin = dip(-100)
                        bottomMargin = dip(30)
                    }
                    gridLabelRenderer.isVerticalLabelsVisible = false
                    gridLabelRenderer.isHighlightZeroLines = false
                    gridLabelRenderer.isHorizontalLabelsVisible = false
                    gridLabelRenderer.isHumanRounding = true
                    gridLabelRenderer.gridStyle = GridLabelRenderer.GridStyle.NONE
                    ui.series = LineGraphSeries<DataPoint>()
                    ui.series.color = ContextCompat.getColor(ui.activity, R.color.indigo)
                    ui.series.dataPointsRadius = 3f
                    viewport.setMinX(0.0)
                    viewport.setMaxX(10.0)
                    viewport.setMinY(0.0)
                    viewport.setMaxY(15.0)
                    viewport.isYAxisBoundsManual = true
                    viewport.isXAxisBoundsManual = true
                    viewport.isScrollable = false
                    visibility = View.GONE

                }


                ui.permissionsGrantedLayout = verticalLayout {
                    visibility = View.GONE
                    ui.roundBtn = frameLayout {
                        backgroundResource = R.drawable.round_btn
                        setOnClickListener { ui.onClickPlayBtn() }
                        ui.playerIcon = imageView {
                            imageResource  = R.drawable.ic_navigate
                        }.lparams(width = wrapContent, height = wrapContent){
                            gravity = Gravity.CENTER
                            padding = dip(10)
                        }

                    }.lparams(width = 350, height = 350) {
                        gravity = Gravity.CENTER
                    }

                    ui.textContainer = linearLayout {
                        visibility = View.GONE
                        orientation = LinearLayout.HORIZONTAL
                        ui.lowLvl = textView {
                            gravity = Gravity.CENTER
                            setFont("fonts/RobotoLight.ttf")
                            textColorResource = R.color.grey
                            textSize = sp(8).toFloat()
                            setOnClickListener { ui.onClickLow() }
                            text = "Low"
                        }.lparams(width = wrapContent, height = wrapContent){
                            weight = 1f
                        }

                        ui.mediumLvl = textView {
                            gravity = Gravity.CENTER
                            setFont("fonts/RobotoLight.ttf")
                            textColorResource = R.color.indigo
                            textSize = sp(8).toFloat()
                            setOnClickListener {
                                ui.onClickMedium()
                            }
                            text = "Medium"
                        }.lparams(width = wrapContent, height = wrapContent){
                            weight = 1f
                        }

                        ui.highLvl = textView {
                            gravity = Gravity.CENTER
                            setFont("fonts/RobotoLight.ttf")
                            textColorResource = R.color.grey
                            textSize = sp(8).toFloat()
                            setOnClickListener {
                                ui.onClickHigh()
                            }
                            text = "High"
                        }.lparams(width = wrapContent, height = wrapContent){
                            weight = 1f
                        }

                    }.lparams(width = matchParent, height = wrapContent) {
                        gravity = Gravity.CENTER
                        topMargin = dip(50)

                    }
                }.lparams(width = matchParent, height = wrapContent){
                    gravity = Gravity.CENTER
                }

            }
        }.view
    }

    override fun unbind(t: TrackingController) {
    }

}