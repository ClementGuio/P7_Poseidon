package com.nnk.springboot.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.repositories.TradeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class TradeControllerTest {

	@Autowired
	MockMvc mvc;
	
	@Autowired
	TradeRepository repo;
	
	Trade trade;
	
	@Test
	@WithMockUser(username = "user")
	public void showListTest() throws Exception {
		this.mvc.perform(get("/trade/list"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("alltrades"));
	}
	
	@Test
	@WithMockUser(username = "user")
	public void showAddFormTest() throws Exception{
		this.mvc.perform(get("/trade/add"))
				.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "user")
	public void showUpdateFormTest() throws Exception{
		trade = new Trade("TRADE TEST", "Type");
		trade = repo.save(trade);
		
		this.mvc.perform(get("/trade/update/"+trade.getTradeId().toString()))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("trade"));
		
		repo.delete(trade);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void postUpdateFormTest() throws Exception{
		trade = new Trade("TRADE TEST", "Type");
		trade = repo.save(trade);
		
		this.mvc.perform(post("/trade/update/"+trade.getTradeId().toString())
				.param("account", "TRADE TEST")
				.param("type", "NEW")
				.sessionAttr("trade", new Trade())
				.with(csrf()))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/trade/list"));
		
		trade = repo.findById(trade.getTradeId()).get();
		assertEquals("NEW",trade.getType());
		repo.delete(trade);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void postUpdateFormWithBlankAccountTest() throws Exception{
		trade = new Trade("TRADE TEST", "Type");
		trade = repo.save(trade);
		
		this.mvc.perform(post("/trade/update/"+trade.getTradeId().toString())
				.param("account", "")
				.param("type", "type")
				.sessionAttr("trade", new Trade())
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(model().hasErrors());
		
		repo.delete(trade);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void postUpdateFormWithBlankTypeTest() throws Exception{
		trade = new Trade("TRADE TEST", "Type");
		trade = repo.save(trade);
		
		this.mvc.perform(post("/trade/update/"+trade.getTradeId().toString())
				.param("account", "TRADE TEST")
				.param("type", "")
				.sessionAttr("trade", new Trade())
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(model().hasErrors());
		
		repo.delete(trade);
	}
		
	@Test
	@WithMockUser(username = "user")
	public void validateAddFormTest() throws Exception{
		this.mvc.perform(post("/trade/validate")
				.param("account", "TRADE TEST")
				.param("type", "type")
				.sessionAttr("trade", new Trade())
				.with(csrf()))
			.andExpect(status().isFound())
			.andExpect(model().hasNoErrors());
		
		List<Trade> res = repo.findByAccount("TRADE TEST");
		for (Trade trade : res) {
			repo.delete(trade);
		}
	}
	
	@Test
	@WithMockUser(username = "user")
	public void validateAddFormWithBlankAccountTest() throws Exception{
		this.mvc.perform(post("/trade/validate")
				.param("account", "")
				.param("type", "type")
				.sessionAttr("trade", new Trade())
				.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(model().hasErrors());
	}
	
	@Test
	@WithMockUser(username = "user")
	public void validateAddFormWithBlankTypeTest() throws Exception{
		this.mvc.perform(post("/trade/validate")
				.param("account", "TRADE TEST")
				.param("type", "")
				.sessionAttr("trade", new Trade())
				.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(model().hasErrors());
	}
	
	@Test
	@WithMockUser(username = "user")
	public void deleteTest() throws Exception{
		trade = new Trade("TRADE TEST", "Type");
		trade = repo.save(trade);
		Integer id = trade.getTradeId();
		
		this.mvc.perform(get("/trade/delete/"+id))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/trade/list"));
		
		assertFalse(repo.findById(id).isPresent());
	}
}
