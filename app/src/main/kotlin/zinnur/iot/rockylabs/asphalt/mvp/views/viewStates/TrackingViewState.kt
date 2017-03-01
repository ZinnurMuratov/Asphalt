package zinnur.iot.rockylabs.asphalt.mvp.views.viewStates

import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState
import zinnur.iot.rockylabs.asphalt.mvp.views.TrackingView

/**
 * Created by Zinnur on 28.02.17.
 */


class TrackingViewState : ViewState<TrackingView> {
    enum class State {
        SHOW_BASE, SHOW_PROCCESS
    }

    private var state = State.SHOW_BASE

    override fun apply(view: TrackingView, retained: Boolean) = when (state) {
        State.SHOW_BASE -> view.transitionOnStop()
        State.SHOW_PROCCESS -> view.transitionOnStart()
    }

    fun setShowIdle() {
        state = State.SHOW_BASE
    }

    fun setShowProccess(){
        state = State.SHOW_PROCCESS
    }
}