package com.nnk.springboot.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BidListServiceTest {

	@Autowired
	BidListService service;
	
	@Test
	public void bidListServiceTest() {
		BidList bid1 = new BidList("Account Test", "Type Test", 1d);
		BidList bid2 = new BidList("Account Test", "Type Test", 2d);

		// Save
		bid1 = service.saveBid(bid1);
		bid2 = service.saveBid(bid2);
		Assert.assertNotNull(bid1.getBidListId());
		Assert.assertNotNull(bid2.getBidListId());
		Assert.assertTrue(bid1.getBidQuantity() == 1d);
		Assert.assertTrue(bid2.getBidQuantity() == 2d);

		// Update
		bid1.setBidQuantity(11d);
		bid1 = service.saveBid(bid1);
		Assert.assertTrue(bid1.getBidQuantity() == 11d);

		// Find all
		List<BidList> listResult = service.getAllBids();
		Assert.assertTrue(listResult.size() >= 2);
		
		// Find by id
		Integer id2 = bid2.getBidListId();
		BidList result = service.getBidById(id2);
		Assert.assertEquals(bid2.getBidQuantity(),result.getBidQuantity());
		
		// Delete
		service.deleteBid(bid2);
		result = service.getBidById(id2);
		Assert.assertNull(result);
	}
	

}
