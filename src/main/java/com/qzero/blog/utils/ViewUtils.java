package com.qzero.blog.utils;

import com.qzero.blog.RedirectionTarget;
import org.springframework.web.servlet.ModelAndView;

public class ViewUtils {

    public static ModelAndView getRedirectView(RedirectionTarget target){
        return new ModelAndView("redirect","target",target);
    }

}
