package com.restaurant.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

//  @ResponseStatus(value = HttpStatus.NOT_FOUND)
//public class NoSuchElementFoundException extends ResponseStatusException {
//    public NoSuchElementFoundException(int id){
//        super(HttpStatus.NOT_FOUND, "Item with id" + id + " not found.");
//    }

public class NoSuchElementFoundException extends RuntimeException {
    public NoSuchElementFoundException(long id){
        super("Item with id " + id + " not found.");
    }

}
