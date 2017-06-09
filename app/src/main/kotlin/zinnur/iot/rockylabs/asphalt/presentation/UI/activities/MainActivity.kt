package zinnur.iot.rockylabs.asphalt.presentation.UI.activities

import android.app.Activity
import zinnur.iot.rockylabs.asphalt.presentation.UI.anko.MainActivityLayout
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
import android.widget.LinearLayout
import android.widget.TextView
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.AnkoLogger
import zinnur.iot.rockylabs.asphalt.presentation.mvp.views.MainView
import zinnur.iot.rockylabs.asphalt.presentation.navigation.Navigator
import zinnur.iot.rockylabs.asphalt.presentation.navigation.PhoneNavigator
import kotlin.ru.rockylabs.kotlintest.R
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import com.kelvinapps.rxfirebase.RxFirebaseAuth
import com.kelvinapps.rxfirebase.RxFirebaseUser
import com.miguelbcr.ui.rx_paparazzo2.RxPaparazzo
import com.miguelbcr.ui.rx_paparazzo2.entities.size.Size
import com.yalantis.ucrop.UCrop
import org.jetbrains.anko.doAsync
import zinnur.iot.rockylabs.asphalt.domain.AuthPreferences
import zinnur.iot.rockylabs.asphalt.presentation.AsphaltApp
import zinnur.iot.rockylabs.asphalt.presentation.mvp.presenters.MainActivityPresenter
import javax.inject.Inject


class MainActivity  : MvpActivity<MainView, MainActivityPresenter>(), MainView, NavigationView.OnNavigationItemSelectedListener, AnkoLogger{



    override fun createPresenter()= AsphaltApp.getComponent(applicationContext).mainPresenter()


    lateinit var toolbar:   Toolbar
    lateinit var container: ViewGroup
    lateinit var drawer:    DrawerLayout
    lateinit var title:     TextView
    lateinit var userName:  TextView
    lateinit var email:     TextView
    private lateinit var router: Router

    private val viewBinder = MainActivityLayout()
    private lateinit var navigator: Navigator
    private lateinit var toggle: ActionBarDrawerToggle


    @Inject
    lateinit var authPreferences: AuthPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinder.bind(this))
        AsphaltApp.component.inject(this)
        toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        router = Conductor.attachRouter(this, container, savedInstanceState)
        navigator = PhoneNavigator(router) as Navigator
        authenticateAnon()
        setStartController()

    }


    private fun setStartController(){
        if (!router.hasRootController()){
            doAsync {
                val res = authPreferences.isUserAuthorized()
                Log.d("auth", " " + authPreferences.userAuthCredentials?.refreshToken)
                runOnUiThread{
                    if (res) {
                        showMain()
                    } else {
                        showAuth()
                    }
                }
            }

        }
    }

    private fun  authenticateAnon() {
           RxFirebaseAuth
                   .signInAnonymously(FirebaseAuth.getInstance())
                   .flatMap { it -> RxFirebaseUser.getToken(FirebaseAuth.getInstance().currentUser!!, false) }
                   .subscribe({
                       it -> Log.d("firebase auth", " " + it)
                   }, {
                       it -> it.printStackTrace()
                   } )
    }

    override fun getUser() {
        presenter.getUser()
    }

    override fun setUserData(name: String, email: String) {
        this.userName.text = name
        this.email.text = email
    }

    private fun showMain() {
        navigator.showTracking(true)
        presenter.getUser()
    }

    private fun showAuth(){
        navigator.showWelcome(true)
    }



    override fun onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_tracking -> navigator.showTracking(true)
            R.id.nav_map -> navigator.showMap(true)
            R.id.nav_potholes_feed -> navigator.showPotholeFeed(true)
            R.id.nav_settings -> navigator.showSettings(true)
            R.id.nav_feed -> navigator.showFeed(true)
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
            navigator.showTracking(true)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun pickSingle(options: UCrop.Options?, size: Size): RxPaparazzo.SingleSelectionBuilder<MainActivity> {
        
        val resized = RxPaparazzo.single(this)
                .sendToMediaScanner()
                .size(size)

        if (options != null) {
            resized.crop(options)
        }
        return resized
    }

}
