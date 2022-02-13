package com.qzero.blog.controller;

import com.qzero.blog.data.Token;
import com.qzero.blog.exception.ResponsiveException;
import com.qzero.blog.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    @Autowired
    private AuthService authService;

    @RequestMapping
    public ModelAndView indexView(@CookieValue(name = "token_id", defaultValue = "")String tokenId,
                                  HttpServletResponse httpServletResponse){
        ModelAndView modelAndView=new ModelAndView("index");

        //Which means logged in
        if(!tokenId.equals("")){
            try {
                Token token=authService.checkAndGetToken(tokenId);
                modelAndView.addObject("username",token.getUsername());
            }catch (ResponsiveException e){
                //Delete cookie
                Cookie cookie=new Cookie("token_id",null);
                cookie.setMaxAge(0);
                httpServletResponse.addCookie(cookie);
            }
        }

        return modelAndView;
    }

}
