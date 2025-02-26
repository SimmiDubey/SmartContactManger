package com.example.SmartContactManager.smart.controller;

import com.example.SmartContactManager.com.smart.dao.UserRepository;
import com.example.SmartContactManager.helper.Message;
import com.example.SmartContactManager.model.User;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


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
    public String registerUser(@Valid @ModelAttribute("user") User user,
                               @RequestParam(value="agreement",defaultValue = "false")
                               boolean agreement, Model model, BindingResult result1, HttpSession session){
        try {
            if (!agreement) {
                System.out.println("You have not agreed the terms and conditions");
                throw new Exception("You have not agreed the terms and conditions");
            }

            if(result1.hasErrors())
            {
                System.out.println("ERROR"+result1.toString());
                model.addAttribute("user",user);
                return "signup";
            }


            user.setRole("ROLE_USER");
            user.setEnabled(true);
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            System.out.println("Agreement" + agreement);
            System.out.println("User" + user);

            User result = this.userRepository.save(user);
            model.addAttribute("user",new User());
            session.setAttribute("message",new Message("Successfully Registered!!","alert-success"));
            return "signup";

        }catch(Exception e){
            e.printStackTrace();
            model.addAttribute("user",user);
            session.setAttribute("message",new Message("Something went wrong!!"+e.getMessage(),"alert-danger"));

            return "signup";
        }


    }

}
