package BunnyCafeIsland.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class APIExceptionHandler {

    //Catch All Exception, Global
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException (Exception exception){

        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exception.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }


    //Ignore this
    /* Basic not found exception
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException (BadRequestException exception){
        
        //Create ErrorResponse object

        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        //Return ResponseEntity
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }*/
}
