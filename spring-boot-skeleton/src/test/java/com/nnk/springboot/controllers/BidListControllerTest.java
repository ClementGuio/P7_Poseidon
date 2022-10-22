package com.nnk.springboot.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
//@WebAppConfiguration
@AutoConfigureMockMvc
public class BidListControllerTest {

	@Autowired
	MockMvc mvc;
	
	@Autowired
	BidListRepository repo;
	
	BidList bid;
	
	@Test
	@WithMockUser(username = "user")
	public void showListTest() throws Exception {
		this.mvc.perform(get("/bidList/list"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("allbids"));
	}
	
	@Test
	@WithMockUser(username = "user")
	public void showAddFormTest() throws Exception{
		this.mvc.perform(get("/bidList/add"))
				.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "user")
	public void showUpdateFormTest() throws Exception{
		bid = new BidList("acc","type",2.0);
		bid = repo.save(bid);
		
		this.mvc.perform(get("/bidList/update/"+bid.getBidListId().toString()))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("bidList"));
		
		repo.delete(bid);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void postUpdateFormTest() throws Exception{
		bid = new BidList("BIDLIST TEST","type",2.0);
		bid = repo.save(bid);
		
		this.mvc.perform(post("/bidList/update/"+bid.getBidListId().toString())
				.param("account", "BIDLIST TEST")
				.param("type", "new")
				.param("bidQuantity", "2.0")
				.sessionAttr("bidList", new BidList())
				.with(csrf()))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/bidList/list"));
		
		bid = repo.findById(bid.getBidListId()).get();
		assertEquals("new", bid.getType());
		repo.delete(bid);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void postUpdateFormWithBlankAccountTest() throws Exception{
		bid = new BidList("acc","type",2.0);
		bid = repo.save(bid);
		
		this.mvc.perform(post("/bidList/update/"+bid.getBidListId().toString())
				.param("account", "")
				.param("type", "type")
				.param("bidQuantity", "2.0")
				.sessionAttr("bidList", new BidList())
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(model().hasErrors());
		
		repo.delete(bid);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void postUpdateFormWithBlankTypeTest() throws Exception{
		bid = new BidList("acc","type",2.0);
		bid = repo.save(bid);
		
		this.mvc.perform(post("/bidList/update/"+bid.getBidListId().toString())
				.param("account", "BIDLIST TEST")
				.param("type", "")
				.param("bidQuantity", "2.0")
				.sessionAttr("bidList", new BidList())
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(model().hasErrors());
		
		repo.delete(bid);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void postUpdateFormWithBlankBidQuantityTest() throws Exception{
		bid = new BidList("acc","type",2.0);
		bid = repo.save(bid);
		
		this.mvc.perform(post("/bidList/update/"+bid.getBidListId().toString())
				.param("account", "BIDLIST TEST")
				.param("type", "type")
				.param("bidQuantity", "")
				.sessionAttr("bidList", new BidList())
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(model().hasErrors());
		
		repo.delete(bid);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void postUpdateFormWithBadBidQuantityTest() throws Exception{
		bid = new BidList("acc","type",2.0);
		bid = repo.save(bid);
		
		this.mvc.perform(post("/bidList/update/"+bid.getBidListId().toString())
				.param("account", "")
				.param("type", "type")
				.param("bidQuantity", "BAD")
				.sessionAttr("bidList", new BidList())
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(model().hasErrors());
		
		repo.delete(bid);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void validateAddFormTest() throws Exception{
		this.mvc.perform(post("/bidList/validate")
				.param("account", "BIDLIST TEST")
				.param("type", "type")
				.param("bidQuantity", "1")
				.flashAttr("bidList", new BidList())
				.with(csrf()))
			.andExpect(status().isFound())
			.andExpect(model().hasNoErrors());
		
		List<BidList> res = repo.findByAccount("accountTest");
		for (BidList bid : res) {
			repo.delete(bid);
		}
	}
	
	@Test
	@WithMockUser(username = "user")
	public void validateAddFormWithBlankAccountTest() throws Exception{
		this.mvc.perform(post("/bidList/validate")
				.param("account", "")
				.param("type", "type")
				.param("bidQuantity", "1")
				.sessionAttr("bidList", new BidList())
				.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(model().hasErrors());
		
	}
	
	@Test
	@WithMockUser(username = "user")
	public void validateAddFormWithBlankTypeTest() throws Exception{
		this.mvc.perform(post("/bidList/validate")
				.param("account", "BIDLIST TEST")
				.param("type", "")
				.param("bidQuantity", "1")
				.sessionAttr("bidList", new BidList())
				.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(model().hasErrors());
		
	}
	
	@Test
	@WithMockUser(username = "user")
	public void validateAddFormWithBlankBidQuantityTest() throws Exception{
		this.mvc.perform(post("/bidList/validate")
				.param("account", "BIDLIST TEST")
				.param("type", "type")
				.param("bidQuantity", "")
				.sessionAttr("bidList", new BidList())
				.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(model().hasErrors());
	}
	
	@Test
	@WithMockUser(username = "user")
	public void validateAddFormWithBadBidQuantityTest() throws Exception{
		this.mvc.perform(post("/bidList/validate")
				.param("account", "")
				.param("type", "type")
				.param("bidQuantity", "BAD")
				.sessionAttr("bidList", new BidList())
				.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(model().hasErrors());	
	}
	
	@Test
	@WithMockUser(username = "user")
	public void deleteTest() throws Exception{
		bid = new BidList("acc","type",2.0);
		bid = repo.save(bid);
		Integer id = bid.getBidListId();
		
		this.mvc.perform(get("/bidList/delete/"+id))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/bidList/list"));
		
		assertFalse(repo.findById(id).isPresent());
	}
}
