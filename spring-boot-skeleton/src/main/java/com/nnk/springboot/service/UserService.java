package com.nnk.springboot.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repo;

	public List<User> getAllUsers() {
		return repo.findAll();
	}

	public User saveUser(User user) {
		return repo.save(user);
	}

	public User getUserById(Integer id) {
		Optional<User> opt = repo.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		return null;
	}

	public void deleteUser(User user) {
		repo.delete(user);
	}
}
