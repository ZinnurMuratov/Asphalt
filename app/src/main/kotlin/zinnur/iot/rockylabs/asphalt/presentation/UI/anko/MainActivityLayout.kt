package zinnur.iot.rockylabs.asphalt.presentation.UI.anko

import zinnur.iot.rockylabs.asphalt.presentation.UI.activities.MainActivity
import android.support.design.widget.AppBarLayout
import android.support.v4.view.GravityCompat.*
import android.view.Gravity
import android.view.View
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.*
import org.jetbrains.anko.support.v4.drawerLayout
import kotlin.ru.rockylabs.kotlintest.R

/**
 * Created by Zinnur on 12.01.17.
 */

class MainActivityLayout : ViewBinder<MainActivity> {
    override fun bind(ui: MainActivity): View  = ui.UI {
        ui.drawer = drawerLayout {
            fitsSystemWindows = true
            coordinatorLayout {

                ui.container = frameLayout {
                }.lparams(width = matchParent, height = matchParent) {
                    behavior = AppBarLayout.ScrollingViewBehavior()
                }

                ui.toolbar = toolbar {
                    ui.setSupportActionBar(this)
                    elevation = 0f
                    color(android.R.color.transparent)
                    backgroundResource = android.R.color.transparent
                    ui.supportActionBar?.setDisplayShowTitleEnabled(false)
                    ui.supportActionBar?.setDisplayHomeAsUpEnabled(false)

                    ui.title = textView{
                        lparams(width = wrapContent, height = wrapContent){
                            gravity = Gravity.CENTER
                        }
                        textColorResource = R.color.white
                        textSize = sp(10).toFloat()
                        setFont("fonts/RobotoLight.ttf")
                    }

                }.lparams(width = matchParent, height = actionBarSize())



            }
            navigationView {
                val headerContext = AnkoContext.create(ctx, this)
                val headerView = NavHeaderComponent()
                        .createView(headerContext)
                        .lparams(width = matchParent, height = dimen(R.dimen.nav_header_height))
                addHeaderView(headerView)
                inflateMenu(R.menu.activity_main_drawer)
                if(!isInEditMode) {
                    setNavigationItemSelectedListener(ui)
                }
            }.lparams(height = matchParent) {
                gravity = START
            }

            if(isInEditMode){
                openDrawer(START)
            }
        }
    }.view

    override fun unbind(t: MainActivity) {
        t.toolbar = null!!
        t.container = null!!
        t.drawer = null!!
    }

}
