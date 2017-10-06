package com.sda.jspexample.controller;

import com.sda.jspexample.model.ModelAndViewWithMenu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class AuthorizationController {
    @RequestMapping(value = "/anonymous")
    public ModelAndView anonymousPage() {
        ModelAndView model = new ModelAndViewWithMenu();
        model.addObject("loggedUser", "anonymous");
        model.setViewName("anonymous.jsp");
        return model;
    }

    @RequestMapping(value = "/authenticated")
    public ModelAndView authenticatedPage(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        ModelAndView model = new ModelAndViewWithMenu();
        HttpSession session = request.getSession(false);

        if (session != null) {
            String userLogin = (String) session.getAttribute("userLogin");
            if (userLogin != null) {
                model.addObject("loggedUser", userLogin);
            }else{
                model.setViewName("error.jsp");
                return model;
            }
        } else {
            model.setViewName("error.jsp");
            return model;
        }


        model.setViewName("anonymous.jsp");
        return model;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("login") String login,
                        @ModelAttribute("password") String password,
                        HttpServletRequest request){
        //Logika sprawdzająca czy dane logowania są poprawne
        HttpSession session = request.getSession();
        session.setAttribute("userLogin", login);
        return "redirect:/";
    }
    @RequestMapping(value="/logout", method = RequestMethod.POST)
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }

}
