package com.impc.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.impc.project.entity.MouseGeneSynonym;

/**
 * 
 * @author Madhumiethaa
 *
 */
@Repository
public interface MouseGeneSynonymRepository extends JpaRepository<MouseGeneSynonym, Long>{

	List<MouseGeneSynonym> findByMgiGeneAccId(String mgiGeneAccId);

	List<MouseGeneSynonym> findBySynonymContainingIgnoreCase(String symbol);

}
