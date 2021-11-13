package com.restaurant.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class ErrorResponse {
    private String statusCode;
    private List<String> messages;

    public ErrorResponse(String statusCode, List<String> messages){
        this.statusCode = statusCode;
        this.messages = messages;
    }

//    public ErrorResponse(String statusCode, String errorContent, String messages) {
//        this.statusCode = statusCode;
//        this.errorContent = errorContent;
//        this.messages = new ArrayList<>();
//        this.messages.add(messages);
//    }

//    public ErrorResponse(String statusCode, String errorContent, List<String> messages) {
//        this.statusCode = statusCode;
//        this.errorContent = errorContent;
//        this.messages = messages;
//    }

//    public ErrorResponse(Object input, List<String> messages){
//        this.input = input;
//        this.messages = messages;
//    }

}
