package com.training.usermanagement.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class UserServiceExceptionsHandler {

    private final UserServiceExceptionMsgs userServiceExceptionMsgs;
    private static final ErrorInfo DEFAULT_ERROR_INFO=new ErrorInfo(-1,400,new HashMap(){{put(Lang.EN,"General Error");}});

    public UserServiceExceptionsHandler(UserServiceExceptionMsgs userServiceExceptionMsgs) {
        this.userServiceExceptionMsgs = userServiceExceptionMsgs;
    }

    @ExceptionHandler(AbstractUserServiceException.class)
    public ResponseEntity<Object> handle(AbstractUserServiceException exc){
        exc.printStackTrace();
        ErrorInfo errorInfo = userServiceExceptionMsgs.getExceptionMessages().getOrDefault(exc.getClass().getSimpleName(),DEFAULT_ERROR_INFO);

        return new ResponseEntity(errorInfo,new HttpHeaders(), errorInfo.getStatusCode());
    }
//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<Object> handle(UserNotFoundException exc){
//
//        return new ResponseEntity<>("User Exception", HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handle(RuntimeException exc){
        exc.printStackTrace();

        ErrorInfo errorInfo = userServiceExceptionMsgs.getExceptionMessages().getOrDefault(RuntimeException.class.getSimpleName(),DEFAULT_ERROR_INFO);
        return new ResponseEntity<>(errorInfo, new HttpHeaders(),errorInfo.getStatusCode());
    }


}
