package zinnur.iot.rockylabs.asphalt.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Zinnur on 04.04.17.
 */

public class AbsResponseEntity {

    private final @SerializedName("status") String status;


    public AbsResponseEntity(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
