package zinnur.iot.rockylabs.asphalt.presentation.mvp.presenters

import android.location.Location
import android.net.Uri
import android.support.annotation.Nullable
import android.text.format.Time
import android.util.Log
import com.google.android.gms.location.LocationRequest
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.patloew.rxlocation.FusedLocation
import io.reactivex.disposables.Disposables
import io.reactivex.rxkotlin.toSingle
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.CameraView
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.TrackingView
import javax.inject.Inject
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.kelvinapps.rxfirebase.RxFirebaseStorage
import io.reactivex.observers.DisposableObserver
import zinnur.iot.rockylabs.asphalt.data.entity.AbsResponseEntity
import zinnur.iot.rockylabs.asphalt.domain.iteractor.CreatePhotoHoleUseCase
import java.io.File


/**
 * Created by Zinnur on 10.04.17.
 */

class CameraPresenter @Inject constructor(@Nullable var locSensor: FusedLocation,
                                          var locationRequest: LocationRequest,
                                          var createPhotoHoleUseCase: CreatePhotoHoleUseCase) : MvpBasePresenter<CameraView>(){
    init {
        requestLocation()
    }

    private var locationSubscription = Disposables.empty()
    private lateinit var lastLatLng: Location

    fun requestLocation(){
        locationSubscription = locSensor
                .updates(locationRequest.setNumUpdates(1))
                .subscribe{ it ->
                    if (it == null){
                        view?.showGeoNotEnable()
                    } else {
                        lastLatLng = it
                        view?.isCameraEnable()
                    }
                }
    }

    fun uploadToFireBase(file: File?){
        view?.showProgress(true)
        val storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://asphalt-1491741748735.appspot.com/")
        RxFirebaseStorage.putFile(storageRef.child("photos/"+ System.nanoTime()), Uri.fromFile(file))
                .subscribe{ url ->
                    uploadToBackend(url.downloadUrl.toString())
                    Log.d("firebase ", " " + url.downloadUrl)
                }
    }

    fun uploadToBackend(url: String){
        createPhotoHoleUseCase.execute(TrackingObserver(),
                CreatePhotoHoleUseCase.Params.withCoords(lastLatLng.latitude, lastLatLng.longitude, url))
    }

    fun clearSubscriptions(){
        if (!locationSubscription?.isDisposed!!){
            locationSubscription?.dispose()
        }
    }

    private inner class TrackingObserver : DisposableObserver<AbsResponseEntity>() {

        override fun onComplete() {
            view?.showProgress(false)
        }

        override fun onError(e: Throwable) {
            view?.showProgress(false)
            view?.showError()
            e.printStackTrace()
        }

        override fun onNext(res: AbsResponseEntity) {
            Log.d("result ->", " " + res.status)
            view?.showProgress(false)

        }
    }

}