package com.qzero.blog.controller;

import com.qzero.blog.data.Token;
import com.qzero.blog.exception.ResponsiveException;
import com.qzero.blog.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user_center")
public class UserCenterController {

    @Autowired
    private AuthService authService;

    @RequestMapping
    public ModelAndView userCenterView(@CookieValue(name = "token_id",defaultValue = "") String tokenIdStored) throws ResponsiveException {
        //Check token first
        Token token=authService.checkAndGetToken(tokenIdStored);


    }

}
