package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Rdetails;


@Repository
public interface RdetailsRepository extends JpaRepository<Rdetails, Integer>{

	

}
