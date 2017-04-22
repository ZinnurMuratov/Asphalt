package zinnur.iot.rockylabs.asphalt.presentation.mvp.views

import com.hannesdorfmann.mosby3.mvp.MvpView

/**
 * Created by Zinnur on 10.04.17.
 */
interface CameraView : MvpView{

    fun showCamera()

    fun showCameraNotEnable()

    fun showGeoNotEnable()

    fun showError()

    fun showProgress(show: Boolean)

    fun showStart()

    fun showPhotoTaken();

    fun isCameraEnable()
}