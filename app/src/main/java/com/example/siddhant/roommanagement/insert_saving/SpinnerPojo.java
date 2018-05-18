package com.example.siddhant.roommanagement.insert_saving;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class SpinnerPojo {


    @SerializedName("data")
    @Expose
    private List<SpinnerDataPojo> data = null;
    @SerializedName("response")
    @Expose
    private String response;

    public List<SpinnerDataPojo> getData() {
        return data;
    }

    public void setData(List<SpinnerDataPojo> data) {
        this.data = data;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
