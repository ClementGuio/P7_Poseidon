package com.nnk.springboot.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurvePointServiceTest {

	@Autowired
	CurvePointService service;
	
	@Test
	public void bidListServiceTest() {
		CurvePoint curv1 = new CurvePoint(1, 10d, 30d);
		CurvePoint curv2 = new CurvePoint(2, 10d, 30d);

		// Save
		curv1 = service.saveCurve(curv1);
		curv2 = service.saveCurve(curv2);
		Assert.assertNotNull(curv1.getId());
		Assert.assertNotNull(curv2.getId());
		Assert.assertTrue(curv1.getCurveId() == 1);
		Assert.assertTrue(curv2.getCurveId() == 2);

		// Update
		curv1.setCurveId(11);
		curv1 = service.saveCurve(curv1);
		Assert.assertTrue(curv1.getCurveId() == 11);

		// Find all
		List<CurvePoint> listResult = service.getAllCurves();
		Assert.assertTrue(listResult.size() >= 2);
		
		// Find by id
		Integer id2 = curv2.getId();
		CurvePoint result = service.getCurveById(id2);
		Assert.assertEquals(curv2.getCurveId(),result.getCurveId());
		
		// Delete
		service.deleteCurve(curv2);
		result = service.getCurveById(id2);
		Assert.assertNull(result);
	}
	

}
