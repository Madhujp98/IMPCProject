package com.impc.project.objectcreator;

import java.util.ArrayList;
import java.util.List;

import com.impc.project.beans.HumanGeneResource;
import com.impc.project.beans.MouseResourceResponse;

public class GeneResourceObjectCreator {

	public List<MouseResourceResponse> getExpectedMouseResourceResponseWithWithPartialSearchKey() {
		List<MouseResourceResponse> mouseResourceResponses = new ArrayList<>();
		
		MouseResourceResponse mouseResourceResponse1 = new MouseResourceResponse();
		mouseResourceResponse1.setSymbol("Scfd1");
		mouseResourceResponse1.setName("Sec1 family domain containing 1");
		List<String> synonyms = new ArrayList<>();
		synonyms.add("3110021P21Rik");
		synonyms.add("STXBP1L2");
		synonyms.add("RA410");
		mouseResourceResponse1.setSynonyms(synonyms);
		List<HumanGeneResource> humanGeneResources = new ArrayList<>();
		HumanGeneResource humanGeneResource = new HumanGeneResource();
		humanGeneResource.setId((long) 33951);
		humanGeneResource.setSupportCount((long) 12);
		humanGeneResources.add(humanGeneResource);
		mouseResourceResponse1.setHumanGeneResources(humanGeneResources);
		mouseResourceResponses.add(mouseResourceResponse1);
		
		MouseResourceResponse mouseResourceResponse2 = new MouseResourceResponse();
		mouseResourceResponse1.setSymbol("Scfd2");
		mouseResourceResponse1.setName("Sec1 family domain containing 2");
		List<String> synonyms1 = new ArrayList<>();
		synonyms.add("STXBP1L1");
		synonyms.add("E430013M20Rik");
		mouseResourceResponse1.setSynonyms(synonyms1);
		List<HumanGeneResource> humanGeneResources1 = new ArrayList<>();
		HumanGeneResource humanGeneResource1 = new HumanGeneResource();
		humanGeneResource.setId((long) 33952);
		humanGeneResource.setSupportCount((long) 12);
		humanGeneResources.add(humanGeneResource1);
		mouseResourceResponse1.setHumanGeneResources(humanGeneResources1);
		mouseResourceResponses.add(mouseResourceResponse2);
		
		return mouseResourceResponses;
	}

}
