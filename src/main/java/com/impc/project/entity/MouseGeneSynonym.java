package com.impc.project.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="mouse_gene_synonym")
@Getter
@Setter
public class MouseGeneSynonym implements Serializable{


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@Column(name="mgi_gene_acc_id")
	public String mgiGeneAccId;
	
	public String synonym;
	
	//establish a relationship with MouseGene without explicit constraint using common mgi_gene_acc_id column for ease of data access.
	 @ManyToOne(fetch = FetchType.LAZY)
	 @NotFound(action = NotFoundAction.IGNORE)
	 @JoinColumn(name = "mgi_gene_acc_id", 
	       referencedColumnName = "mgi_gene_acc_id", 
	       insertable = false, 
	       updatable = false, 
	       foreignKey = @javax.persistence
	         .ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	public MouseGene mouseGene;

}
