package zinnur.iot.rockylabs.asphalt.presentation.UI.anko

import android.os.Build
import android.support.design.R.style.TextAppearance_AppCompat_Body1
import android.support.design.R.style.ThemeOverlay_AppCompat_Dark
import android.support.design.widget.NavigationView
import android.view.Gravity.*
import android.view.View
import android.widget.LinearLayout.*
import org.jetbrains.anko.*
import kotlin.ru.rockylabs.kotlintest.R

/**
 * Created by Zinnur on 15.02.17.
 */


class NavHeaderComponent : AnkoComponent<NavigationView> {
    override fun createView(ui: AnkoContext<NavigationView>): View = with(ui) {
        verticalLayout(ThemeOverlay_AppCompat_Dark) {
            gravity = BOTTOM

            verticalPadding = dimen(R.dimen.activity_vertical_margin)
            horizontalPadding = dimen(R.dimen.activity_horizontal_margin)

            backgroundResource = R.drawable.wellcome_bg

            textView(""){
                tag = "username"
                topPadding = dimen(R.dimen.nav_header_vertical_spacing)
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setTextAppearance(TextAppearance_AppCompat_Body1)
                } else {
                    @Suppress("DEPRECATION")
                    setTextAppearance(this.context, TextAppearance_AppCompat_Body1)
                }
            }

            textView(""){
                tag = "email"
            }
        }
    }
}