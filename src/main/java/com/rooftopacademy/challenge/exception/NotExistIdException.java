package com.rooftopacademy.challenge.exception;

public class NotExistIdException extends RuntimeException{
    public NotExistIdException() { }
    public NotExistIdException(String message) { super(message); }
}
