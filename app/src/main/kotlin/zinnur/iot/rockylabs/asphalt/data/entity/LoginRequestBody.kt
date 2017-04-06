package zinnur.iot.rockylabs.asphalt.data.entity

import com.google.gson.annotations.SerializedName



/**
 * Created by Zinnur on 30.03.17.
 */
class LoginRequestBody{

    @SerializedName("email") private var email: String
    @SerializedName("password") private var password: String


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