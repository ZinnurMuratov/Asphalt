package zinnur.iot.rockylabs.asphalt.presentation.mvp.views

import com.hannesdorfmann.mosby3.mvp.MvpView
import zinnur.iot.rockylabs.asphalt.data.entity.realm.PathData

/**
 * Created by Zinnur on 07.06.17.
 */
interface TrackView : MvpView{
    fun showError()

    fun setData(data: PathData)

    fun draw()
}