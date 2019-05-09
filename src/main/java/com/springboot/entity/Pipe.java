package com.springboot.entity;

import java.util.List;

public class Pipe {

	private int id;
	private int no;
	private String video;
	private String smanhole;
	private String fmanhole;
	private String method;
	private String laidyear;
	private String sdepth;
	private String fdepth;
	private String pipetype;
	private String material;
	private String diameter;
	private String direction;
	private String pipelength;
	private String testlength;
	private String site;
	private String date;
	private String remarks;
	private Project project;
	private List<Item> items;

	private double RI;
	private double MI;
	private double[] value;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getSmanhole() {
		return smanhole;
	}

	public void setSmanhole(String smanhole) {
		this.smanhole = smanhole;
	}

	public String getFmanhole() {
		return fmanhole;
	}

	public void setFmanhole(String fmanhole) {
		this.fmanhole = fmanhole;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getLaidyear() {
		return laidyear;
	}

	public void setLaidyear(String laidyear) {
		this.laidyear = laidyear;
	}

	public String getSdepth() {
		return sdepth;
	}

	public void setSdepth(String sdepth) {
		this.sdepth = sdepth;
	}

	public String getFdepth() {
		return fdepth;
	}

	public void setFdepth(String fdepth) {
		this.fdepth = fdepth;
	}

	public String getPipetype() {
		return pipetype;
	}

	public void setPipetype(String pipetype) {
		this.pipetype = pipetype;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getDiameter() {
		return diameter;
	}

	public void setDiameter(String diameter) {
		this.diameter = diameter;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getPipelength() {
		return pipelength;
	}

	public void setPipelength(String pipelength) {
		this.pipelength = pipelength;
	}

	public String getTestlength() {
		return testlength;
	}

	public void setTestlength(String testlength) {
		this.testlength = testlength;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public double getRI() {
		return RI;
	}

	public void setRI(double rI) {
		RI = rI;
	}

	public double getMI() {
		return MI;
	}

	public void setMI(double mI) {
		MI = mI;
	}

	public double[] getValue() {
		return value;
	}

	public void setValue(double[] value) {
		this.value = value;
	}

}
