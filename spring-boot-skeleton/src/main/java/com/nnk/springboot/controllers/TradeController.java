package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;

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
public class TradeController {
    
	Logger logger = LoggerFactory.getLogger(TradeController.class);
	
	@Autowired
	TradeService service;

    @RequestMapping("/trade/list")
    public String home(Model model){
    	logger.info("GET : /trade/list");
    	model.addAttribute("alltrades", service.getAllTrades());
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
    	logger.info("GET : /trade/add");
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
    	logger.info("POST : /trade/validate");
    	if (!result.hasErrors()) {
    		service.saveTrade(trade);
    		model.addAttribute("alltrades", service.getAllTrades());
    		return "redirect:/trade/list";
    	}
        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	logger.info("GET : /trade/update/"+id);
    	model.addAttribute("trade",service.getTradeById(id));
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
    	logger.info("POST : /trade/update/"+id);
    	if (result.hasErrors()) {
    		return "trade/update";
    	}
    	trade.setTradeId(id);
    	service.saveTrade(trade);
    	model.addAttribute("alltrades", service.getAllTrades());
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
    	logger.info("GET : /trade/delete/"+id);
    	service.deleteTrade(service.getTradeById(id));
    	model.addAttribute("alltrades", service.getAllTrades());
        return "redirect:/trade/list";
    }
}
