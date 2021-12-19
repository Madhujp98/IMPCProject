package com.impc.project.beans;

/**
 * Response Bean to return Human Gene id with their supportCounts
 * @author Madhumiethaa
 *
 */
public class HumanGeneResource {
	
	private Long id;
	private Long supportCount;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSupportCount() {
		return supportCount;
	}
	public void setSupportCount(Long supportCount) {
		this.supportCount = supportCount;
	}
}
