package com.ufkunl.workshop.exception;

import com.ufkunl.workshop.util.GenericServiceResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Detecting wrong argument parameters and return an error message to users to be corrected it.
     *
     * @param ex
     * @param request
     * @return
     */

    @ExceptionHandler(value = {PlaceNotFound.class})
    protected GenericServiceResult handleRuntimeProductException(Exception ex, WebRequest request) {
        String message = ex.getMessage();
        return new GenericServiceResult(false,message,null);
    }

}
