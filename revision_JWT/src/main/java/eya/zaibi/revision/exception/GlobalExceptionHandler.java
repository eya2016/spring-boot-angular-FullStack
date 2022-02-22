package eya.zaibi.revision.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<?> resource_not_found(ResourceNotFound ex){
        ResponseMessage response = new ResponseMessage(
                LocalDateTime.now(),ex.getMessage()
        );
        return ResponseEntity.ok().body(response);
    }

   @ExceptionHandler(Exception.class)
    public ResponseEntity<?> error_system(Exception ex){
        ResponseMessage response = new ResponseMessage(
                LocalDateTime.now(), "Internal Server Error");
        return ResponseEntity.internalServerError().body(response);
    }

}