package edu.mum.example.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.example.domain.Project;

public interface ProjectDAO extends JpaRepository<Project, Long>{
	//List of objects based on description
	List<Project> findByDescription(String des);
	// find all project by Project ID
	Project findByProjectID(int id);
	// List all the available project
	
	Page<Project> findAll(Pageable pageable);
	
}
