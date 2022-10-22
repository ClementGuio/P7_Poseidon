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
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	MockMvc mvc;
	
	@Autowired
	UserRepository repo;
	
	User user;
	
	@Test
	@WithMockUser(username = "user")
	public void showListTest() throws Exception {
		this.mvc.perform(get("/user/list"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("users"));
	}
	
	@Test
	@WithMockUser(username = "user")
	public void showAddFormTest() throws Exception{
		this.mvc.perform(get("/user/add"))
				.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "user")
	public void showUpdateFormTest() throws Exception{
		user = new User("user","Password.8","fullname","TEST");
		user = repo.save(user);
		
		this.mvc.perform(get("/user/update/"+user.getId().toString()))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("user"));
		
		repo.delete(user);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void postUpdateFormTest() throws Exception{
		user = new User("user","Password.8","fullname","TEST");
		user = repo.save(user);
		
		this.mvc.perform(post("/user/update/"+user.getId().toString())
				.param("username", "user")
				.param("password", "Password.8")
				.param("fullname", "NEW")
				.param("role", "TEST")
				.sessionAttr("user", new User())
				.with(csrf()))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/user/list"));
		
		user = repo.findById(user.getId()).get();
		assertEquals("NEW",user.getFullname());
		repo.delete(user);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void postUpdateFormWithTooShortPasswordTest() throws Exception{
		user = new User("user","Password.8","fullname","TEST");
		user = repo.save(user);
		
		this.mvc.perform(post("/user/update/"+user.getId().toString())
				.param("username", "user")
				.param("password", "Short.1")
				.param("fullname", "NEW")
				.param("role", "TEST")
				.sessionAttr("user", new User())
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(model().hasErrors());
		
		repo.delete(user);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void postUpdateFormWithPasswordNoUppercaseTest() throws Exception{
		user = new User("user","Password.8","fullname","TEST");
		user = repo.save(user);
		
		this.mvc.perform(post("/user/update/"+user.getId().toString())
				.param("username", "user")
				.param("password", "password.8")
				.param("fullname", "NEW")
				.param("role", "TEST")
				.sessionAttr("user", new User())
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(model().hasErrors());
		
		repo.delete(user);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void postUpdateFormWithPasswordNoSpecialTest() throws Exception{
		user = new User("user","Password.8","fullname","TEST");
		user = repo.save(user);
		
		this.mvc.perform(post("/user/update/"+user.getId().toString())
				.param("username", "user")
				.param("password", "Passwordd8")
				.param("fullname", "NEW")
				.param("role", "TEST")
				.sessionAttr("user", new User())
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(model().hasErrors());
		
		repo.delete(user);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void postUpdateFormWithPasswordNoNumberTest() throws Exception{
		user = new User("user","Password.8","fullname","TEST");
		user = repo.save(user);
		
		this.mvc.perform(post("/user/update/"+user.getId().toString())
				.param("username", "user")
				.param("password", "Password..")
				.param("fullname", "NEW")
				.param("role", "TEST")
				.sessionAttr("user", new User())
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(model().hasErrors());
		
		repo.delete(user);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void postUpdateFormWithPasswordWhitespaceTest() throws Exception{
		user = new User("user","Password.8","fullname","TEST");
		user = repo.save(user);
		
		this.mvc.perform(post("/user/update/"+user.getId().toString())
				.param("username", "user")
				.param("password", "P ssword.8")
				.param("fullname", "NEW")
				.param("role", "TEST")
				.sessionAttr("user", new User())
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(model().hasErrors());
		
		repo.delete(user);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void postUpdateFormWithPasswordLowercaseTest() throws Exception{
		user = new User("user","Password.8","fullname","TEST");
		user = repo.save(user);
		
		this.mvc.perform(post("/user/update/"+user.getId().toString())
				.param("username", "user")
				.param("password", "PASSWORD.8")
				.param("fullname", "NEW")
				.param("role", "TEST")
				.sessionAttr("user", new User())
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(model().hasErrors());
		
		repo.delete(user);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void postUpdateFormWithBlankUsernameTest() throws Exception{
		user = new User("user","Password.8","fullname","TEST");
		user = repo.save(user);
		
		this.mvc.perform(post("/user/update/"+user.getId().toString())
				.param("username", "")
				.param("password", "Password.8")
				.param("fullname", "fullname")
				.param("role", "TEST")
				.sessionAttr("user", new User())
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(model().hasErrors());
		
		repo.delete(user);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void postUpdateFormWithBlankPasswordTest() throws Exception{
		user = new User("user","Password.8","fullname","TEST");
		user = repo.save(user);
		
		this.mvc.perform(post("/user/update/"+user.getId().toString())
				.param("username", "user")
				.param("password", "")
				.param("fullname", "fullname")
				.param("role", "TEST")
				.sessionAttr("user", new User())
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(model().hasErrors());
		
		repo.delete(user);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void postUpdateFormWithBlankFullnameTest() throws Exception{
		user = new User("user","Password.8","fullname","TEST");
		user = repo.save(user);
		
		this.mvc.perform(post("/user/update/"+user.getId().toString())
				.param("username", "user")
				.param("password", "Password.8")
				.param("fullname", "")
				.param("role", "TEST")
				.sessionAttr("user", new User())
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(model().hasErrors());
		
		repo.delete(user);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void postUpdateFormWithBlankRoleTest() throws Exception{
		user = new User("user","Password.8","fullname","TEST");
		user = repo.save(user);
		
		this.mvc.perform(post("/user/update/"+user.getId().toString())
				.param("username", "user")
				.param("password", "Password.8")
				.param("fullname", "fullname")
				.param("role", "")
				.sessionAttr("user", new User())
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(model().hasErrors());
		
		repo.delete(user);
	}
	
	@Test
	@WithMockUser(username = "user")
	public void validateAddFormTest() throws Exception{
		this.mvc.perform(post("/user/validate")
				.param("username", "user")
				.param("password", "Password.8")
				.param("fullname", "fullname")
				.param("role", "TEST")
				.sessionAttr("user", new User())
				.with(csrf()))
			.andExpect(status().isFound())
			.andExpect(model().hasNoErrors());
		
		List<User> res = repo.findByRole("TEST");
		for (User user : res) {
			repo.delete(user);
		}
	}
	
	@Test
	@WithMockUser(username = "user")
	public void validateAddFormWithBlankUsernameTest() throws Exception{
		this.mvc.perform(post("/user/validate")
				.param("username", "")
				.param("password", "Password.8")
				.param("fullname", "fullname")
				.param("role", "TEST")
				.sessionAttr("user", new User())
				.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(model().hasErrors());
		
	}
	
	@Test
	@WithMockUser(username = "user")
	public void validateAddFormWithBlankPasswordTest() throws Exception{
		this.mvc.perform(post("/user/validate")
				.param("username", "user")
				.param("password", "")
				.param("fullname", "fullname")
				.param("role", "TEST")
				.sessionAttr("user", new User())
				.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(model().hasErrors());
		
	}
	
	@Test
	@WithMockUser(username = "user")
	public void validateAddFormWithBlankFullnameTest() throws Exception{
		this.mvc.perform(post("/user/validate")
				.param("username", "user")
				.param("password", "Password.8")
				.param("fullname", "")
				.param("role", "TEST")
				.sessionAttr("user", new User())
				.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(model().hasErrors());
	}
	
	@Test
	@WithMockUser(username = "user")
	public void validateAddFormWithBlankRoleTest() throws Exception{
		this.mvc.perform(post("/user/validate")
				.param("username", "user")
				.param("password", "Password.8")
				.param("fullname", "fullname")
				.param("role", "")
				.sessionAttr("user", new User())
				.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(model().hasErrors());	
	}
	
	@Test
	@WithMockUser(username = "user")
	public void deleteTest() throws Exception{
		user = new User("user","Password.8","fullname","TEST");
		user = repo.save(user);
		Integer id = user.getId();
		
		this.mvc.perform(get("/user/delete/"+id))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/user/list"));
		
		assertFalse(repo.findById(id).isPresent());
	}

}
