package zinnur.iot.rockylabs.asphalt.presentation.mvp.views

import com.hannesdorfmann.mosby3.mvp.MvpView

/**
 * Created by Zinnur on 28.02.17.
 */

interface  TrackingView : MvpView {

    fun updateGraph(gForce: Double)

    fun updateBackgroundColor()

    fun transitionOnStart()

    fun transitionOnStop()

    fun accentLowLvl()

    fun accentMediumLvl()

    fun accentHighLvl()
}