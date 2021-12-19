package com.impc.project.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Getter;
import lombok.Setter;
/**
 * 
 * @author Madhumiethaa
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="mouse_gene")
@Getter
@Setter
public class MouseGene implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@Column(name="ensembl_chromosome")
	public String ensemblChromosome;

	@Column(name="ensembl_gene_acc_id")
	public String ensemblGeneAccId;
	
	 @Column(name = "ensembl_start")
	 public Long ensemblStart;
		
	 @Column(name = "ensembl_stop")
	 public Long ensemblStop;	
		
	 @Column(name="ensembl_strand")
	 public String ensemblStrand;
	 
	 @Column(name="entrez_gene_acc_id")
	 public Long entrezGeneAccId; 
	 
	 @Column(name="genome_build")
	 public String genomeBuild;
	 
	 @Column(name="mgi_gene_acc_id")
	 public String mgiGeneAccId;
	 
	 public String name;
	 
	 @Column(name="mgi_cm")
	 public String mgiCm;
	 
	 @Column(name="mgi_chromosome")
	 public String mgiChromosome;
	 
	 @Column(name="mgi_start")
	 public Long mgiStart;
	 
	 @Column(name="mgi_stop")
	 public Long mgiStop;
	 
	 @Column(name="mgi_strand")
     public String mgiStrand;
	 
	 @Column(name="ncbi_chromosome")
	 public String ncbiChromosome;
	 
	 @Column(name="ncbi_start")
	 public Long ncbiStart;
	 
	 @Column(name="ncbiStop")
	 public Long ncbi_stop;
	 
	 @Column(name="ncbi_strand")
	 public String ncbiStrand;
	 
	 public String symbol;
	 
	 public String type;

	 public String subtype;	
	 
	 //establish a relationship with MouseGeneSynonym without explicit constraint using common mgi_gene_acc_id column for ease of data access.
	 @OneToMany(fetch = FetchType.LAZY)
	 @NotFound(action = NotFoundAction.IGNORE)
	 @JoinColumn(name = "mgi_gene_acc_id", 
	       referencedColumnName = "mgi_gene_acc_id", 
	       insertable = false, 
	       updatable = false, 
	       foreignKey = @javax.persistence
	         .ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	public List<MouseGeneSynonym> mouseGeneSynonyms;	 
	 
	 @OneToMany(mappedBy="mouseGene",fetch = FetchType.LAZY)
	 public Set<Ortholog> orthologs;
	
	
}
