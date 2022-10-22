package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;

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
public class BidListController {
    
	Logger logger = LoggerFactory.getLogger(BidListController.class);
	
	@Autowired
    BidListService service;

    @RequestMapping("/bidList/list")
    public String home(Model model){
    	logger.info("GET : /bidList/list");
    	model.addAttribute("allbids",service.getAllBids());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
    	logger.info("GET : /bidList/add");
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
    	logger.info("POST : /bidList/validate");
        if (!result.hasErrors()) {
        	service.saveBid(bid);
        	model.addAttribute("allbids",service.getAllBids());
        	return "redirect:/bidList/list";
        }
    	return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	logger.info("GET : /bidList/update/"+id);
    	BidList bid = service.getBidById(id);
    	model.addAttribute("bidList",bid);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
    	logger.info("POST : /bidList/update/"+id);
        if (result.hasErrors()) {
        	return "/bidList/update";
        }
        bidList.setBidListId(id);
        service.saveBid(bidList);
    	model.addAttribute("allbids",service.getAllBids());
    	return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
    	logger.info("GET : /bidList/delete/"+id);
    	service.deleteBid(service.getBidById(id));
        return "redirect:/bidList/list";
    }
}
