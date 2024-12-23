package com.tject.controller;
import com.tjetc.common.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandleController {
    @ExceptionHandler({Exception.class})
    public JsonResult processException(Exception e) {
       e.printStackTrace();
       log.error(e.toString());
        return JsonResult.fail("服务器异常，联系管理员");
    }

}
