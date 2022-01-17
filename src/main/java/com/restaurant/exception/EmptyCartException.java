package com.restaurant.exception;

public class EmptyCartException extends RuntimeException{
    public EmptyCartException(){
        super("Your cart is empty. It's not able to make an order with an empty cart.");
    }
}
