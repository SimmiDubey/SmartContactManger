package com.example.SmartContactManager.smart.controller;

import com.example.SmartContactManager.com.smart.dao.UserRepository;
import com.example.SmartContactManager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

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
        model.addAttribute("user", new User());
        return "signup";
    }

    @RequestMapping("/do_register")
    public String registerUser(@ModelAttribute("user") User user,
                               @RequestParam(value="agreement",defaultValue = "false")
                               boolean agreement,Model model){
        try {
            if (!agreement) {
                System.out.println("You have not agreed the terms and conditions");
            }

            user.setRole("ROLE_USER");
            user.setEnabled(true);

            System.out.println("Agreement" + agreement);
            System.out.println("User" + user);

            User result = this.userRepository.save(user);
            model.addAttribute("user", result);
        }catch(Exception e){
            e.printStackTrace();
            model.addAttribute("user",user);


        }

        return "signup";
    }


}
