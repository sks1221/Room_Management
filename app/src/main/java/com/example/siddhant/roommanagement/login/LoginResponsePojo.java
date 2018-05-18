package com.example.siddhant.roommanagement.login;

import com.example.siddhant.roommanagement.login.LoginDataPojo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponsePojo {

    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("data")
    @Expose
    private LoginDataPojo data;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public LoginDataPojo getData() {
        return data;
    }

    public void setData(LoginDataPojo data) {
        this.data = data;
    }

}
