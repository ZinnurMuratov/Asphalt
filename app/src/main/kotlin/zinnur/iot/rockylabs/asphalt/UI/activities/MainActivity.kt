package zinnur.iot.rockylabs.asphalt.UI.activities

import zinnur.iot.rockylabs.asphalt.UI.anko.MainActivityLayout
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import org.jetbrains.anko.AnkoLogger
import zinnur.iot.rockylabs.asphalt.mvp.views.MainView
import zinnur.iot.rockylabs.asphalt.navigation.Navigator
import zinnur.iot.rockylabs.asphalt.navigation.PhoneNavigator
import kotlin.ru.rockylabs.kotlintest.R
import android.R.id.toggle



class MainActivity : MainView, AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, AnkoLogger{

    lateinit var toolbar:   Toolbar
    lateinit var container: ViewGroup
    lateinit var drawer:    DrawerLayout
    lateinit var title:     TextView
    private lateinit var router: Router

    private val viewBinder = MainActivityLayout()
    private lateinit var navigator: Navigator
    private lateinit var toggle: ActionBarDrawerToggle



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinder.bind(this))
        toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        router = Conductor.attachRouter(this, container, savedInstanceState)
        navigator = PhoneNavigator(router) as Navigator
        if (!router.hasRootController()){
            navigator.showWelcome(true)
        }

    }

    override fun onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_tracking -> navigator.showTracking(true)
            R.id.nav_feed -> navigator.showFeed(true)
            R.id.nav_settings -> navigator.showSettings(true)
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

    override fun changeTitle(title: String) {
        toolbar.title = title
    }

    override fun setHomeEnabled(enable: Boolean) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(enable)
        supportActionBar!!.setDisplayShowHomeEnabled(enable)
        supportActionBar!!.setHomeButtonEnabled(enable)
        if (!enable){
            toggle.isDrawerIndicatorEnabled = !enable
            syncToggle()
            toolbar.setNavigationOnClickListener { drawer.openDrawer(GravityCompat.START) }
        } else {
            toolbar.setNavigationOnClickListener { onBackPressed() }
        }

    }

    override fun syncToggle() {
        toggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            Log.d("sadsa", "sadsd")
            navigator.showTracking(true)
        }
        return super.onOptionsItemSelected(item)
    }

}
