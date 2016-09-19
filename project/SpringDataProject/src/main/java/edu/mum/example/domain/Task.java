package edu.mum.example.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Task {
	public enum StatusTask {
		PENDING, ACTIVE, INACTIVE, DELETED, COMPLELETED;
	}

	@Id
	@GeneratedValue
	@Column(name = "TaskID")
	private int taskID;
	private String descriptionTask;
	@Temporal(TemporalType.TIME)
	private Date timeFrame;
	@ElementCollection
	private List<String> resources = new ArrayList<>();
	@Enumerated(EnumType.STRING)
	private StatusTask statusTask;
	@Column(name="IMAGEFILE")
	private byte[] imageFile;

	public Task() {

	}

	public Task(String descriptionTask, Date timeFrame, List<String> resources, StatusTask inactive, byte[] imageFile) {
		this.descriptionTask = descriptionTask;
		this.resources = resources;
		this.timeFrame = timeFrame;
		this.statusTask = inactive;
		this.imageFile = imageFile;
	}

	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	public String getDescriptionTask() {
		return descriptionTask;
	}

	public void setDescriptionTask(String descriptionTask) {
		this.descriptionTask = descriptionTask;
	}

	public Date getTimeFrame() {
		return timeFrame;
	}

	public void setTimeFrame(Date timeFrame) {
		this.timeFrame = timeFrame;
	}

	public List<String> getResources() {
		return resources;
	}

	public void setResources(List<String> resources) {
		this.resources = resources;
	}

	public StatusTask getStatusTask() {
		return statusTask;
	}

	public void setStatusTask(StatusTask statusTask) {
		this.statusTask = statusTask;
	}

	public byte[] getImageFile() {
		return imageFile;
	}

	public void setImageFile(byte[] imageFile) {
		this.imageFile = imageFile;
	}

	@Override
	public String toString() {
		return "Task [taskID=" + taskID + ", descriptionTask=" + descriptionTask + ", timeFrame=" + timeFrame
				+ ", resources=" + resources + ", statusTask=" + statusTask + "]";
	}

}
