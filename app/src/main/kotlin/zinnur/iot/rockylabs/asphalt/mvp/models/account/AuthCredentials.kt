package zinnur.iot.rockylabs.asphalt.mvp.models.account

/**
 * Created by Zinnur on 13.02.17.
 */
class AuthCredentials(username: String, password: String) {

    var username: String internal set
    var password: String internal set

    init {
        this.username = username
        this.password = password
    }

}