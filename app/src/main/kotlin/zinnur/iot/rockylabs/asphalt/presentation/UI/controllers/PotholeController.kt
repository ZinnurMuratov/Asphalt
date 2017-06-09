package zinnur.iot.rockylabs.asphalt.presentation.UI.controllers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.hannesdorfmann.mosby3.conductor.viewstate.MvpViewStateController
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import zinnur.iot.rockylabs.asphalt.data.entity.realm.Pothole
import zinnur.iot.rockylabs.asphalt.presentation.UI.anko.PotholeLayout
import zinnur.iot.rockylabs.asphalt.presentation.daggerComponent
import zinnur.iot.rockylabs.asphalt.presentation.mvp.presenters.PotholePresenter
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.MainView
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.PotholeView
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.viewStates.PotholeViewState

/**
 * Created by Zinnur on 04.06.17.
 */

class PotholeController : MvpViewStateController<PotholeView, PotholePresenter, PotholeViewState>, PotholeView{
    constructor() : super()
    constructor(bundle: Bundle) : super(bundle)
    constructor(pothole: Pothole?) {
        this.pothole = pothole
    }
    private var pothole: Pothole ?= null
    lateinit var container: FrameLayout
    lateinit var graph: GraphView
    lateinit var xLine: LineGraphSeries<DataPoint>
    lateinit var yLine: LineGraphSeries<DataPoint>
    lateinit var zLine: LineGraphSeries<DataPoint>
    lateinit var gLine: LineGraphSeries<DataPoint>



    override fun onViewStateInstanceRestored(instanceStateRetained: Boolean) {}

    override fun createPresenter(): PotholePresenter = daggerComponent.potholePresenter()

    override fun createViewState() = PotholeViewState()

    override fun onNewViewStateInstance() {  }



    private lateinit var activityCallback: MainView

    private val viewBinder = PotholeLayout()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        activityCallback =  activity as MainView
        val view = viewBinder.bind(this)
        activityCallback.lockDrawer(true)
        activityCallback.setHomeEnabled(true)
        activityCallback.changeTitle("Pothole")
        graph.addSeries(xLine)
        graph.addSeries(zLine)
        graph.addSeries(yLine)
        graph.addSeries(gLine)
        parseData()
        return view
    }


    private fun parseData(){
        pothole?.let {
            Log.d("pothole"," " + it.created)
            doAsync {
                val zList = it.z?.split(";")
                val xList = it.x?.split(";")
                val yList = it.y?.split(";")
                val gList = it.g?.split(";")
                uiThread {

                    setGraph(xLine, xList)
                    setGraph(yLine, yList)
                    setGraph(gLine, gList)
                    setGraph(zLine, zList)
                }
            }

        }
    }

    private fun setGraph(series: LineGraphSeries<DataPoint>, data: List<String>?){
        data?.let{
            val size = it.size
            for ((last, dot) in it.withIndex()){
                if (dot.isNotEmpty()){
                    series.appendData(DataPoint(last.toDouble(), dot.toDouble() ), false, size)
                }
            }
        }
    }

    override fun setData(pothole: Pothole) {
    }

    override fun showError() {
    }



}