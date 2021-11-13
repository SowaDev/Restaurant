package com.restaurant.exception;

import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class InputNotValidException extends RuntimeException{
    private final BindingResult bindingResult;

    public InputNotValidException(BindingResult bindingResult){
        super("Input not valid");
        this.bindingResult = bindingResult;
    }
}
