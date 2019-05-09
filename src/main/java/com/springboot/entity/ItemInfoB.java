package com.springboot.entity;

public class ItemInfoB {

	private int id;
	private String devices;
	private String persons;
	private Project project;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDevices() {
		return devices;
	}

	public void setDevices(String devices) {
		this.devices = devices;
	}

	public String getPersons() {
		return persons;
	}

	public void setPersons(String persons) {
		this.persons = persons;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
