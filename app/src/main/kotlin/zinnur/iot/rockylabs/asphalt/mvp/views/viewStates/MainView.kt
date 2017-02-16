package zinnur.iot.rockylabs.asphalt.mvp.views.viewStates

/**
 * Created by Zinnur on 15.02.17.
 */

interface MainView  {
    fun lockDrawer(lock: Boolean)

    fun showToolbar(show: Boolean)
}