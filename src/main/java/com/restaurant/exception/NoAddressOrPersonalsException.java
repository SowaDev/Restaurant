package com.restaurant.exception;

public class NoAddressOrPersonalsException extends RuntimeException {
    public NoAddressOrPersonalsException(){
        super("You haven't filled info about your address or your personals. Please fill them up to make an order");
    }
}
