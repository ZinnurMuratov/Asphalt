package zinnur.iot.rockylabs.asphalt.presentation.mvp.views

import com.hannesdorfmann.mosby3.mvp.MvpView
import zinnur.iot.rockylabs.asphalt.data.entity.realm.Pothole

/**
 * Created by Zinnur on 04.06.17.
 */
interface PotholeView : MvpView {
    fun showError()

    fun setData(pothole: Pothole)
}