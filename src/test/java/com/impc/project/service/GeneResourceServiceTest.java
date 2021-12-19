package com.impc.project.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.impc.project.beans.MouseResourceResponse;
import com.impc.project.objectcreator.GeneResourceObjectCreator;



import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GeneResourceServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(GeneResourceServiceTest.class);
	
	@Autowired
	GeneResourceService geneResourceService;
	
	@Autowired
	GeneResourceObjectCreator geneResourceObjectCreator;
	
	/**
	 * to test getMouseResource with search key : null
	 * @throws TclCommonException
	 */
	@Test
	public void testGetMouseResourceWithNullSearchKey() throws Exception{	
		logger.info("testGetMouseResourceWithNullSearchKey() method invoked");
		List<MouseResourceResponse> response = geneResourceService.getMouseResource(null);
		assertTrue(response != null && !response.isEmpty() && response.get(0).getSynonyms()!=null && response.get(0).getHumanGeneResources()!=null);
	}
	
	/**
	 * to test getMouseResource with search key : empty
	 * @throws TclCommonException
	 */
	@Test
	public void testGetMouseResourceWithEmptySearchKey() throws Exception{	
		logger.info("testGetMouseResourceWithEmptySearchKey() method invoked");
		List<MouseResourceResponse> response = geneResourceService.getMouseResource("");
		assertTrue(response != null && !response.isEmpty() && response.get(0).getSynonyms()!=null && response.get(0).getHumanGeneResources()!=null);
	}
	
	/**
	 * to test getMouseResource with partial Search key like 'Scfd' instead of Scfd1
	 * @throws TclCommonException
	 */
	@Test
	public void testGetMouseResourceWithPartialSearchKey() throws Exception{	
		logger.info("testGetMouseResourceWithPartialSearchKey() method invoked");
		List<MouseResourceResponse> response = geneResourceService.getMouseResource("Scfd");
		List<MouseResourceResponse> expectedResponse = geneResourceObjectCreator.getExpectedMouseResourceResponseWithWithPartialSearchKey();
	    assertEquals(expectedResponse, response);
	}
	
	/**
	 * to test getMouseResource with partial Search key like 'Sc' instead of Scfd1 and case insensitive check
	 * @throws TclCommonException
	 */
	@Test
	public void testGetMouseResourceWithPartialSearchKeyAndCaseInsensitiveCheck() throws Exception{	
		logger.info("testGetMouseResourceWithPartialSearchKeyAndCaseInsensitiveCheck() method invoked");
		List<MouseResourceResponse> response = geneResourceService.getMouseResource("sc");
		assertTrue(response != null && !response.isEmpty() && response.get(0).getSymbol().contains("Sc") && response.get(0).getSynonyms()!=null && response.get(0).getHumanGeneResources()!=null);
	}
	
	/**
	 * to test getMouseResource with synonym as search key (ex: STXBP1L1)
	 * @throws TclCommonException
	 */
	@Test
	public void testGetMouseResourceWithSynonymAsSearchKey() throws Exception{	
		logger.info("testGetMouseResourceWithSynonymAsSearchKey() method invoked");
		List<MouseResourceResponse> response = geneResourceService.getMouseResource("STXBP1L1");
		assertTrue(response != null && !response.isEmpty() && response.get(0).getSynonyms()!=null && response.get(0).getHumanGeneResources()!=null);
	}
	
	/**
	 * to test getMouseResource with synonym as search key and case insensitive check (ex: Stxb1L1)
	 * @throws TclCommonException
	 */
	@Test
	public void testGetMouseResourceWithSynonymAsSearchKeyAndCaseInsensitiveCheck() throws Exception{	
		logger.info("testGetMouseResourceWithSynonymAsSearchKeyAndCaseInsensitiveCheck() method invoked");
		List<MouseResourceResponse> response = geneResourceService.getMouseResource("Stxb1L1");
		assertTrue(response != null && !response.isEmpty() && response.get(0).getSynonyms()!=null && response.get(0).getHumanGeneResources()!=null);
	}
	
	/**
	 * to test getMouseResource with partial synonym as search key  (ex: Stx)
	 * @throws TclCommonException
	 */
	@Test
	public void testGetMouseResourceWithPartialSynonymAsSearchKey() throws Exception{	
		logger.info("testGetMouseResourceWithPartialSynonymAsSearchKey() method invoked");
		List<MouseResourceResponse> response = geneResourceService.getMouseResource("Stx");
		assertTrue(response != null && !response.isEmpty() && response.get(0).getSynonyms()!=null && response.get(0).getHumanGeneResources()!=null);
	}
	
	/**
	 * to test getMouseResource with mgi_gene_acc_id as search key (ex: MGI:2443446)
	 * @throws TclCommonException
	 */
	@Test
	public void testGetMouseResourceWithMgiGeneAccIdAsSearchKey() throws Exception{	
		logger.info("testGetMouseResourceWithMgiGeneAccIdAsSearchKey() method invoked");
		List<MouseResourceResponse> response = geneResourceService.getMouseResource("MGI:2443446");
		assertTrue(response != null && !response.isEmpty() && response.get(0).getSynonyms()!=null && response.get(0).getHumanGeneResources()!=null);
	}
	
	/**
	 * to test getMouseResource with mgi_gene_acc_id as search key and case insensitive check(ex: Mgi:2443446)
	 * @throws TclCommonException
	 */
	@Test
	public void testGetMouseResourceWithMgiGeneAccIdAsSearchKeyAndCaseInSensitiveCheck() throws Exception{	
		logger.info("testGetMouseResourceWithMgiGeneAccIdAsSearchKeyAndCaseInSensitiveCheck() method invoked");
		List<MouseResourceResponse> response = geneResourceService.getMouseResource("Mgi:2443446");
		assertTrue(response != null && !response.isEmpty() && response.get(0).getSynonyms()!=null && response.get(0).getHumanGeneResources()!=null);
	}
	
	/**
	 * to test getMouseResource with partial mgi_gene_acc_id as search key (ex: Mgi:244)
	 * @throws TclCommonException
	 */
	@Test
	public void testGetMouseResourceWithPartialMgiGeneAccIdAsSearchKey() throws Exception{	
		logger.info("testGetMouseResourceWithPartialMgiGeneAccIdAsSearchKey() method invoked");
		List<MouseResourceResponse> response = geneResourceService.getMouseResource("Mgi:244");
		assertTrue(response != null && !response.isEmpty() && response.get(0).getSynonyms()!=null && response.get(0).getHumanGeneResources()!=null);
	}
}
