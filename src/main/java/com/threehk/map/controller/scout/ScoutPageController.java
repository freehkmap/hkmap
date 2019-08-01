package com.threehk.map.controller.scout;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/scout")
@Controller
public class ScoutPageController {
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model) {
        return "scout/login";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        return "scout/home";
    }
}
