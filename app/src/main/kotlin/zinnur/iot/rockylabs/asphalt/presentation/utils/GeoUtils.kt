package zinnur.iot.rockylabs.asphalt.presentation.utils

import android.location.Location
import android.util.Log
import org.jetbrains.anko.custom.async

/**
 * Created by Zinnur on 07.06.17.
 */

object GeoUtils {
    fun distance(lat1: Double, lat2: Double, lon1: Double,
                 lon2: Double, el1: Double = 0.0, el2: Double = 0.0): Double {

        val R = 6371

        val latDistance = Math.toRadians(lat2 - lat1)
        val lonDistance = Math.toRadians(lon2 - lon1)
        val a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2)
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        var distance = R.toDouble() * c * 1000.0

        val height = el1 - el2

        distance = Math.pow(distance, 2.0) + Math.pow(height, 2.0)

        return Math.sqrt(distance)
    }

    fun distanceMoreThanOneMetr(prev: Location, current: Location) : Boolean{
        var isMore: Boolean = false
        val distance = distance(prev.latitude, current.latitude, prev.longitude, current.longitude)
        Log.d("distance", "is " + distance)
        if (distance > 1) isMore = true
        return isMore
    }
}
