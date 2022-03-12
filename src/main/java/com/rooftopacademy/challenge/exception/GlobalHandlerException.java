package com.rooftopacademy.challenge.exception;

import com.rooftopacademy.challenge.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalHandlerException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidException.class)
    public ResponseEntity<ErrorDTO> validException(RuntimeException e){
        return new ResponseEntity<>(new ErrorDTO(true, e.getMessage(), HttpStatus.BAD_REQUEST.value()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HashErrorException.class)
    public ResponseEntity<ErrorDTO> hasHErrorException(RuntimeException e){
        return new ResponseEntity<>(new ErrorDTO(true, e.getMessage(), HttpStatus.BAD_REQUEST.value()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotExistIdException.class)
    public ResponseEntity<ErrorDTO> notExistIdException(RuntimeException e){
        return new ResponseEntity<>(new ErrorDTO(true, e.getMessage(), HttpStatus.NOT_FOUND.value()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SubStringErrorException.class)
    public ResponseEntity<ErrorDTO> subStringErrorException(RuntimeException e){
        return new ResponseEntity<>(new ErrorDTO(true, e.getMessage(), HttpStatus.NOT_FOUND.value()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotExistPageException.class)
    public ResponseEntity<ErrorDTO> notExistPageException(RuntimeException e){
        return new ResponseEntity<>(new ErrorDTO(true, e.getMessage(), HttpStatus.NOT_FOUND.value()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotExistCharsException.class)
    public ResponseEntity<ErrorDTO> notExistCharsException(RuntimeException e){
        return new ResponseEntity<>(new ErrorDTO(true, e.getMessage(), HttpStatus.NOT_FOUND.value()),
                HttpStatus.NOT_FOUND);
    }
}
