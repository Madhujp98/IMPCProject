package com.impc.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.impc.project.beans.HumanGeneResource;
import com.impc.project.beans.MouseResourceResponse;
import com.impc.project.beans.ResponseResource;
import com.impc.project.service.GeneResourceService;
import com.impc.project.utils.Constants;
import com.impc.project.utils.Status;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

/**
 * 
 * @author Madhumiethaa
 *
 */
@RestController
@RequestMapping("api/impc/")
public class GeneResourceController {
	
	@Autowired 
	GeneResourceService geneResourceService;
	
	/**
	 * Api to Fetch Mouse Gene Symbols and Synonyms along with its associated Human Genes Support Count.
	 * @param key
	 * @return
	 */
	@ApiOperation(value = Constants.MOUSE_RESOURCE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = Constants.SUCCESS, response = MouseResourceResponse.class),
			@ApiResponse(code = 403, message = Constants.FORBIDDEN),
			@ApiResponse(code = 422, message = Constants.NOT_FOUND),
			@ApiResponse(code = 417, message = Constants.EXCEPTION_FAILED) })
	@GetMapping("/mouse-gene/search")
	 public ResponseResource<List<MouseResourceResponse>> getMouseResource(@RequestParam(required = false) String key ){

		List<MouseResourceResponse> mouseResourceResponses = geneResourceService.getMouseResource(key);

		if(mouseResourceResponses==null || mouseResourceResponses.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry, No outcomes for search key : "+key);
		}
		return new ResponseResource<>(ResponseResource.R_CODE_OK, ResponseResource.RES_SUCCESS, mouseResourceResponses,
				Status.SUCCESS);
    }
	
	/**
	 * Additional api to separately fetch Human Genes Support Count associated with a specified mouse gene searchable via symbol or mgi_gene_account_id
	 * @param key
	 * @return
	 * @throws ResourceNotFoundException 
	 */
	@ApiOperation(value = Constants.HUMAN_GENE_RESOURCE)
	@ApiResponses(value = { @ApiResponse(code = 200, message = Constants.SUCCESS, response = MouseResourceResponse.class),
			@ApiResponse(code = 403, message = Constants.FORBIDDEN),
			@ApiResponse(code = 422, message = Constants.NOT_FOUND),
			@ApiResponse(code = 417, message = Constants.EXCEPTION_FAILED) })
	@GetMapping("/human-gene/supportCount")
	 public ResponseResource<List<HumanGeneResource>> getHumanResource(@RequestParam(required = true) String key ){

		List<HumanGeneResource> humanGeneResources = geneResourceService.getHumanResource(key);
		if(humanGeneResources==null || humanGeneResources.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry, No outcomes for search key : "+key);
		}
		return new ResponseResource<>(ResponseResource.R_CODE_OK, ResponseResource.RES_SUCCESS, humanGeneResources,
				Status.SUCCESS);
    }
	
	

}
