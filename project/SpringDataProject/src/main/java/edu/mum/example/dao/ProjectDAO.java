package edu.mum.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.example.domain.Project;
import edu.mum.example.domain.Student;

public interface ProjectDAO extends JpaRepository<Project, Long>{
	//List<Project> projects
	List<Project> findByDescription(String des);
}
