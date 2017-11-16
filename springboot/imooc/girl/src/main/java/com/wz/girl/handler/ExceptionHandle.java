package com.wz.girl.handler;

import com.wz.girl.domain.Result;
import com.wz.girl.exception.GirlException;
import com.wz.girl.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = GirlException.class)
    @ResponseBody
    public Result handler(Exception e){
        if (e instanceof GirlException){
            GirlException girlException = (GirlException) e;
            return ResultUtil.error(girlException.getCode(), e.getMessage());
        }
        logger.error("[系统异常]{}", e);
        return ResultUtil.error(-1, "位置错误");
    }

}
