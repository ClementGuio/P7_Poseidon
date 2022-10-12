package com.nnk.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@Service
public class RuleNameService {

	@Autowired
	RuleNameRepository repo;
	
	public List<RuleName> getAllRules() {
		return repo.findAll();
	}

	public RuleName getRuleById(Integer id) {
		Optional<RuleName> opt = repo.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		return null;
	}

	public void deleteRule(RuleName rule) {
		repo.delete(rule);
	}
	
	public RuleName saveRule(RuleName rule) {
		return repo.save(rule);
	}

}
