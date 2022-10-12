package com.nnk.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@Service
public class BidListService {

	@Autowired
	BidListRepository repo;
	
	public List<BidList> getAllBids() {
		return repo.findAll();
	}

	public BidList getBidById(Integer id) {
		Optional<BidList> opt = repo.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		return null;
	}

	public void deleteBid(BidList bid) {
		repo.delete(bid);
		
	}
	
	public BidList saveBid(BidList bid) {
		return repo.save(bid);
	}

}
