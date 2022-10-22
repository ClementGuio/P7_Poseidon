package com.nnk.springboot.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RuleNameServiceTest {

	@Autowired
	RuleNameService service;
	
	@Test
	public void ruleNameServiceTest() {
		RuleName rule1 = new RuleName("1st", "Description", "Json", "Template", "SQL", "SQL Part");
		RuleName rule2 = new RuleName("2nd", "Description", "Json", "Template", "SQL", "SQL Part");

		// Save
		rule1 = service.saveRule(rule1);
		rule2 = service.saveRule(rule2);
		Assert.assertNotNull(rule1.getId());
		Assert.assertNotNull(rule2.getId());
		Assert.assertEquals("1st",rule1.getName());
		Assert.assertEquals("2nd",rule2.getName());

		// Update
		rule1.setName("11th");
		rule1 = service.saveRule(rule1);
		Assert.assertEquals("11th",rule1.getName());

		// Find all
		List<RuleName> listResult = service.getAllRules();
		Assert.assertTrue(listResult.size() >= 2);
		
		// Find by id
		Integer id2 = rule2.getId();
		RuleName result = service.getRuleById(id2);
		Assert.assertEquals(rule2.getName(),result.getName());
		
		// Delete
		service.deleteRule(rule2);
		result = service.getRuleById(id2);
		Assert.assertNull(result);
	}
	
}
