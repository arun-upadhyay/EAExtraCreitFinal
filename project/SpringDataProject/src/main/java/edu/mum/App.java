package edu.mum;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.mum.example.dao.ProjectDAO;
import edu.mum.example.domain.Project;
import edu.mum.example.domain.Project.StatusProject;
import edu.mum.example.domain.Task;
import edu.mum.example.domain.Task.StatusTask;

public class App {
	@Autowired
	ProjectDAO projectDao;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		App a = context.getBean("mainApp", App.class);
		try {
			a.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
		context.close();
	}

	public void setRepository(ProjectDAO projectDao) {
		this.projectDao = projectDao;
	}

	public void run() throws Exception {

		SimpleDateFormat formatterDate = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");
		File file = new File("F:\test.jpg");
		byte[] imageData = new byte[(int) file.length()];

		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(imageData);
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// List of benefits
		List<String> benefits = new ArrayList();
		benefits.add("Incentives");
		benefits.add("Overtime");
		benefits.add("Bonus");
		List<String> resources = new ArrayList<>();
		resources.add("Java Developers");
		resources.add("15 Computers");
		// Task1
		Task task = new Task("Requirement Gathering", formatterTime.parse("08:56"), resources, StatusTask.INACTIVE,
				imageData);
		// Task
		Task task2 = new Task("Designing", formatterTime.parse("11:53"), resources, StatusTask.INACTIVE, imageData);
		Project project = new Project("Inventory Managment System", formatterDate.parse("03/24/2010"),
				formatterDate.parse("03/24/2014"), StatusProject.ACTIVE, benefits);
		project.getTaskList().add(task);
		project.getTaskList().add(task2);
		projectDao.save(project);

		// find the information of project based on description
		List<Project> projectList = projectDao.findByDescription("Inventory Managment System");
		for (Project p : projectList) {
			System.out.println("Project Name: " + p.getDescription());
		}
		// Updating the existing project based on description
		System.out.println("Enter project name to update: ");
		// Reading values form the console
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String projectname = br.readLine();
		List<Project> projectListtoUpdate = projectDao.findByDescription(projectname.trim());
		for (Project p : projectListtoUpdate) {
			if (projectname.equals(p.getDescription())) {
				System.out.println("Enter the new project name:");
				String newprojectname = br.readLine();
				p.setDescription(newprojectname);
				projectDao.save(p);
			}

		}
	}

}
