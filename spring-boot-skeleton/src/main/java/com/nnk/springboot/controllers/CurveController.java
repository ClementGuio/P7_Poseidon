package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;

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
public class CurveController {
    
	Logger logger = LoggerFactory.getLogger(CurveController.class);
	
	@Autowired
    CurvePointService service;

    @RequestMapping("/curvePoint/list")
    public String home(Model model){
    	logger.info("GET : /curvePoint/list");
    	model.addAttribute("allcurves", service.getAllCurves());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
    	logger.info("GET : /curvePoint/add");
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
    	logger.info("POST : /curvePoint/validate");
    	if (!result.hasErrors()) {
    		service.saveCurve(curvePoint);
        	model.addAttribute("allcurves", service.getAllCurves());
        	return "redirect:/curvePoint/list";
    	}
        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	logger.info("GET : /curvePoint/update/"+id);
    	model.addAttribute("curvePoint", service.getCurveById(id));
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        logger.info("POST : /curvePoint/update/"+id);
    	if (result.hasErrors()) {
        	return "/curvePoint/update";
        }
        curvePoint.setId(id);
        service.saveCurve(curvePoint);
    	model.addAttribute("allcurves", service.getAllCurves());
    	return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
    	logger.info("GET : /curvePoint/delete/"+id);
    	service.deleteCurve(service.getCurveById(id));
        return "redirect:/curvePoint/list";
    }
}
