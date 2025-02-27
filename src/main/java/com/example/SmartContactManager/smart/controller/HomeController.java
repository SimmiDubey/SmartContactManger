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
    public String home(Model model) {
        model.addAttribute("title", "Home - Smart Contact Manager");
        return "home";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About - Smart Contact Manager");
        return "about";
    }

    @RequestMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("title", "Register - Smart Contact Manager");
        model.addAttribute("user", new User());
        return "signup";
    }

    //login page handler

    @GetMapping("/sign")
    public String customLogin(Model model)
    {
        model.addAttribute("title","login page");
        return "login";








    }






    @RequestMapping("/do_register")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                               @RequestParam(value = "agreement", defaultValue = "false") boolean agreement,
                               Model model, BindingResult result1, HttpSession session) {



        try {
            // Handle agreement checkbox
            if (!agreement) {
                throw new Exception("You have not agreed to the terms and conditions");
            }

            // Check if there are validation errors
            if (result1.hasErrors()) {
                System.out.println("Validation errors: " + result1.toString());
                model.addAttribute("user", user);
                return "signup";  // return to signup page with validation errors
            }

            // Encrypt the password before saving the user
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            // Set default role and enabled flag
            user.setRole("ROLE_USER");
            user.setEnabled(true);

            // Save the user to the database
            userRepository.save(user);

            // Reset the user object in the model for a fresh signup
            model.addAttribute("user", new User());

            // Add success message to session
            session.setAttribute("message", new Message("Successfully Registered!", "alert-success"));
            return "signup";  // redirect to the signup page or success page

        } catch (Exception e) {
            // Log the error and return a failure message
            e.printStackTrace();
            model.addAttribute("user", user);
            session.setAttribute("message", new Message("Something went wrong! " + e.getMessage(), "alert-danger"));
            return "signup";  // return to signup page with error message





        }



    }
}
