package com.example.siddhant.roommanagement.register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterResponsePojo {
    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("user")
    @Expose
    private String user;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
