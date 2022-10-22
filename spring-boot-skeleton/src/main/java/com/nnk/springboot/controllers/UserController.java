package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);
	
    @Autowired
    private UserService service;

    @RequestMapping("/user/list")
    public String home(Model model){
    	logger.info("GET : /user/list");
        model.addAttribute("users", service.getAllUsers());
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(User bid) {
    	logger.info("GET : /user/add");
        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {
        logger.info("POST : /user/validate");
    	if (!result.hasErrors()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            service.saveUser(user);
            model.addAttribute("users", service.getAllUsers());
            return "redirect:/user/list";
        }
        return "user/add";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	logger.info("GET : /user/update/"+id);
    	User user = service.getUserById(id);
    	if (user==null){
    		throw new IllegalArgumentException("Invalid user Id:" + id);
    	}
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
        logger.info("POST : /user/update/"+id);
    	if (result.hasErrors()) {
            return "user/update";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        service.saveUser(user);
        model.addAttribute("users", service.getAllUsers());
        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        logger.info("GET : /user/delete/"+id);
    	User user = service.getUserById(id);
        if (user==null){
        	throw new IllegalArgumentException("Invalid user Id:" + id);
        }
        service.deleteUser(user);
        model.addAttribute("users", service.getAllUsers());
        return "redirect:/user/list";
    }
}
