package com.qzero.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UnderDevelopController {

    @RequestMapping("/under_develop")
    public ModelAndView underDevelopHint(){
        return new ModelAndView("under_develop");
    }

}
