package com.rooftopacademy.challenge.exception;

public class NotExistPageException extends RuntimeException{
    public NotExistPageException() { }
    public NotExistPageException(String message) { super(message); }
}
