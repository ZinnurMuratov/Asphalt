package zinnur.iot.rockylabs.asphalt.presentation.navigation

import android.annotation.SuppressLint
import android.os.Build
import android.support.annotation.RequiresApi
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.AutoTransitionChangeHandler
import zinnur.iot.rockylabs.asphalt.data.entity.realm.Pothole
import zinnur.iot.rockylabs.asphalt.data.entity.realm.Track
import zinnur.iot.rockylabs.asphalt.presentation.UI.controllers.*

/**
 * Created by Zinnur on 10.02.17.
 */
@SuppressLint("NewApi")
interface Navigator {

    val router: Router

    fun showSignIn(){
        val handler = AutoTransitionChangeHandler()
        router.pushController(RouterTransaction.with(SignInController())
                .pushChangeHandler(handler)
                .popChangeHandler(handler)
        )
    }

    fun showSignUp(){
        val handler = AutoTransitionChangeHandler()
        router.pushController(RouterTransaction.with(SignUpController())
                .pushChangeHandler(handler)
                .popChangeHandler(handler)
        )
    }

    fun showTracking(root: Boolean){
        val handler = AutoTransitionChangeHandler()
        if (root){
            router.setRoot(RouterTransaction.with(TrackingController())
                    .popChangeHandler(handler)
                    .pushChangeHandler(handler))
        } else {
            router.pushController(RouterTransaction.with(TrackingController())
                    .popChangeHandler(handler)
                    .pushChangeHandler(handler))
        }
    }

    fun showWelcome(root: Boolean){
        val handler = AutoTransitionChangeHandler()
        if (root) {
            router.setRoot(RouterTransaction.with(WelcomeController())
                    .popChangeHandler(handler)
                    .pushChangeHandler(handler)
            )
        } else {
            router.pushController(RouterTransaction.with(WelcomeController())
                    .popChangeHandler(handler)
                    .pushChangeHandler(handler)
            )
        }
    }

    fun showMap(root: Boolean){
        val handler = AutoTransitionChangeHandler()
        if (root) {
            router.setRoot(RouterTransaction.with(OnMapController())
                    .popChangeHandler(handler)
                    .pushChangeHandler(handler)
            )
        } else {
            router.pushController(RouterTransaction.with(OnMapController())
                    .popChangeHandler(handler)
                    .pushChangeHandler(handler)
            )
        }
    }

    fun showSettings(root: Boolean){
        val handler = AutoTransitionChangeHandler()
        if (root) {
            router.setRoot(RouterTransaction.with(SettingsController())
                    .popChangeHandler(handler)
                    .pushChangeHandler(handler)
            )
        } else {
            router.pushController(RouterTransaction.with(SettingsController())
                    .popChangeHandler(handler)
                    .pushChangeHandler(handler)
            )
        }
    }

    fun showFeed(root: Boolean){
        val handler = AutoTransitionChangeHandler()
        if (root) {
            router.setRoot(RouterTransaction.with(FeedController())
                    .popChangeHandler(handler)
                    .pushChangeHandler(handler)
            )
        } else {
            router.pushController(RouterTransaction.with(FeedController())
                    .popChangeHandler(handler)
                    .pushChangeHandler(handler)
            )
        }
    }

    fun showPotholeFeed(root: Boolean){
        val handler = AutoTransitionChangeHandler()
        if (root) {
            router.setRoot(RouterTransaction.with(TrackingFeedController())
                    .popChangeHandler(handler)
                    .pushChangeHandler(handler)
            )
        } else {
            router.pushController(RouterTransaction.with(TrackingFeedController())
                    .popChangeHandler(handler)
                    .pushChangeHandler(handler)
            )
        }
    }

    fun showCamera(){
        val handler = AutoTransitionChangeHandler()
            router.pushController(RouterTransaction.with(CameraController())
                    .popChangeHandler(handler)
                    .pushChangeHandler(handler)
            )

    }

    fun showPothole(pothole: Pothole?){
        val handler = AutoTransitionChangeHandler()
        router.pushController(RouterTransaction.with(PotholeController(pothole))
                .popChangeHandler(handler)
                .pushChangeHandler(handler)
        )
    }

    fun showTrack(track: Track?){
        val handler = AutoTransitionChangeHandler()
        router.pushController(RouterTransaction.with(TrackController(track))
                .popChangeHandler(handler)
                .pushChangeHandler(handler)
        )
    }



}