package edu.mum;

import java.util.List;

import edu.mum.example.domain.Project;

public class ProjectServiceImp {
	public Project searchProject(String pname, List<Project> projectList) {
		for (Project p : projectList) {
			if (p.getDescription().equals(pname))
				return p;
		}
		return null;

	}

	public Project updateProject(String pname, List<Project> projectList) {
		
		return null;
	}

}
