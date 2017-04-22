package zinnur.iot.rockylabs.asphalt.presentation.mvp.views.viewStates

import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.FeedView

/**
 * Created by Zinnur on 11.04.17.
 */

class FeedViewState  : ViewState<FeedView> {

    enum class State {
        SHOW_ERROR
    }

    private var state = State.SHOW_ERROR

    override fun apply(view: FeedView, retained: Boolean) = when (state) {
        State.SHOW_ERROR -> view.showError()
    }


    fun setShowError() {
        state = State.SHOW_ERROR
    }

}