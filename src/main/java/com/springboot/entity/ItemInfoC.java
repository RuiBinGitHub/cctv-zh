package com.springboot.entity;

public class ItemInfoC {

	private int id;
	private String inspection;
	private String workdate1;
	private String workdate2;
	private String collect;
	private String compile;
	private Project project;

	public int getId() {
		return id;
	}

	public String getInspection() {
		return inspection;
	}

	public void setInspection(String inspection) {
		this.inspection = inspection;
	}

	public String getWorkdate1() {
		return workdate1;
	}

	public void setWorkdate1(String workdate1) {
		this.workdate1 = workdate1;
	}

	public String getWorkdate2() {
		return workdate2;
	}

	public void setWorkdate2(String workdate2) {
		this.workdate2 = workdate2;
	}

	public String getCollect() {
		return collect;
	}

	public void setCollect(String collect) {
		this.collect = collect;
	}

	public String getCompile() {
		return compile;
	}

	public void setCompile(String compile) {
		this.compile = compile;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public void setId(int id) {
		this.id = id;
	}

}
