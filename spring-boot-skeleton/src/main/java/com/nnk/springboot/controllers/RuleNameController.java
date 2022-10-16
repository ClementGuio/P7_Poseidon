package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;

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
public class RuleNameController {
    @Autowired
    RuleNameService service;

    @RequestMapping("/ruleName/list")
    public String home(Model model){
    	model.addAttribute("allrulenames", service.getAllRules());
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        if (!result.hasErrors()) {
        	service.saveRule(ruleName);
        	model.addAttribute("allrulenames", service.getAllRules());
        	return "redirect:/ruleName/list";
        }
    	return "ruleName/add";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	RuleName rule = service.getRuleById(id);
    	model.addAttribute("rule", rule);
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
    	if (result.hasErrors()) {
    		return "ruleName/update";
    	}
    	ruleName.setId(id);
    	service.saveRule(ruleName);
    	model.addAttribute("allrulenames", service.getAllRules());
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
    	service.deleteRule(service.getRuleById(id));
    	model.addAttribute("allrulenames", service.getAllRules());
        return "redirect:/ruleName/list";
    }
}
