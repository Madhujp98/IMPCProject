package com.impc.project.controller;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.impc.project.beans.HumanGeneResource;
import com.impc.project.beans.MouseResourceResponse;
import com.impc.project.beans.ResponseResource;
import com.impc.project.utils.Status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GeneResourceControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(GeneResourceControllerTest.class);
	
	@Autowired
	GeneResourceController geneResourceController;
	/**
	 * to test getMouseResource
	 * @throws TclCommonException
	 */
	@Test
	public void testgetMouseResource() throws Exception{	
		logger.info("testgetMouseResource() method invoked");
		ResponseResource<List<MouseResourceResponse>> response = geneResourceController.getMouseResource("scfd");
		assertTrue(response != null && response.getStatus() == Status.SUCCESS);
	}
	
	/**
	 * to test getMouseResource with NotFoundException
	 * @throws TclCommonException
	 */
	@Test(expected = Exception.class)
	public void testgetMouseResourceWithNotFoundException() throws Exception{	
		logger.info("testgetMouseResourceWithNotFoundException() method invoked");
		geneResourceController.getMouseResource("scfd9");	
	}
	
	/**
	 * to test getHumanGeneResource
	 * @throws TclCommonException
	 */
	@Test
	public void testgetHumanGeneResource() throws Exception{	
		logger.info("testgetHumanGeneResource() method invoked");
		ResponseResource<List<HumanGeneResource>> response = geneResourceController.getHumanResource("scfd");
		assertTrue(response != null && response.getStatus() == Status.SUCCESS);
	}
	
	/**
	 * to test getHumanGeneResource with NotFoundException
	 * @throws TclCommonException
	 */
	@Test(expected = Exception.class)
	public void testgetHumanGeneResourceWithNotFoundException() throws Exception{	
		logger.info("testgetHumanGeneResourceWithNotFoundException() method invoked");
		geneResourceController.getHumanResource("scfd9");	
	}
	
}
