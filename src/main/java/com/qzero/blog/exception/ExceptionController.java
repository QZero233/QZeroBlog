package com.qzero.blog.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.UndeclaredThrowableException;

@ControllerAdvice
public class ExceptionController{

    private final Logger log= LoggerFactory.getLogger(getClass());

    @ExceptionHandler
    public ModelAndView handleException(HttpServletRequest request,
                                        Throwable e){
        log.error(e.getMessage(),e);

        ResponsiveException responsiveException=processException(e);
        ModelAndView modelAndView=new ModelAndView("error");
        modelAndView.addObject("reason",responsiveException.getErrorMessage());

        return modelAndView;
    }

    private ResponsiveException processException(Throwable e){
        if(e instanceof UndeclaredThrowableException){
            Throwable undeclared=((UndeclaredThrowableException) e).getUndeclaredThrowable();
            if(undeclared!=null && !(undeclared instanceof UndeclaredThrowableException))
                return processException(undeclared);
        }

        if(e instanceof ResponsiveException){
            return (ResponsiveException) e;
        }else{
            return new ResponsiveException(ErrorCodeList.UNKNOWN_REASON,"未知原因引发的错误，详细信息为："+e.getMessage());
        }
    }
}
