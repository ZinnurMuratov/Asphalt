package zinnur.iot.rockylabs.asphalt.presentation.UI.controllers

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import com.hannesdorfmann.mosby3.conductor.viewstate.MvpViewStateController
import com.tbruyelle.rxpermissions2.RxPermissions
import zinnur.iot.rockylabs.asphalt.presentation.UI.anko.CameraLayout
import zinnur.iot.rockylabs.asphalt.presentation.daggerComponent
import zinnur.iot.rockylabs.asphalt.presentation.mvp.presenters.CameraPresenter
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.CameraView
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.MainView
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.viewStates.CameraViewState
import android.R.attr.data
import android.app.Activity.RESULT_OK
import android.app.ProgressDialog
import android.util.Log
import android.widget.ImageView
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo
import com.miguelbcr.ui.rx_paparazzo2.entities.FileData
import com.miguelbcr.ui.rx_paparazzo2.entities.Response
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.net.CacheResponse
import kotlin.ru.rockylabs.kotlintest.R
import com.miguelbcr.ui.rx_paparazzo2.entities.size.CustomMaxSize
import com.miguelbcr.ui.rx_paparazzo2.entities.size.OriginalSize
import com.miguelbcr.ui.rx_paparazzo2.entities.size.ScreenSize
import com.miguelbcr.ui.rx_paparazzo2.entities.size.Size
import com.yalantis.ucrop.UCrop
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import zinnur.iot.rockylabs.asphalt.presentation.UI.activities.MainActivity


/**
 * Created by Zinnur on 16.02.17.
 */
class CameraController : MvpViewStateController<CameraView, CameraPresenter, CameraViewState>(), CameraView{

    private val viewBinder = CameraLayout()
    private lateinit var activityCallback: MainView
    lateinit var locationIsNotAvailableView: LinearLayout
    lateinit var cameraIsNotAvailableView: LinearLayout
    lateinit var cameraView: LinearLayout
    lateinit var iv: ImageView
    lateinit var takePhotoBtn: Button
    lateinit var uploadPhotoBtn: Button
    private lateinit var progressDialog: ProgressDialog
    private var photoFile: FileData? = null


    override fun createPresenter(): CameraPresenter = daggerComponent.cameraPresenter()

    override fun onViewStateInstanceRestored(instanceStateRetained: Boolean) {}

    override fun onNewViewStateInstance() {showStart()}

    override fun createViewState(): CameraViewState = CameraViewState()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = viewBinder.bind(this)
        activityCallback =  activity as MainView
        activityCallback.changeTitle("Capture")
        activityCallback.lockDrawer(true)
        activityCallback.setHomeEnabled(true)
        initProgress()
        return view
    }

    fun initProgress(){
        progressDialog = ProgressDialog(activity)
        progressDialog.setMessage("Loading...")
        progressDialog.setCancelable(false)
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.clearSubscriptions()
    }


    fun requestCamera(){
        RxPermissions(activity as Activity)
                .request(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe({
                    granted ->
                    if (granted) {
                        showCamera()
                    } else {
                        showCameraNotEnable()
                    }
                }) { it.printStackTrace() }
    }


    override fun showStart() {}

    override fun isCameraEnable() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity?.checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                    && activity?.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                showCamera()
            } else {
                showCameraNotEnable()
            }
        } else {
            showCamera()
        }
    }

    fun takePhoto(){
        val size = ScreenSize()
        val takeOne = activityCallback.pickSingle(null, size).usingCamera()
        processSingle(takeOne)
    }

    fun processSingle(pickUsingGallery: Observable<Response<MainActivity, FileData>> ) {
        pickUsingGallery
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {it -> showPhoto(it.data())},
                        onError = {e -> e.printStackTrace()})
    }


    fun showPhoto(response: FileData){
        showPhotoTaken()
        photoFile = response
        Picasso.with(activity)
                .load(response.file)
                .error(R.color.colorPrimary)
                .into(iv)
    }

    fun uploadPhoto(){
        presenter.uploadToFireBase(photoFile?.file)
    }

    override fun showPhotoTaken() {
        takePhotoBtn.visibility = View.GONE
        uploadPhotoBtn.visibility = View.VISIBLE
        viewState.setShowPhotoTaken()
    }




    override fun showCamera() {
        Log.d("camera", "show camera")
        locationIsNotAvailableView.visibility = View.GONE
        cameraView.visibility = View.VISIBLE
        cameraIsNotAvailableView.visibility = View.GONE
        takePhotoBtn.visibility = View.VISIBLE
        viewState.setShowCamera()
    }

    override fun showCameraNotEnable() {
        locationIsNotAvailableView.visibility = View.GONE
        cameraView.visibility = View.GONE
        cameraIsNotAvailableView.visibility = View.VISIBLE
        takePhotoBtn.visibility = View.GONE
        viewState.setShowCameraNotEnable()

    }

    override fun showGeoNotEnable() {
        locationIsNotAvailableView.visibility = View.VISIBLE
        cameraView.visibility = View.GONE
        cameraIsNotAvailableView.visibility = View.GONE
        takePhotoBtn.visibility = View.GONE
        viewState.setShowLocationNotEnable()

    }

    override fun showError() {
        viewState.setShowError()
    }

    override fun showProgress(show:Boolean) {
        if (show){
            progressDialog.show()
            viewState.setShowProgress()
        } else {
            progressDialog.dismiss()
        }
    }



}