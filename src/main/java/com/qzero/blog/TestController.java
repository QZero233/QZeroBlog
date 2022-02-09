package com.qzero.blog;

import com.qzero.blog.data.Passage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping("/test")
public class TestController {

    @RequestMapping("/test")
    public ModelAndView test(){
        List<Passage> passageList=new ArrayList<>();

        for(int i=1;i<=20;i++){
            Passage passage=new Passage(i+"","文章"+i,Long.valueOf(0),"这是文章内容");
            passageList.add(passage);
        }

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("redirect");
        modelAndView.addObject("passages",passageList);
        modelAndView.addObject("target",new RedirectionTarget("/login","登录区","同志，该登录了"));

        return modelAndView;
    }

}
