package com.example.pritam.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseApiResponse implements Serializable {
    @SerializedName("statusCode")
    private int error;

    @SerializedName("message")
    private String message;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public boolean isError() {
        return error == 1;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BaseApiResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                '}';
    }
}
