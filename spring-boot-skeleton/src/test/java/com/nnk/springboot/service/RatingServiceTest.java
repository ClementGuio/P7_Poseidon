package com.nnk.springboot.service;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.Rating;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingServiceTest {

	@Autowired
	RatingService service;
	
	@Test
	public void ratingServiceTest() {
		Rating rating1 = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 1);
		Rating rating2 = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 2);

		// Save
		rating1 = service.saveRating(rating1);
		rating2 = service.saveRating(rating2);
		Assert.assertNotNull(rating1.getId());
		Assert.assertNotNull(rating2.getId());
		Assert.assertTrue(rating1.getOrderNumber() == 1);
		Assert.assertTrue(rating2.getOrderNumber() == 2);

		// Update
		rating1.setOrderNumber(11);
		rating1 = service.saveRating(rating1);
		Assert.assertTrue(rating1.getOrderNumber() == 11);

		// Find all
		List<Rating> listResult = service.getAllRatings();
		Assert.assertTrue(listResult.size() >= 2);
		
		// Find by id
		Integer id2 = rating2.getId();
		Rating result = service.getRatingById(id2);
		Assert.assertEquals(rating2.getOrderNumber(),result.getOrderNumber());
		
		// Delete
		service.deleteRating(rating2);
		result = service.getRatingById(id2);
		Assert.assertNull(result);
	}
	
}
