package edu.mum.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.example.domain.Project;

public interface ProjectDAO extends JpaRepository<Project, Long>{

}
