package com.nnk.springboot;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.repositories.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void tradeTest() {
		User user = new User("user","Password.8","fullname","TEST");
		// Save
		user = userRepository.save(user);
		Assert.assertNotNull(user.getId());
		Assert.assertTrue(user.getRole().equals("TEST"));

		// Update
		user.setRole("NEW");
		user = userRepository.save(user);
		Assert.assertTrue(user.getRole().equals("NEW"));

		// Find
		List<User> listResult = userRepository.findAll();
		Assert.assertTrue(listResult.size() >= 1);

		// Delete
		Integer id = user.getId();
		userRepository.delete(user);
		Optional<User> tradeList = userRepository.findById(id);
		Assert.assertFalse(tradeList.isPresent());
	}
}
