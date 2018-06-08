package com.piterpan.sipr.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Response<T> implements Serializable {
    /**
     * JsonProperty untuk mengambil properti di dalam json yang bernama "message"
     */
    @JsonProperty("message")
    private String mMessage; // Merupakan pesan tentang kembalian rest controller
    /**
     * JsonProperty untuk mengambil property di dalam json yang bernama "data"
     */
    @JsonProperty("data")
    private T mData; // Merupakan mData yang dikirim sebagai kembalian dari rest controller

    public Response() {
    }

    public Response(String message) {
        this(message, null);
    }

    public Response(String message, T data) {
        mMessage = message;
        mData = data;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public T getData() {
        return mData;
    }

    public void setData(T data) {
        mData = data;
    }

    @Override
    public String toString() {
        return mMessage + ", " + mData.toString();
    }
}
