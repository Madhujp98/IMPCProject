package com.impc.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.impc.project.entity.HumanGene;

/**
 * 
 * @author Madhumiethaa
 *
 */
@Repository
public interface HumanGeneRepository extends JpaRepository<HumanGene,Long>{

	
	List<HumanGene> findBySymbolIgnoreCase(String symbol);
}
