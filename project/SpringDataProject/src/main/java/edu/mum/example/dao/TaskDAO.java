package edu.mum.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.example.domain.Project;
import edu.mum.example.domain.Task;

public interface TaskDAO extends JpaRepository<Task, Long>{
	//find task based on Project ID
	List<Task> findBytaskID(Project p);
	
}
