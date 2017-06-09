package zinnur.iot.rockylabs.asphalt.presentation.mvp.views.viewStates

import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.TrackingFeedView

/**
 * Created by Zinnur on 04.06.17.
 */
class TrackingFeedViewState : ViewState<TrackingFeedView> {

    enum class State {
        SHOW_ERROR
    }

    private var state = State.SHOW_ERROR

    override fun apply(view: TrackingFeedView, retained: Boolean) = when (state) {
        State.SHOW_ERROR -> view.showError()
    }


    fun setShowError() {
        state = State.SHOW_ERROR
    }

}