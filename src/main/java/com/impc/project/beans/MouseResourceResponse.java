package com.impc.project.beans;

import java.util.List;

/**
 * Response Bean to return Mouse Resource containing its symbol,name,synonyms and corresponding Human gene List for each Mouse Gene containing id and supportCounts
 * @author Madhumiethaa
 *
 */
public class MouseResourceResponse {

	private String symbol; 
	
	private String name;
	
	private List<String> synonyms;

	private List<HumanGeneResource> humanGeneResources;
	
	public List<HumanGeneResource> getHumanGeneResources() {
		return humanGeneResources;
	}

	public void setHumanGeneResources(List<HumanGeneResource> humanGeneResources) {
		this.humanGeneResources = humanGeneResources;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(List<String> synonyms) {
		this.synonyms = synonyms;
	}
	
}
