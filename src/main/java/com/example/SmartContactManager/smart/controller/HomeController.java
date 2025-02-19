package com.example.SmartContactManager.smart.controller;

import com.example.SmartContactManager.com.smart.dao.UserRepository;
import com.example.SmartContactManager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("title","Home -Smart Contact manager");
           return "home";
    }

    @RequestMapping("/about")
    public String about(Model model){
        model.addAttribute("title","about -Smart Contact manager");
        return "about";
    }


    @RequestMapping("/signup")
    public String signup(Model model){
        model.addAttribute("title","Register -Smart Contact manager");
        return "signup";
    }


}
