package com.sda.jspexample.model;

import com.sda.jspexample.menu.MenuElement;
import org.springframework.web.servlet.ModelAndView;

public class ModelAndViewWithMenu extends ModelAndView{
    public ModelAndViewWithMenu(){
        super();
        addObject("menu", MenuElement.values());
    }
}
