package zinnur.iot.rockylabs.asphalt.presentation.mvp.views.viewStates

import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.PotholeView

/**
 * Created by Zinnur on 04.06.17.
 */
class PotholeViewState  : ViewState<PotholeView> {

    enum class State {
        SHOW_ERROR
    }

    private var state = State.SHOW_ERROR

    override fun apply(view: PotholeView, retained: Boolean) = when (state) {
        State.SHOW_ERROR -> view.showError()
    }


    fun setShowError() {
        state = State.SHOW_ERROR
    }

}