package com.nnk.springboot.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.domain.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserServiceTest {

	@Autowired
	UserService service;
	
	@Test
	public void tradeServiceTest() {
		User user1 = new User("user1","password","fullname","TEST");
		User user2 = new User("user2","password","fullname","TEST");

		// Save
		user1 = service.saveUser(user1);
		user2 = service.saveUser(user2);
		Assert.assertNotNull(user1.getId());
		Assert.assertNotNull(user2.getId());
		Assert.assertEquals("TEST",user1.getRole());
		Assert.assertEquals("TEST",user2.getRole());

		// Update
		user1.setRole("NEW");
		user1 = service.saveUser(user1);
		Assert.assertEquals("NEW",user1.getRole());

		// Find all
		List<User> listResult = service.getAllUsers();
		Assert.assertTrue(listResult.size() >= 2);
		
		// Find by id
		Integer id2 = user2.getId();
		User result = service.getUserById(id2);
		Assert.assertEquals(user2.getRole(),result.getRole());
		
		// Delete
		service.deleteUser(user2);
		result = service.getUserById(id2);
		Assert.assertNull(result);
	}


}
