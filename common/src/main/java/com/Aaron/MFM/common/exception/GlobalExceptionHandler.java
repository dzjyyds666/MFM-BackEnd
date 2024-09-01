package com.Aaron.MFM.common.exception;


import com.Aaron.MFM.common.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail();
    }

    @ExceptionHandler(MFMException.class)
    @ResponseBody
    public Result error(MFMException e){
        System.out.println(e.getMessage());
        return Result.fail(e.getCode(),e.getMessage());
    }
}
