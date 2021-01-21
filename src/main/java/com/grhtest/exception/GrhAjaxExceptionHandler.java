package com.grhtest.exception;

import com.grhtest.pojo.GrhJsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author grh
 */
//@RestControllerAdvice
public class GrhAjaxExceptionHandler {

    //@ExceptionHandler(value = Exception.class)
    public GrhJsonResult errorHandler(HttpServletRequest request, Exception e) throws Exception{
        e.printStackTrace();
        return GrhJsonResult.errorException(e.getMessage());
    }
}
