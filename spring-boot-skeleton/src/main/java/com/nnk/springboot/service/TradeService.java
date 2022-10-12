package com.nnk.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@Service
public class TradeService {

	@Autowired
	TradeRepository repo;

	public List<Trade> getAllTrades() {
		return repo.findAll();
	}

	public Trade getTradeById(Integer id) {
		Optional<Trade> opt = repo.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		return null;
	}

	public void deleteTrade(Trade trade) {
		repo.delete(trade);
	}

	public Trade saveTrade(Trade trade) {
		return repo.save(trade);
	}
}
