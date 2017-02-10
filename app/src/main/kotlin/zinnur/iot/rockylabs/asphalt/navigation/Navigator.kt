package zinnur.iot.rockylabs.asphalt.navigation

import android.view.View
import com.bluelinelabs.conductor.Router

/**
 * Created by Zinnur on 10.02.17.
 */

interface Navigator {

    val router: Router

    fun showSignIn(fab: View){}
}