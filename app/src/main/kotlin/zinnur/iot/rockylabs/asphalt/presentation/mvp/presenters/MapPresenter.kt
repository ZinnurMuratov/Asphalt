package zinnur.iot.rockylabs.asphalt.presentation.mvp.presenters

import android.util.Log
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.observers.DisposableObserver
import zinnur.iot.rockylabs.asphalt.data.entity.GetHoleResponseEntity
import zinnur.iot.rockylabs.asphalt.domain.iteractor.GetHolesUseCase
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.OnMapView
import javax.inject.Inject

/**
 * Created by Zinnur on 10.04.17.
 */
class MapPresenter @Inject constructor(var getHolesUseCase: GetHolesUseCase) {

    private lateinit var view: OnMapView

    fun attachView(view: OnMapView){
        this.view = view
    }

    fun getHoles() {
        getHolesUseCase.execute(HolesObserver(), GetHolesUseCase.Params.ofCity("Kazan'"))
    }

    fun onDestroyView(){
        getHolesUseCase.dispose()
    }

    private inner class HolesObserver : DisposableObserver<GetHoleResponseEntity>() {

        override fun onComplete() {
        }

        override fun onError(e: Throwable) {
            e.printStackTrace()
        }

        override fun onNext(holes: GetHoleResponseEntity) {
            for (hole in holes.holesEntity){
                try {
                    view?.putMarkers(hole.lat, hole.lng, hole.level, hole.axis)
                } catch (e: Exception){
                    e.printStackTrace()
                }

            }
        }
    }
}