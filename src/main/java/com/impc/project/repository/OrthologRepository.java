package com.impc.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.impc.project.entity.HumanGene;
import com.impc.project.entity.MouseGene;
import com.impc.project.entity.Ortholog;

/**
 * 
 * @author Madhumiethaa
 *
 */
@Repository
public interface OrthologRepository extends JpaRepository<Ortholog,Long>{

//	Ortholog findByHuman_Gene_IdAndMouse_Gene_Id(Long id, Long id2);
	Ortholog findByHumanGeneAndMouseGene(HumanGene humanGene, MouseGene mouseGene);



	
}
