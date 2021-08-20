package com.project.orange.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private String response;
    private String message;
    private Object data1;
    private Object data2;
    private Object data3;

    public Response(String response, String message, Object data1, Object data2, Object data3) {
        this.response = response;
        this.message = message;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
    }

    public Response() {

    }
}