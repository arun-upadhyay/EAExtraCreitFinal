package edu.mum.example.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Project {
	public enum StatusProject {
		PENDING, ACTIVE, INACTIVE, DELETED, COMPLELETED;
	}

	@Id
	@GeneratedValue
	@Column(name = "ProjectID")
	private int projectID;
	private String description;
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Temporal(TemporalType.DATE)
	private Date endDate;
	@Enumerated(EnumType.STRING)
	private StatusProject statusProject;
	@ElementCollection
	private List<String> listBeneficiaries = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Project_Task")
	private Collection<Task> taskList = new ArrayList<>();

	public Project() {

	}

	public Project(String description, Date startDate, Date endDate, StatusProject statusProduct,
			List<String> listBeneficiaries) {
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.statusProject = statusProduct;
		this.listBeneficiaries = listBeneficiaries;
	}

	public int getProductID() {
		return projectID;
	}

	public void setProductID(int productID) {
		this.projectID = productID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public StatusProject getStatusProduct() {
		return statusProject;
	}

	public void setStatusProduct(StatusProject statusProduct) {
		this.statusProject = statusProduct;
	}

	public List<String> getListBeneficiaries() {
		return listBeneficiaries;
	}

	public void setListBeneficiaries(List<String> listBeneficiaries) {
		this.listBeneficiaries = listBeneficiaries;
	}

	public Collection<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(Collection<Task> taskList) {
		this.taskList = taskList;
	}

}
