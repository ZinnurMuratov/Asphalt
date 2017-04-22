package zinnur.iot.rockylabs.asphalt.presentation.mvp.views.viewStates

import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.CameraView
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.SettingsView

/**
 * Created by Zinnur on 10.04.17.
 */
class CameraViewState : ViewState<CameraView>{

    enum class State {
        SHOW_ERROR,
        SHOW_CAMERA,
        SHOW_PROGRESS,
        SHOW_CAMERA_NULL,
        SHOW_LOCATION_NULL,
        SHOW_PHOTO_TAKEN
    }

    private var state = State.SHOW_PROGRESS

    override fun apply(view: CameraView, retained: Boolean) = when (state) {
        State.SHOW_ERROR -> view.showError()
        State.SHOW_CAMERA -> view.showCamera()
        State.SHOW_CAMERA_NULL -> view.showCameraNotEnable()
        State.SHOW_LOCATION_NULL -> view.showGeoNotEnable()
        State.SHOW_PROGRESS -> view.showProgress(true)
        State.SHOW_PHOTO_TAKEN -> view.showPhotoTaken()
    }


    fun setShowError() {
        state = State.SHOW_ERROR
    }

    fun setShowCamera(){
        state = State.SHOW_CAMERA
    }

    fun setShowCameraNotEnable(){
        state = State.SHOW_CAMERA_NULL
    }

    fun setShowLocationNotEnable(){
        state = State.SHOW_LOCATION_NULL
    }

    fun setShowProgress(){
        state = State.SHOW_PROGRESS
    }

    fun setShowPhotoTaken(){
        state = State.SHOW_PHOTO_TAKEN
    }

}