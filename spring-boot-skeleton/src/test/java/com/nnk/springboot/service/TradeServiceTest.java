package com.nnk.springboot.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.Trade;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeServiceTest {

	@Autowired
	TradeService service;
	
	@Test
	public void tradeServiceTest() {
		Trade trade1 = new Trade("Trade Account", "Type 1");
		Trade trade2 = new Trade("Trade Account", "Type 2");

		// Save
		trade1 = service.saveTrade(trade1);
		trade2 = service.saveTrade(trade2);
		Assert.assertNotNull(trade1.getTradeId());
		Assert.assertNotNull(trade2.getTradeId());
		Assert.assertEquals("Type 1",trade1.getType());
		Assert.assertEquals("Type 2",trade2.getType());

		// Update
		trade1.setType("Type 11");
		trade1 = service.saveTrade(trade1);
		Assert.assertEquals("Type 11",trade1.getType());

		// Find all
		List<Trade> listResult = service.getAllTrades();
		Assert.assertTrue(listResult.size() >= 2);
		
		// Find by id
		Integer id2 = trade2.getTradeId();
		Trade result = service.getTradeById(id2);
		Assert.assertEquals(trade2.getType(),result.getType());
		
		// Delete
		service.deleteTrade(trade2);
		result = service.getTradeById(id2);
		Assert.assertNull(result);
	}

}
