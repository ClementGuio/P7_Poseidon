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
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.repositories.RatingRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class RatingControllerTest {

	@Autowired
	MockMvc mvc;
	
	@Autowired
	RatingRepository repo;
	
	Rating rating;
	
	@Test
	@WithMockUser(username = "user")
	public void showListTest() throws Exception {
		this.mvc.perform(get("/rating/list"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("allratings"));
	}
	
	@Test
	@WithMockUser(username = "user")
	public void showAddFormTest() throws Exception{
		this.mvc.perform(get("/rating/add"))
				.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "user")
	public void showUpdateFormTest() throws Exception{
		rating = new Rating("moodys","sand","fitch",777);
		rating = repo.save(rating);
		
		this.mvc.perform(get("/rating/update/"+rating.getId().toString()))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("rating"));
		
		repo.delete(rating);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void postUpdateFormTest() throws Exception{
		rating = new Rating("moodys","sand","fitch",777);
		rating = repo.save(rating);
		
		this.mvc.perform(post("/rating/update/"+rating.getId().toString())
				.param("moodysRating", "moodys")
				.param("sandPRating", "sand")
				.param("fitchRating", "NEW")
				.param("orderNumber", "777")
				.sessionAttr("rating", new Rating())
				.with(csrf()))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/rating/list"));
		
		rating = repo.findById(rating.getId()).get();
		assertEquals("NEW",rating.getFitchRating());
		repo.delete(rating);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void postUpdateFormWithBadNumberTest() throws Exception{
		rating = new Rating("moodys","sand","fitch",777);
		rating = repo.save(rating);
		
		this.mvc.perform(post("/rating/update/"+rating.getId().toString())
				.param("moodysRating", "moodys")
				.param("sandPRating", "sand")
				.param("fitchRating", "fitch")
				.param("orderNumber", "BAD")
				.sessionAttr("rating", new Rating())
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(model().hasErrors());
		
		repo.delete(rating);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void validateAddFormTest() throws Exception{
		this.mvc.perform(post("/rating/validate")
				.param("moodysRating", "moodys")
				.param("sandPRating", "sand")
				.param("fitchRating", "fitch")
				.param("orderNumber", "777")
				.sessionAttr("rating", new Rating())
				.with(csrf()))
			.andExpect(status().isFound())
			.andExpect(model().hasNoErrors());
		
		List<Rating> res = repo.findByOrderNumber(777);
		for (Rating rating : res) {
			repo.delete(rating);
		}
	}
	
	@Test
	@WithMockUser(username = "user")
	public void validateAddFormWithBadNumberTest() throws Exception{
		this.mvc.perform(post("/rating/validate")
				.param("moodysRating", "moodys")
				.param("sandPRating", "sand")
				.param("fitchRating", "fitch")
				.param("orderNumber", "BAD")
				.sessionAttr("rating", new Rating())
				.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(model().hasErrors());	
	}
	
	@Test
	@WithMockUser(username = "user")
	public void deleteTest() throws Exception{
		rating = new Rating("moodys","sand","fitch",777);
		rating = repo.save(rating);
		Integer id = rating.getId();
		
		this.mvc.perform(get("/rating/delete/"+id))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/rating/list"));
		
		assertFalse(repo.findById(id).isPresent());
	}

}