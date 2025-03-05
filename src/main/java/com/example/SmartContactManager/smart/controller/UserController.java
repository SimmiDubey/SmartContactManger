package com.example.SmartContactManager.smart.controller;

import com.example.SmartContactManager.com.smart.dao.UserRepository;
import com.example.SmartContactManager.model.Contact;
import com.example.SmartContactManager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    //open add form handler

    @ModelAttribute
    public void addCommonData(Model m,Principal principal){

        String userName=principal.getName();
        System.out.println("Username" +userName);

        User user =userRepository.getUserByUserName(userName);
        System.out.println("User"+user);
        m.addAttribute("user",user);

    }

// dashboard home page
    @RequestMapping("/index")

    public String dashboard(Model model, Principal principal){

        return "normal/user_dashboard";
    }

    //open add form handler

    @GetMapping("add-contact")
    public String openAddContactForm(Model model){
        model.addAttribute("title","Add Contact");
        model.addAttribute("contact",new Contact());
        return "normal/add_contact_form";
    }




}
