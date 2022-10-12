package com.nnk.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@Service
public class CurvePointService {

	@Autowired
	CurvePointRepository repo;
	
	public List<CurvePoint> getAllCurves() {
		return repo.findAll();
	}

	public CurvePoint getCurveById(Integer id) {
		Optional<CurvePoint> opt = repo.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		return null;
	}

	public void deleteCurve(CurvePoint curve) {
		repo.delete(curve);
	}
	
	public CurvePoint saveCurve(CurvePoint curve) {
		return repo.save(curve);
	}

}
