package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class RatingController {
    
	Logger logger = LoggerFactory.getLogger(RatingController.class);
	
	@Autowired
    RatingService service;

    @RequestMapping("/rating/list")
    public String home(Model model){
    	logger.info("GET : /rating/list");
    	model.addAttribute("allratings",service.getAllRatings());
    	return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
    	logger.info("GET : /rating/add");
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
    	logger.info("POST : /rating/validate");
        if (!result.hasErrors()) {
        	service.saveRating(rating);
        	model.addAttribute("allratings",service.getAllRatings());
        	return "redirect:/rating/list";
        }
    	return "rating/add";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	logger.info("GET : /rating/update/"+id);
    	Rating rating = service.getRatingById(id);
    	model.addAttribute("rating",rating);
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
    	logger.info("POST : /rating/update/"+id);
        if (result.hasErrors()) {
        	return "/rating/update"; 
        }
        rating.setId(id);
        service.saveRating(rating);
    	model.addAttribute("allratings",service.getAllRatings());
    	return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
    	logger.info("GET : /rating/delete/"+id);
    	Rating rating = service.getRatingById(id);
    	service.deleteRating(rating);
        return "redirect:/rating/list";
    }
}
