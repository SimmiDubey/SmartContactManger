package com.example.SmartContactManager.smart.controller;

import com.example.SmartContactManager.com.smart.dao.UserRepository;
import com.example.SmartContactManager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @RequestMapping("/home")
    public String home(){
        return "home";
    }

}
