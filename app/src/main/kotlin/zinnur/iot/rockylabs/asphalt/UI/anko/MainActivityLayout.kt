package zinnur.iot.rockylabs.asphalt.UI.anko

import zinnur.iot.rockylabs.asphalt.UI.activities.MainActivity
import android.view.View
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import kotlin.ru.rockylabs.kotlintest.R
import android.support.design.widget.AppBarLayout
import org.jetbrains.anko.*

/**
 * Created by Zinnur on 12.01.17.
 */

class MainActivityLayout : ViewBinder<MainActivity> {
    override fun bind(ui: MainActivity): View  = ui.UI {
        coordinatorLayout {
            fitsSystemWindows = true

            appBarLayout {
                ui.toolbar = toolbar(R.style.AppTheme_PopupOverlay) {
                    ui.setSupportActionBar(this)
                    ui.supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }.lparams(width = matchParent, height = actionBarSize())
            }.lparams(width = matchParent)

            ui.container = frameLayout {
            }.lparams(width = matchParent, height = matchParent) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }

        }
    }.view

    override fun unbind(t: MainActivity) {
        t.toolbar = null!!
        t.container = null!!
    }

}
