package com.example.SmartContactManager.smart.controller;

import com.example.SmartContactManager.com.smart.dao.UserRepository;
import com.example.SmartContactManager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;


    @RequestMapping("/index")

    public String dashboard(Model model, Principal principal){
        String userName=principal.getName();
        System.out.println("Username" +userName);

        User user =userRepository.getUserByUserName(userName);
        System.out.println("User"+user);
        model.addAttribute("user",user);
        return "normal/user_dashboard";
    }

}
