package com.proman.domainmanager.model;

import java.util.List;

public class Response<T> {
    private int statusCode;
    private List<T> data;

    public Response(int value, Domain createdDomain) {
    }

    public Response(int statusCode, List<T> data) {
        this.statusCode = statusCode;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
