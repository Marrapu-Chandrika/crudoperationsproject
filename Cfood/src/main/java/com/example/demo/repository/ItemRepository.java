package com.example.demo.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Itemdetails;

@Repository
public interface ItemRepository extends JpaRepository<Itemdetails, Integer>{

	@Query("SELECT idet from Rdetails rdet join rdet.itemdetails idet where rdet.rId = :rId")
	List<Itemdetails> findAllByResId(@Param("rId") int rId);
	
	


}
