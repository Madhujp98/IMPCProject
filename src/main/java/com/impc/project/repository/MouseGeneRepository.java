package com.impc.project.repository;

import org.springframework.stereotype.Repository;
import com.impc.project.entity.MouseGene;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 
 * @author Madhumiethaa
 *
 */
@Repository
public interface MouseGeneRepository extends JpaRepository<MouseGene,Long>{
 
	//fetch result by ignoring case and pattern matching
	@Query(value = "select * from public.mouse_gene where (lower(symbol) like lower(concat('%', :key,'%'))) or (lower(mgi_gene_acc_id) like lower(concat('%', :key,'%')))", nativeQuery = true)
	List<MouseGene> findBySymbolOrMgiGeneAccIdIgnoreCase(String key);
	
}
