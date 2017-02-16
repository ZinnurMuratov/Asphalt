package zinnur.iot.rockylabs.asphalt.UI.activities

import zinnur.iot.rockylabs.asphalt.UI.anko.MainActivityLayout
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import zinnur.iot.rockylabs.asphalt.UI.controllers.TrackingController
import zinnur.iot.rockylabs.asphalt.UI.controllers.WelcomeController
import zinnur.iot.rockylabs.asphalt.mvp.views.viewStates.MainView
import zinnur.iot.rockylabs.asphalt.navigation.Navigator
import zinnur.iot.rockylabs.asphalt.navigation.PhoneNavigator
import kotlin.ru.rockylabs.kotlintest.R

class MainActivity : MainView, AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, AnkoLogger{



    lateinit var toolbar:   Toolbar
    lateinit var container: ViewGroup
    lateinit var drawer:    DrawerLayout
    lateinit var title:     TextView
    private lateinit var router: Router

    private val viewBinder = MainActivityLayout()
    private lateinit var navigator: Navigator



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinder.bind(this))
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        router = Conductor.attachRouter(this, container, savedInstanceState)
        navigator = PhoneNavigator(router) as Navigator
        navigator.showWelcome(true)

    }

    override fun onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_camera -> navigator.showTracking(false)
            R.id.nav_gallery -> navigator.showSignIn()
            R.id.nav_slideshow -> navigator.showSignUp()
            R.id.nav_manage -> navigator.showWelcome(false)
            R.id.nav_share -> debug("-> share")
            R.id.nav_send -> debug("-> send")
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun lockDrawer(lock: Boolean) {
        if (lock)
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        else
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    override fun showToolbar(show: Boolean) {
        if (show)
            toolbar.visibility = View.VISIBLE
        else
            toolbar.visibility = View.GONE
    }

}
