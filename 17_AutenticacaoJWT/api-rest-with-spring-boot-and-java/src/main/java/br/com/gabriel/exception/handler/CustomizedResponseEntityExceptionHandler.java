package br.com.gabriel.exception.handler;

import br.com.gabriel.exception.ExceptionResponse;
import br.com.gabriel.exception.RequiredObjectIsNullException;
import br.com.gabriel.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(RequiredObjectIsNullException.class)
    public final ResponseEntity<ExceptionResponse> handleRequiredObjectIsNullException(RequiredObjectIsNullException ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

}

