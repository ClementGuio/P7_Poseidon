package com.nnk.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

@Service
public class RatingService {

	@Autowired
	RatingRepository repo;
	
	public List<Rating> getAllRatings() {
		return repo.findAll();
	}
	
	public Rating getRatingById(Integer id) {
		Optional<Rating> opt = repo.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		return null;
	}
	
	public void deleteRating(Rating rating) {
		repo.delete(rating);
	}
	
	public Rating saveRating(Rating rating) {
		return repo.save(rating);
	}
}
