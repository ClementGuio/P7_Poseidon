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
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.repositories.RuleNameRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class RulenameControllerTest {

	@Autowired
	MockMvc mvc;
	
	@Autowired
	RuleNameRepository repo;
	
	RuleName rule;
	
	@Test
	@WithMockUser(username = "user")
	public void showListTest() throws Exception {
		this.mvc.perform(get("/ruleName/list"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("allrulenames"));
	}
	
	@Test
	@WithMockUser(username = "user")
	public void showAddFormTest() throws Exception{
		this.mvc.perform(get("/ruleName/add"))
				.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "user")
	public void showUpdateFormTest() throws Exception{
		rule = new RuleName("RULE TEST", "Description", "Json", "Template", "SQL", "SQL Part");
		rule = repo.save(rule);
		
		this.mvc.perform(get("/ruleName/update/"+rule.getId().toString()))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("ruleName"));
		
		repo.delete(rule);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void postUpdateFormTest() throws Exception{
		rule = new RuleName("RULE TEST", "Description", "Json", "Template", "SQL", "SQL Part");
		rule = repo.save(rule);
		
		this.mvc.perform(post("/ruleName/update/"+rule.getId().toString())
				.param("name", "RULE TEST")
				.param("description", "NEW")
				.sessionAttr("ruleName", new RuleName())
				.with(csrf()))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/ruleName/list"));
		
		rule = repo.findById(rule.getId()).get();
		assertEquals("NEW", rule.getDescription());
		repo.delete(rule);
	}

	@Test
	@WithMockUser(username = "user")
	public void postUpdateFormWithBlankNameTest() throws Exception{
		rule = new RuleName("RULE TEST", "Description", "Json", "Template", "SQL", "SQL Part");
		rule = repo.save(rule);
		
		this.mvc.perform(post("/ruleName/update/"+rule.getId().toString())
				.param("name", "")
				.param("description", "Description")
				.sessionAttr("ruleName", new RuleName())
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(model().hasErrors());
		
		repo.delete(rule);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void postUpdateFormWithBlankDescriptionTest() throws Exception{
		rule = new RuleName("RULE TEST", "Description", "Json", "Template", "SQL", "SQL Part");
		rule = repo.save(rule);
		
		this.mvc.perform(post("/ruleName/update/"+rule.getId().toString())
				.param("name", "RULE TEST")
				.param("description", "")
				.sessionAttr("ruleName", new RuleName())
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(model().hasErrors());
		
		repo.delete(rule);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void validateAddFormTest() throws Exception{
		this.mvc.perform(post("/ruleName/validate")
				.param("name", "RULE TEST")
				.param("description", "Description")
				.sessionAttr("ruleName", new RuleName())
				.with(csrf()))
			.andExpect(status().isFound())
			.andExpect(model().hasNoErrors());
		
		List<RuleName> res = repo.findByName("RULE TEST");
		for (RuleName rule : res) {
			repo.delete(rule);
		}
	}
	
	@Test
	@WithMockUser(username = "user")
	public void validateAddFormWithBlankNameTest() throws Exception{
		this.mvc.perform(post("/ruleName/validate")
				.param("name", "")
				.param("description", "Description")
				.sessionAttr("ruleName", new RuleName())
				.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(model().hasErrors());
		
	}
	
	@Test
	@WithMockUser(username = "user")
	public void validateAddFormWithBlankDescriptionTest() throws Exception{
		this.mvc.perform(post("/ruleName/validate")
				.param("name", "RULE TEST")
				.param("description", "")
				.sessionAttr("ruleName", new RuleName())
				.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(model().hasErrors());
		
	}
	
	@Test
	@WithMockUser(username = "user")
	public void deleteTest() throws Exception{
		rule = new RuleName("RULE TEST", "Description", "Json", "Template", "SQL", "SQL Part");
		rule = repo.save(rule);
		Integer id = rule.getId();
		
		this.mvc.perform(get("/ruleName/delete/"+id))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/ruleName/list"));
		
		assertFalse(repo.findById(id).isPresent());
	}

}
