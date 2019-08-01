package com.threehk.map.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {
    @RequestMapping(value = {"/","/home","/login"},method = RequestMethod.GET)
    public String login(Model model){
        return "login";
    }
}
