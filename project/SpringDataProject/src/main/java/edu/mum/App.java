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
import edu.mum.example.dao.TaskDAO;
import edu.mum.example.domain.Project;
import edu.mum.example.domain.Project.StatusProject;
import edu.mum.example.domain.Task;
import edu.mum.example.domain.Task.StatusTask;

public class App {
	@Autowired
	ProjectDAO projectDao;
	@Autowired
	TaskDAO taskDao;

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
		ProjectServiceImp projectServiceImp = new ProjectServiceImp();
		Project p;
		SimpleDateFormat formatterDate = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");
		File file = new File("F:\test.jpg");
		byte[] imageData = new byte[(int) file.length()];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String user = br.readLine();
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
		// selecting the user
		System.out.println("Type A for admin / V for Volunteer");
		String us = br.readLine();
		if (us.equalsIgnoreCase("A")) {

			loop: while (true) {
				System.out.println(
						"Enter your choice: \n1. Search project based on project name \n 2. Updat project based on project name \n 3. Update status of project\n 4. Update status of Task  \n 5. Exit ");
				int ch = Integer.parseInt(br.readLine());
				switch (ch) {
				case 1:
					// find the information of project based on description
					System.out.println("Enter your project name: ");
					String pname = br.readLine();
					Project p1 = projectServiceImp.searchProject(pname, projectDao.findByDescription(pname));
					System.out.println("Project Name: " + p1.getDescription());
					break;
				case 2:
					// Updating the existing project based on description
					System.out.println("Enter project name to update: ");
					// Reading values form the console
					String projectname = br.readLine();
					List<Project> projectListtoUpdate = projectDao.findByDescription(projectname.trim());
					for (Project p2 : projectListtoUpdate) {
						if (projectname.equals(p2.getDescription())) {
							System.out.println("Enter the new project name:");
							String newprojectname = br.readLine();
							p2.setDescription(newprojectname);
							projectDao.save(p2);
						}

					}
					break;
				case 3:
					// updating status of the project
					System.out.println("Enter project ID to update: ");
					int projectid = Integer.parseInt(br.readLine());
					p = projectDao.findByProjectID(projectid);
					if (p != null) {
						System.out.println(
								"Enter project status(PENDING, ACTIVE, INACTIVE, DELETED, COMPLELETED)-Type any one of these ");
						String status = br.readLine();
						if (status.equalsIgnoreCase("Active"))
							p.setStatusProduct(p.getStatusProduct().ACTIVE);
						if (status.equalsIgnoreCase("pending"))
							p.setStatusProduct(p.getStatusProduct().PENDING);
						if (status.equalsIgnoreCase("inactive"))
							p.setStatusProduct(p.getStatusProduct().INACTIVE);
						if (status.equalsIgnoreCase("deleted"))
							p.setStatusProduct(p.getStatusProduct().DELETED);
						if (status.equalsIgnoreCase("COMPLELETED"))
							p.setStatusProduct(p.getStatusProduct().COMPLELETED);
						projectDao.save(p);
					}
					break;
				case 4:
					System.out.println("Enter project ID to update: ");
					int proID = Integer.parseInt(br.readLine());
					p = projectDao.findByProjectID(proID);
					if (p != null) {
						System.out.println("Enter Task ID to update: ");
						int taskID = Integer.parseInt(br.readLine());
						List<Task> listTask = taskDao.findBytaskID(p);
						// for(Task)
						for (Task t : listTask) {
							if (t.getTaskID() == taskID) {
								System.out.println(
										"Enter project status(PENDING, ACTIVE, INACTIVE, DELETED, COMPLELETED)-Type any one of these ");
								String status = br.readLine();
								if (status.equalsIgnoreCase("Active"))
									t.setStatusTask(t.getStatusTask().ACTIVE);
								if (status.equalsIgnoreCase("pending"))
									t.setStatusTask(t.getStatusTask().PENDING);
								if (status.equalsIgnoreCase("inactive"))
									t.setStatusTask(t.getStatusTask().INACTIVE);
								if (status.equalsIgnoreCase("deleted"))
									t.setStatusTask(t.getStatusTask().DELETED);
								if (status.equalsIgnoreCase("COMPLELETED"))
									t.setStatusTask(t.getStatusTask().COMPLELETED);
								taskDao.save(t);
							}

						}
						projectDao.save(p);
					}
					break;
				case 5:
					break loop;
				}
			}
		} else {
			// Volunteer part of the project
			endLoop: while (true) {
				System.out.println(
						"\n 1. All tasks to different projects \n 2. See information about projects and their beneficiaries \n 3.PROJECT INFORMATION ON THE BASIC OF RESOURCE SKILL 4. Exit ");
				String ch = br.readLine();
				switch (ch) {
				case "1":
					// List of different tasks assigned to different Projects
					// System.out.println("hi");
					for (Project pro : projectDao.findAll()) {
						System.out.println("===============" + pro.getDescription() + "=====================");
						for (Task tk : taskDao.findBytaskID(pro)) {
							System.out.println(tk);
						}
					}

					break;
				case "2":
					// See information about projects and their beneficiaries
					for (Project pro : projectDao.findAll()) {
						System.out.println(pro.getDescription());
						System.out.println(pro.getListBeneficiaries());
					}
					break;
				case "3":
					System.out.println("===============PROJECT INFORMATION ON THE BASIC OF RESOURCE SKILL===========");
					System.out.println("Enter your resource requirement  ");
					String res = br.readLine();
					for (Project pro : projectDao.findAll()) {
						for (Task tk : taskDao.findBytaskID(pro)) {
							if (tk.getResources().contains(res)) {
								System.out.println("Project Name: " + pro.getDescription());
								break;
							}
						}
					}
					break;
				case "4":
					break endLoop;
				}
			}
		}

	}

}
