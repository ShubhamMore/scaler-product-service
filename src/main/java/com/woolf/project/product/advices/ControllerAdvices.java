package com.woolf.project.product.advices;

import com.woolf.project.product.dto.ExceptionDTO;
import com.woolf.project.product.exceptions.InsufficientStockException;
import com.woolf.project.product.exceptions.InvalidDataException;
import com.woolf.project.product.exceptions.ProductNotExistException;
import com.woolf.project.product.exceptions.ResourceAccessForbiddenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvices {

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<ExceptionDTO> handleRuntimeException(RuntimeException ex){
        ExceptionDTO exceptionDto = new ExceptionDTO(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        //ex.printStackTrace();
        return new ResponseEntity<>(exceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductNotExistException.class)
    ResponseEntity<ExceptionDTO> handleNotFoundException(ProductNotExistException ex){
        ExceptionDTO exceptionDto = new ExceptionDTO(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidDataException.class)
    ResponseEntity<ExceptionDTO> handleInvalidException(InvalidDataException ex){
        ExceptionDTO exceptionDto = new ExceptionDTO(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsufficientStockException.class)
    ResponseEntity<ExceptionDTO> handleInsufficientException(InsufficientStockException ex){
        ExceptionDTO exceptionDto = new ExceptionDTO(HttpStatus.BAD_REQUEST, ex.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceAccessForbiddenException.class)
    ResponseEntity<ExceptionDTO> handleForbiddenException(ResourceAccessForbiddenException ex){
        ExceptionDTO exceptionDto = new ExceptionDTO(HttpStatus.FORBIDDEN, ex.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.FORBIDDEN);
    }

}