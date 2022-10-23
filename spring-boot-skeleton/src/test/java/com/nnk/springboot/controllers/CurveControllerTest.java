package com.nnk.springboot.controllers;

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
import com.nnk.springboot.repositories.CurvePointRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class CurveControllerTest {

	@Autowired
	MockMvc mvc;
	
	@Autowired
	CurvePointRepository repo;
	
	CurvePoint curve;
	
	@Test
	@WithMockUser(username = "user")
	public void showListTest() throws Exception {
		this.mvc.perform(get("/curvePoint/list"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("allcurves"));
	}
	
	@Test
	@WithMockUser(username = "user")
	public void showAddFormTest() throws Exception{
		this.mvc.perform(get("/curvePoint/add"))
				.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "user")
	public void showUpdateFormTest() throws Exception{
		curve = new CurvePoint(-1,2d,2d);
		curve = repo.save(curve);
		
		this.mvc.perform(get("/curvePoint/update/"+curve.getId().toString()))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("curvePoint"));
		
		repo.delete(curve);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void postUpdateFormTest() throws Exception{
		curve = new CurvePoint(-1,2d,2d);
		curve = repo.save(curve);
		
		this.mvc.perform(post("/curvePoint/update/"+curve.getId().toString())
				.param("curveId", "-1")
				.param("term", "22.0")
				.param("value", "2.0")
				.sessionAttr("curve", new CurvePoint())
				.with(csrf()))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/curvePoint/list"));
		
		curve = repo.findById(curve.getId()).get();
		assertTrue(curve.getTerm() == 22.0);
		repo.delete(curve);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void postUpdateFormWithBlankCurveIdTest() throws Exception{
		curve = new CurvePoint(-1,2.0,2.0);
		curve = repo.save(curve);
		
		this.mvc.perform(post("/curvePoint/update/"+curve.getId().toString())
				.param("curveId", "")
				.param("term", "22.0")
				.param("value", "2.0")
				.sessionAttr("curve", new CurvePoint())
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(model().hasErrors());
		
		repo.delete(curve);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void postUpdateFormWithBlankTermTest() throws Exception{
		curve = new CurvePoint(-1,2.0,2.0);
		curve = repo.save(curve);
		
		this.mvc.perform(post("/curvePoint/update/"+curve.getId().toString())
				.param("curveId", "-1")
				.param("term", "")
				.param("value", "2.0")
				.sessionAttr("curve", new CurvePoint())
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(model().hasErrors());
		
		repo.delete(curve);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void postUpdateFormWithBlankValueTest() throws Exception{
		curve = new CurvePoint(777,2.0,2.0);
		curve = repo.save(curve);
		
		this.mvc.perform(post("/curvePoint/update/"+curve.getId().toString())
				.param("curveId", "-1")
				.param("term", "22.0")
				.param("value", "")
				.sessionAttr("curve", new CurvePoint())
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(model().hasErrors());
		
		repo.delete(curve);
	}
	
	
	@Test
	@WithMockUser(username = "user")
	public void validateAddFormTest() throws Exception{
		this.mvc.perform(post("/curvePoint/validate")
				.param("curveId", "-1")
				.param("term", "22.0")
				.param("value", "2.0")
				.sessionAttr("curve", new CurvePoint())
				.with(csrf()))
			.andExpect(status().isFound())
			.andExpect(model().hasNoErrors());
		
		List<CurvePoint> res = repo.findByCurveId(777);
		for (CurvePoint curve : res) {
			repo.delete(curve);
		}
	}
	
	@Test
	@WithMockUser(username = "user")
	public void validateAddFormWithBlankCurveIdTest() throws Exception{
		this.mvc.perform(post("/curvePoint/validate")
				.param("curveId", "")
				.param("term", "22.0")
				.param("value", "2.0")
				.sessionAttr("curve", new CurvePoint())
				.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(model().hasErrors());
		
	}
	
	@Test
	@WithMockUser(username = "user")
	public void validateAddFormWithBlankTermTest() throws Exception{
		this.mvc.perform(post("/curvePoint/validate")
				.param("curveId", "-1")
				.param("term", "")
				.param("value", "2.0")
				.sessionAttr("curve", new CurvePoint())
				.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(model().hasErrors());
		
	}
	
	@Test
	@WithMockUser(username = "user")
	public void validateAddFormWithBlankValueTest() throws Exception{
		this.mvc.perform(post("/curvePoint/validate")
				.param("curveId", "-1")
				.param("term", "22.0")
				.param("value", "")
				.sessionAttr("curve", new CurvePoint())
				.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(model().hasErrors());
	}
	
	@Test
	@WithMockUser(username = "user")
	public void validateAddFormWithBadFieldsTest() throws Exception{
		this.mvc.perform(post("/curvePoint/validate")
				.param("curveId", "BAD")
				.param("term", "2.0")
				.param("value", "2.0")
				.sessionAttr("curve", new CurvePoint())
				.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(model().hasErrors());	
	}
	
	@Test
	@WithMockUser(username = "user")
	public void deleteTest() throws Exception{
		curve = new CurvePoint(-1,2.0,2.0);
		curve = repo.save(curve);
		Integer id = curve.getId();
		
		this.mvc.perform(get("/curvePoint/delete/"+id))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/curvePoint/list"));
		
		assertFalse(repo.findById(id).isPresent());
	}

}