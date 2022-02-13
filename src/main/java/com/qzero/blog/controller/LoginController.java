package com.qzero.blog.controller;

import com.qzero.blog.RedirectionTarget;
import com.qzero.blog.data.Token;
import com.qzero.blog.data.User;
import com.qzero.blog.exception.ErrorCodeList;
import com.qzero.blog.exception.ResponsiveException;
import com.qzero.blog.service.AuthService;
import com.qzero.blog.utils.SHA256Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthService authService;

    @RequestMapping()
    public ModelAndView loginView(@CookieValue(name = "token_id",defaultValue = "") String tokenIdStored){
        //Check if already logged in
        if(!tokenIdStored.equals("")){
            //Already logged in, jump to user center
            return new ModelAndView("redirect","target",
                    new RedirectionTarget("/user_center","用户中心","您已经登录了"));
        }else{
            //Get to login page
            return new ModelAndView("login");
        }
    }

    @RequestMapping("/action")
    public ModelAndView loginAction(@RequestParam("username") String username,
                                    @RequestParam("password") String password,
                                    @RequestParam("expired_time") int expiredTimeDelta,
                                    HttpServletResponse httpServletResponse) throws ResponsiveException {
        User user=new User(username, SHA256Utils.getHexEncodedSHA256(password),false,null,null);
        long expiredTime=System.currentTimeMillis()+expiredTimeDelta;
        if(expiredTimeDelta<=0)
            expiredTime=-1;

        Token token=authService.login(user,expiredTime);

        //Save to cookie
        Cookie cookie=new Cookie("token_id",token.getTokenId());
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);

        //Go to user center
        return new ModelAndView("redirect","target",
                new RedirectionTarget("/user_center","用户中心","登录成功"));
    }

    @RequestMapping("/logout")
    public ModelAndView logoutAction(@CookieValue(name = "token_id",defaultValue = "") String tokenIdStored,
                                     HttpServletResponse httpServletResponse) throws ResponsiveException {
        if(tokenIdStored.equals("")){
            throw new ResponsiveException(ErrorCodeList.INVALID_TOKEN,"您未登录");
        }

        //Delete cookie
        Cookie cookie=new Cookie("token_id",null);
        cookie.setMaxAge(0);
        //cookie.setPath("/");
        httpServletResponse.addCookie(cookie);

        //Delete from db
        authService.logout(new Token(tokenIdStored,null,0));

        return new ModelAndView("redirect","target",
                new RedirectionTarget("/","首页","退出成功"));
    }

}
