package zinnur.iot.rockylabs.asphalt.data.entity.request

import com.google.gson.annotations.SerializedName



/**
 * Created by Zinnur on 30.03.17.
 */
class LoginRequestBody{

    @com.google.gson.annotations.SerializedName("email") private var email: String
    @com.google.gson.annotations.SerializedName("password") private var password: String


    constructor(email: String, password: String) {
        this.email = email
        this.password = password
    }


    fun getEmail(): String {
        return email
    }

    fun getPassword(): String {
        return password
    }
}