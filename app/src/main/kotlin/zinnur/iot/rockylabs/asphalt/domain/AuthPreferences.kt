package zinnur.iot.rockylabs.asphalt.domain

import android.content.SharedPreferences
import com.annimon.stream.Optional
import com.annimon.stream.function.Function

import com.google.gson.Gson

import zinnur.iot.rockylabs.asphalt.domain.model.UserAuthCredentialsModel

/**
 * Created by Zinnur on 04.04.17.
 */

class AuthPreferences(private val preferences: SharedPreferences, private val gson: Gson) {

    private val KEY_TOKEN = "TOKEN"


    private fun transact(action: Function<SharedPreferences.Editor, SharedPreferences.Editor>) {
        action.apply(preferences.edit()).apply()
    }

    fun clear() {
        transact(Function<SharedPreferences.Editor, SharedPreferences.Editor> { editor ->
            editor.clear()
            editor
        })
    }

    fun saveAuthCredentialsModel(model: UserAuthCredentialsModel) {
        transact(Function<SharedPreferences.Editor, SharedPreferences.Editor> { prefs ->
            prefs.putString(KEY_TOKEN, gson.toJson(model))
        })
    }

    val userAuthCredentials: UserAuthCredentialsModel?
        get() = Optional.ofNullable(preferences.getString(KEY_TOKEN, null))
                .map { it -> gson.fromJson(it, UserAuthCredentialsModel::class.java) }
                .orElse(null)


    fun isUserAuthorized(): Boolean = userAuthCredentials != null


}
