package edu.mum;

import java.io.File;
import java.io.FileInputStream;
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
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
				"application-context.xml");
		App a = context.getBean("mainApp", App.class);
		try {
			a.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
		context.close();
	}

	public void setRepository(ProjectDAO projectDao) {
		//this.studentDao = repository;
		this.projectDao=projectDao;
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
		
		
		
		
//		// save a couple of students
//		studentDao.save(new Student("John", "Doe"));
//		studentDao.save(new Student("Clark", "Kent"));
//		studentDao.save(new Student("Lisa", "Lane"));
//		studentDao.save(new Student("Lex", "Lutor"));
//		studentDao.save(new Student("Peter", "Parker"));
//
//		// fetch all students
//		System.out.println("Students found with findAll():");
//		System.out.println("-------------------------------");
//		for (Student student : studentDao.findAll()) {
//			System.out.println(student);
//		}
//		System.out.println();
//
//		// fetch an individual student by ID
//		Student student = studentDao.findOne(1L);
//		System.out.println("Person found with findOne(1L):");
//		System.out.println("--------------------------------");
//		System.out.println(student);
//		System.out.println();
//
//		// fetch students by last name
//		System.out.println("Person found with findByLastName('Bauer'):");
//		System.out.println("--------------------------------------------");
//		for (Student bauer : studentDao.findByLastName("Bauer")) {
//			System.out.println(bauer);
//		}
//
//		PageRequest pageRequest = new PageRequest(0, 10);
//		Page<Student> page;
//		do {
//			page = studentDao.findAll(pageRequest);
//			for (Student st : page.getContent()) {
//				System.out.println(st);
//			}
//			if (page.hasNext()) {
//				pageRequest = (PageRequest) pageRequest.next();
//			}
//		} while (page.hasNext());
//		
//		pageRequest = new PageRequest(0, 10);
//		Slice<Student> slice = studentDao.findByFirstName("Lisa", pageRequest);
//		do {
//			slice = studentDao.findAll(pageRequest);
//			for (Student st : slice.getContent()) {
//				System.out.println(st);
//			}
//			if (slice.hasNext()) {
//				pageRequest = (PageRequest) pageRequest.next();
//			}
//		} while (slice.hasNext());

	}

}
