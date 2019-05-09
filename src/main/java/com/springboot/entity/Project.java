package com.springboot.entity;

public class Project {

	private int id;
	private String no;
	private String name;
	private String site;
	private String client;
	private String person;
	private String writer;
	private String checker;
	private String approver;
	private int infoA;
	private int infoB;
	private int infoC;
	private String date;
	private User user;
	private Company company;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public int getInfoA() {
		return infoA;
	}

	public void setInfoA(int infoA) {
		this.infoA = infoA;
	}

	public int getInfoB() {
		return infoB;
	}

	public void setInfoB(int infoB) {
		this.infoB = infoB;
	}

	public int getInfoC() {
		return infoC;
	}

	public void setInfoC(int infoC) {
		this.infoC = infoC;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
