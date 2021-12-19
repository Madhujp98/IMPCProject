package com.impc.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Madhumiethaa
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="ortholog")
@Getter
@Setter
public class Ortholog implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	public String support;
	
	@Column(name="support_raw")
	public String supportRaw;
	
	@Column(name="support_count")
	public Long supportCount;
	
	public String category;
	
	@ManyToOne
	@JoinColumn(name="human_gene_id")
	public HumanGene humanGene;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="mouse_gene_id")
	public MouseGene mouseGene;
	
	@Column(name="is_max_human_to_mouse")
	public String isMaxHumanToMouse;
	
	@Column(name="is_max_mouse_to_human")
	public String isMaxMouseToHuman;

}
