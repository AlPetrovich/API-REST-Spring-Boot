package com.blog.exceptions;
import com.blog.dtos.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.util.Date;

@ControllerAdvice // Notacion para indicar que esta clase manejará la captura de todas las exceptions (GLOBALES)
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class) //Notacion para manejar las exceptions resource Not Found Exceptions
    public ResponseEntity<ErrorDetails> manageResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogAppException.class)
    public ResponseEntity<ErrorDetails> manageBlogAppException(BlogAppException exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> manageGlobalException(Exception exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
