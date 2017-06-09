package zinnur.iot.rockylabs.asphalt.presentation.UI.anko

import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import com.jjoe64.graphview.GridLabelRenderer
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import org.jetbrains.anko.*
import zinnur.iot.rockylabs.asphalt.presentation.UI.controllers.PotholeController
import kotlin.ru.rockylabs.kotlintest.R

/**
 * Created by Zinnur on 04.06.17.
 */

class PotholeLayout : ViewBinder<PotholeController>{
    override fun bind(ui: PotholeController): View {
        return ui.activity!!.UI {
            ui.container = frameLayout {
                lparams(width = matchParent, height = matchParent) {
                    padding = dimen(R.dimen.activity_horizontal_margin)
                }


                ui.graph = graphView {
                    lparams(width = matchParent, height = dip(600)){
                        gravity = Gravity.BOTTOM
                    }
                    gridLabelRenderer.isVerticalLabelsVisible = true
                    gridLabelRenderer.isHighlightZeroLines = false
                    gridLabelRenderer.isHorizontalLabelsVisible = false
                    gridLabelRenderer.isHumanRounding = true
                    gridLabelRenderer.gridStyle = GridLabelRenderer.GridStyle.HORIZONTAL
                    ui.zLine = LineGraphSeries<DataPoint>()
                    ui.zLine.color = ContextCompat.getColor(ui.activity, R.color.redError)
                    ui.zLine.dataPointsRadius = 2f
                    ui.xLine = LineGraphSeries<DataPoint>()
                    ui.xLine.color = ContextCompat.getColor(ui.activity, R.color.blueOpacity)
                    ui.xLine.dataPointsRadius = 2f
                    ui.yLine = LineGraphSeries<DataPoint>()
                    ui.yLine.color = ContextCompat.getColor(ui.activity, R.color.greenOpacity)
                    ui.yLine.dataPointsRadius = 2f
                    ui.gLine = LineGraphSeries<DataPoint>()
                    ui.gLine.color = ContextCompat.getColor(ui.activity, R.color.grey)
                    ui.gLine.dataPointsRadius = 2f
                    viewport.setMinX(0.0)
                    viewport.setMaxX(10.0)
                    viewport.setMinY(-5.0)
                    viewport.setMaxY(5.0)
                    viewport.isYAxisBoundsManual = false
                    viewport.isXAxisBoundsManual = false
                    viewport.isScalable = true
                    viewport.isScrollable = true

                }

            }
        }.view
    }

    override fun unbind(t: PotholeController) {

    }

}