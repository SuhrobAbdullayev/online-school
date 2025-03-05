package com.school.onlineschool.exeption;

import com.school.onlineschool.domain.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleUserException(RuntimeException exception) {
        String errorMessage = exception.getMessage();
        ResponseDTO<Object> responseDTO = ResponseDTO.error(errorMessage, null);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> Exception(Exception exception) {
        String errorMessage = exception.getMessage();
        ResponseDTO<Object> responseDTO = ResponseDTO.error(errorMessage, null);
        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
