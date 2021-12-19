package com.impc.project.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impc.project.beans.HumanGeneResource;
import com.impc.project.beans.MouseResourceResponse;
import com.impc.project.entity.HumanGene;
import com.impc.project.entity.MouseGene;
import com.impc.project.entity.MouseGeneSynonym;
import com.impc.project.entity.Ortholog;
import com.impc.project.repository.HumanGeneRepository;
import com.impc.project.repository.MouseGeneRepository;
import com.impc.project.repository.MouseGeneSynonymRepository;
import com.impc.project.repository.OrthologRepository;

/**
 * GeneResourceService is used to obtain MouseGeneResources and HumanGeneResources.
 * @author Madhumiethaa
 *
 */
@Service
public class GeneResourceService {

	@Autowired
	MouseGeneRepository mouseGeneRepository;

	@Autowired
	MouseGeneSynonymRepository mouseGeneSynonymRepository;

	@Autowired
	OrthologRepository orthologRepository;

	@Autowired
	HumanGeneRepository humanGeneRepository;

	/**
	 * Method to fetch Mouse Resource (symbols and its synonyms and its associated human genes supportCount) with/ without
	 * search key/ with partial search keys
	 * 
	 * @param key
	 * @return
	 */
	public List<MouseResourceResponse> getMouseResource(String key) {

		List<MouseResourceResponse> mouseResourceResponses = new ArrayList<>();

		// Scenario 1: If there is no search key, return all mouseGene symbols with
		// their synonyms and associated human genes support count
		
		if (key == null || key.isEmpty()) {
			List<MouseGene> mouseGenes = mouseGeneRepository.findAll();
			setAllMouseResourses(mouseResourceResponses, mouseGenes);
		}

		else {
			// Note :Querying by list as the key may be partial and can contain many results for a single key
			// fetch mouse gene list either by considering key as symbol or MgiGeneAccId
			List<MouseGene> mouseGenes = mouseGeneRepository.findBySymbolOrMgiGeneAccIdIgnoreCase(key);

			// if the search key is a symbol or mgi_gene_acc_id, fetch MouseGenes and then its Associated synonyms 
			if (mouseGenes != null && !mouseGenes.isEmpty()) {
				for (MouseGene mGene : mouseGenes) {
					MouseResourceResponse mouseResourceResponse = new MouseResourceResponse();
					mouseResourceResponse.setSymbol(mGene.symbol);
					mouseResourceResponse.setName(mGene.name);
					List<MouseGeneSynonym> geneSynonyms = mGene.mouseGeneSynonyms;
					setSynonyms(mouseResourceResponse, geneSynonyms);
					setSupportCount(mouseResourceResponse, mGene);
					mouseResourceResponses.add(mouseResourceResponse);
				}
			} else {
				// if the search key is a synonym
				List<MouseGeneSynonym> mouseGeneSynonyms = mouseGeneSynonymRepository.findBySynonymContainingIgnoreCase(key);
				if (mouseGeneSynonyms != null && !mouseGeneSynonyms.isEmpty()) {
					for (MouseGeneSynonym mGeneSynonym : mouseGeneSynonyms) {
						MouseResourceResponse mouseResourceResponse = new MouseResourceResponse();
						MouseGene mGene = mGeneSynonym.mouseGene;
						mouseResourceResponse.setSymbol(mGene.symbol);
						mouseResourceResponse.setName(mGene.name);
						List<MouseGeneSynonym> geneSynonyms = mouseGeneSynonymRepository
								.findByMgiGeneAccId(mGeneSynonym.mgiGeneAccId);
						setSynonyms(mouseResourceResponse, geneSynonyms);
						setSupportCount(mouseResourceResponse, mGene);
						mouseResourceResponses.add(mouseResourceResponse);
					}
				}
			}
		}
		return mouseResourceResponses;
	}

	/**
	 * Method to set supportCounts to MouseResourceResponse for each mouse gene
	 * @param mouseResourceResponse
	 * @param mGene
	 */
	private void setSupportCount(MouseResourceResponse mouseResourceResponse, MouseGene mGene) {
		//Get human genes by its symbol from mouseGene to set its id in list of HumanGeneResource response and to fetch support count from ortholog
		List<HumanGene> humanGenes = humanGeneRepository.findBySymbolIgnoreCase(mGene.symbol);
		if (humanGenes != null && !humanGenes.isEmpty()) {
			List<HumanGeneResource> geneResources = new ArrayList<>();
			for (HumanGene humanGene : humanGenes) {
				System.out.println("human gene id:" + humanGene);
				HumanGeneResource humanGeneResource = new HumanGeneResource();
				humanGeneResource.setId(humanGene.id);
				//fetch ortholog by the foreign key constraint with MouseGene and Humangene to set SupportCount
				Ortholog ortholog = orthologRepository.findByHumanGeneAndMouseGene(humanGene, mGene);
				if (ortholog != null) {
					humanGeneResource.setSupportCount(ortholog.supportCount);
				}
				geneResources.add(humanGeneResource);
			}
			mouseResourceResponse.setHumanGeneResources(geneResources);
		}
	}

	/**
	 * method to set All MouseResourses with its symbols,synonyms and supportcounts if there is no search key
	 * 
	 * @param mouseResourceResponses
	 * @param mouseGenes
	 */
	private void setAllMouseResourses(List<MouseResourceResponse> mouseResourceResponses, List<MouseGene> mouseGenes) {

		for (MouseGene mouseGene : mouseGenes) {
			MouseResourceResponse mouseResourceResponse = new MouseResourceResponse();
			mouseResourceResponse.setName(mouseGene.name);
			mouseResourceResponse.setSymbol(mouseGene.symbol);
			List<MouseGeneSynonym> geneSynonyms = mouseGene.mouseGeneSynonyms;
			setSynonyms(mouseResourceResponse, geneSynonyms);
			setSupportCount(mouseResourceResponse, mouseGene);
			mouseResourceResponses.add(mouseResourceResponse);
		}

	}

	/**
	 * Method to set Synonyms for each Mouse Gene
	 * 
	 * @param mouseResourceResponse
	 * @param synonyms
	 * @param geneSynonyms
	 */
	private void setSynonyms(MouseResourceResponse mouseResourceResponse, List<MouseGeneSynonym> geneSynonyms) {
		if (geneSynonyms != null && !geneSynonyms.isEmpty()) {
			List<String> synonyms = new ArrayList<>();
			for (MouseGeneSynonym geneSynonym : geneSynonyms) {
				synonyms.add(geneSynonym.synonym);
				System.out.println(geneSynonym.mouseGene.symbol);
			}
			mouseResourceResponse.setSynonyms(synonyms);
		}
	}

	public List<HumanGeneResource> getHumanResource(String key) {

		List<HumanGeneResource> humanGeneResources = new ArrayList<>();

		// Querying by list as the key may be partial and can contain many results for a
		// single key
		List<MouseGene> mouseGenes = mouseGeneRepository.findBySymbolOrMgiGeneAccIdIgnoreCase(key);
		// if the search key is a symbol or mgi_gene_acc_id
		if (mouseGenes != null && !mouseGenes.isEmpty()) {
			for (MouseGene mGene : mouseGenes) {
				setSupportCount(humanGeneResources, mGene);
			}
		}
		return humanGeneResources;

	}

	/**
	 * Method to set supportCounts to HumanGeneResource for each mouse gene 
	 * @param humGeneResources
	 * @param mGene
	 */
	private void setSupportCount(List<HumanGeneResource> humGeneResources, MouseGene mGene) {
		List<HumanGene> humanGenes = humanGeneRepository.findBySymbolIgnoreCase(mGene.symbol);
		if (humanGenes != null && !humanGenes.isEmpty()) {
			for (HumanGene humanGene : humanGenes) {
				System.out.println("human gene id:" + humanGene);
				HumanGeneResource humanGeneResource = new HumanGeneResource();
				humanGeneResource.setId(humanGene.id);
				Ortholog ortholog = orthologRepository.findByHumanGeneAndMouseGene(humanGene, mGene);
				if (ortholog != null) {
					humanGeneResource.setSupportCount(ortholog.supportCount);
				}
				humGeneResources.add(humanGeneResource);
			}
		}
	}
}
