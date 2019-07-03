package com.alansystems.bookmaking.exceptions;

public class IncorrectValueException extends RuntimeException {
    public IncorrectValueException() {
        super("The bet contains an invalid value in the outcome field. The bet has not been added.");
    }
}