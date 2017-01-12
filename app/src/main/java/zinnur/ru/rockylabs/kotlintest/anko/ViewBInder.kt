package zinnur.ru.rockylabs.kotlintest.anko

import android.view.View

/**
 * Created by Zinnur on 12.01.17.
 */

interface ViewBInder<in T> {
    fun bind(t: T) : View
    fun unbind(t: T)
}
