package com.impc.project.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name="human_gene")
@Getter
@Setter
public class HumanGene implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@Column(name="hgnc_acc_id")
	public String hgncAccId;
	
	public String name;
	
	public String symbol;
	
	@Column(name="ensembl_gene_acc_id")
	public String ensemblGeneAccId;
	
	@Column(name="entrez_gene_acc_id")
	public Long entrezGeneAccId;
	 
	@OneToMany(mappedBy="humanGene")
	public Set<Ortholog> orthologs;
	
}
