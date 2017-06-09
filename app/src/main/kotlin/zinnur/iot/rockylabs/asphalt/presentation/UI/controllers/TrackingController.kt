package zinnur.iot.rockylabs.asphalt.presentation.UI.controllers

import android.Manifest
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Activity
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.view.*
import zinnur.iot.rockylabs.asphalt.presentation.UI.anko.TrackingControllerLayout
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.MainView
import kotlin.ru.rockylabs.kotlintest.R
import android.view.MenuInflater
import android.widget.FrameLayout
import zinnur.iot.rockylabs.asphalt.presentation.navigator
import android.view.ViewGroup
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.hannesdorfmann.mosby3.conductor.viewstate.MvpViewStateController
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.tbruyelle.rxpermissions2.Permission
import com.tbruyelle.rxpermissions2.RxPermissions
import com.transitionseverywhere.*
import com.transitionseverywhere.extra.Scale
import org.jetbrains.anko.imageResource
import rx.Subscription
import zinnur.iot.rockylabs.asphalt.presentation.UI.anko.textColorResource
import zinnur.iot.rockylabs.asphalt.presentation.daggerComponent
import zinnur.iot.rockylabs.asphalt.presentation.mvp.presenters.TrackingPresenter
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.TrackingView
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.viewStates.TrackingViewState


/**
 * Created by Zinnur on 15.02.17.
 */
class TrackingController : TrackingView, MvpViewStateController<TrackingView, TrackingPresenter, TrackingViewState>(){


    private val viewBinder = TrackingControllerLayout()
    private lateinit var activityCallback: MainView
    lateinit var container: FrameLayout
    lateinit var roundBtn:  FrameLayout
    lateinit var graph: GraphView
    lateinit var playerIcon: ImageView
    lateinit var series: LineGraphSeries<DataPoint>
    lateinit var textContainer: LinearLayout
    lateinit var lowLvl: TextView
    lateinit var mediumLvl: TextView
    lateinit var permissionsLayout: LinearLayout
    lateinit var permissionsGrantedLayout: LinearLayout
    lateinit var highLvl: TextView
    private var clicked = false
    private var permissions = false
    private var lastX = 0

    override fun onViewStateInstanceRestored(instanceStateRetained: Boolean) {}

    override fun onNewViewStateInstance() {}

    override fun createViewState() = TrackingViewState()

    override fun createPresenter():  TrackingPresenter = daggerComponent.trackingPresenter()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = viewBinder.bind(this)
        setHasOptionsMenu(true)
        return view
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        (activity as AppCompatActivity).supportActionBar!!.show()
        activityCallback = activity as MainView
        activityCallback.lockDrawer(false)
        activityCallback.changeTitle("Tracking")
        activityCallback.setHomeEnabled(false)
        graph.addSeries(series)
        checkPermissions()
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        presenter.stopTracking()
        viewBinder.unbind(this)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.tracking, menu)
        val camera = menu.findItem(R.id.action_camera)
        camera.setOnMenuItemClickListener({
            navigator.showCamera()
            false
        })

        super.onCreateOptionsMenu(menu, inflater)
    }


    fun checkPermissions(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity?.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                showUiIfPermissionsGranted()
                permissions = true
            } else {
                showPermissionsExplanation()
                permissions = false
            }
        } else {
            showUiIfPermissionsGranted()
            permissions = true
        }
    }

    fun showPermissionsExplanation(){
        permissionsLayout.visibility = View.VISIBLE
        permissionsGrantedLayout.visibility = View.GONE
    }

    fun showUiIfPermissionsGranted(){
        permissionsGrantedLayout.visibility = View.VISIBLE
        permissionsLayout.visibility = View.GONE

    }

    fun requestPermissions(){
        RxPermissions(activity as Activity)
                .request(Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe({
                    granted ->
                        if (granted) {
                            permissions = granted
                            showUiIfPermissionsGranted()
                        } else {
                            showPermissionsExplanation()
                        }
                }) { it.printStackTrace() }
    }

    override fun showLocationNotAvailable() {
        clicked = false
        transitionOnStop()
        showPermissionsExplanation()
    }


    fun onClickPlayBtn(){
        if (!clicked && permissions) {
            clicked = true
            transitionOnStart()
            presenter.startTraking()
        } else if (clicked && permissions) {
            clicked = false
            transitionOnStop()
            presenter.stopTracking()
        } else {
            Log.d("error ", " no access")
        }
    }

    fun onClickLow(){
        presenter.setSensetiveLvl(2.5f)
        accentLowLvl()
    }

    fun onClickMedium(){
        presenter.setSensetiveLvl(2f)
        accentMediumLvl()
    }

    fun onClickHigh(){
        presenter.setSensetiveLvl(1.3f)
        accentHighLvl()
    }



    override fun transitionOnStart(){
        Log.d("state", "on start")
        viewState.setShowProccess()
        TransitionManager.beginDelayedTransition(container, TransitionSet()
                .addTransition(ChangeBounds().setInterpolator(AccelerateInterpolator()))
                .addTransition(Fade().setInterpolator(LinearInterpolator()))
                .addTransition(Scale()))
        graph.visibility = View.VISIBLE
        playerIcon.imageResource = R.drawable.ic_action_stop
        textContainer.visibility = View.VISIBLE
    }

    override fun transitionOnStop(){
        Log.d("state", "on stop")
        viewState.setShowIdle()
        TransitionManager.beginDelayedTransition(container, TransitionSet()
                .addTransition(ChangeBounds().setInterpolator(AccelerateInterpolator()))
                .addTransition(Fade().setInterpolator(LinearInterpolator()))
                .addTransition(Scale()))
        textContainer.visibility = View.GONE
        graph.visibility = View.GONE
        playerIcon.imageResource = R.drawable.ic_navigate
    }



    override fun updateBackgroundColor(){
        val objectAnimator = ObjectAnimator.ofObject(container, "backgroundColor",
                ArgbEvaluator(), ContextCompat.getColor(activity, R.color.colorPrimary),
                ContextCompat.getColor(activity, R.color.redOpacity))
        objectAnimator.repeatCount = 1
        objectAnimator.repeatMode = ValueAnimator.REVERSE
        objectAnimator.duration = 200
        objectAnimator.start()

    }

    override fun updateGraph(accel: Double) = series.appendData(DataPoint(lastX++.toDouble(), accel), true, 10)

    override fun accentLowLvl() {
        lowLvl.textColorResource = R.color.indigo
        mediumLvl.textColorResource = R.color.grey
        highLvl.textColorResource = R.color.grey
    }

    override fun accentHighLvl() {
        lowLvl.textColorResource = R.color.grey
        mediumLvl.textColorResource = R.color.grey
        highLvl.textColorResource = R.color.indigo
    }

    override fun accentMediumLvl() {
        lowLvl.textColorResource = R.color.grey
        mediumLvl.textColorResource = R.color.indigo
        highLvl.textColorResource = R.color.grey
    }

}