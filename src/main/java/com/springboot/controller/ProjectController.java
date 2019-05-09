package com.springboot.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.biz.DeviceBiz;
import com.springboot.biz.ItemInfoABiz;
import com.springboot.biz.ItemInfoBBiz;
import com.springboot.biz.ItemInfoCBiz;
import com.springboot.biz.PersonBiz;
import com.springboot.biz.ProjectBiz;
import com.springboot.entity.Device;
import com.springboot.entity.ItemInfoA;
import com.springboot.entity.ItemInfoB;
import com.springboot.entity.ItemInfoC;
import com.springboot.entity.Person;
import com.springboot.entity.Project;
import com.springboot.entity.User;
import com.springboot.util.AppUtils;

@RestController
@RequestMapping(value = "/project")
public class ProjectController {

	@Resource
	private ProjectBiz projectBiz;
	@Resource
	private ItemInfoABiz itemInfoABiz;
	@Resource
	private ItemInfoBBiz itemInfoBBiz;
	@Resource
	private ItemInfoCBiz itemInfoCBiz;
	@Resource
	private DeviceBiz deviceBiz;
	@Resource
	private PersonBiz personBiz;

	private Map<String, Object> map = null;

	@RequestMapping(value = "/insert")
	public ModelAndView insert(Project project) {
		ModelAndView view = new ModelAndView("redirect:/failure");
		if (StringUtils.isEmpty(project.getName()))
			return view;
		User user = (User) AppUtils.findMap("user");
		project.setDate(AppUtils.getDate(null));
		project.setCompany(user.getCompany());
		int id = projectBiz.appendProject(project);
		view.setViewName("redirect:/pipe/markpipe?id=" + id);
		return view;
	}

	@RequestMapping(value = "/update")
	public ModelAndView update(Project project) {
		ModelAndView view = new ModelAndView("redirect:/failure");
		User user = (User) AppUtils.findMap("user");
		map = AppUtils.getMap("id", project.getId(), "user", user);
		if (projectBiz.findInfoProject(map) == null)
			return view;
		project.setUser(user);
		project.setCompany(user.getCompany());
		projectBiz.updateProject(project);
		view.setViewName("redirect:/success");
		return view;
	}

	@RequestMapping(value = "/delete")
	public boolean delete(int id) {
		User user = (User) AppUtils.findMap("user");
		map = AppUtils.getMap("id", id, "user", user);
		Project project = projectBiz.findInfoProject(map);
		if (!StringUtils.isEmpty(project))
			projectBiz.deleteProject(project);
		return true;
	}

	@RequestMapping(value = "/remove")
	public boolean remove(int id) {
		User user = (User) AppUtils.findMap("user");
		map = AppUtils.getMap("id", id, "company", user.getCompany());
		Project project = projectBiz.findInfoProject(map);
		if (!StringUtils.isEmpty(project))
			projectBiz.deleteProject(project);
		return true;
	}

	@RequestMapping(value = "/insertview")
	public ModelAndView insertview(){
		ModelAndView view = new ModelAndView("project/insert");
		User user = (User) AppUtils.findMap("user");
		map = AppUtils.getMap("company", user.getCompany());
		List<Person> persons = personBiz.findListPerson(map);
		view.addObject("persons", persons);
		return view;
	}
	
	@RequestMapping(value = "/updateview")
	public ModelAndView updateview(int id) {
		ModelAndView view = new ModelAndView("user/failure");
		User user = (User) AppUtils.findMap("user");
		map = AppUtils.getMap("id", id, "user", user);
		Project project = projectBiz.findInfoProject(map);
		if (StringUtils.isEmpty(project))
			return view;
		map = AppUtils.getMap("company", user.getCompany());
		List<Person> persons = personBiz.findListPerson(map);
		view.setViewName("project/update");
		view.addObject("project", project);
		view.addObject("persons", persons);
		return view;
	}

	@RequestMapping(value = "/showlist")
	public ModelAndView showlist(String name, @RequestParam(defaultValue = "1") int page) {
		ModelAndView view = new ModelAndView("project/showlist");
		User user = (User) AppUtils.findMap("user");
		map = AppUtils.getMap("user", user);
		if (!StringUtils.isEmpty(name))
			map.put("name", name);
		int cont = projectBiz.getCountPage(map, 15);
		page = page > cont ? cont : page;
		map.put("page", page);
		List<Project> projects = projectBiz.findListProject(map);
		view.addObject("projects", projects);
		view.addObject("page", page);
		view.addObject("cont", cont);
		return view;
	}

	@RequestMapping(value = "/operator")
	public ModelAndView operator(String name, @RequestParam(defaultValue = "1") int page) {
		ModelAndView view = new ModelAndView("project/operator");
		User user = (User) AppUtils.findMap("user");
		map = AppUtils.getMap("company", user.getCompany());
		if (!StringUtils.isEmpty(name))
			map.put("name", name);
		int cont = projectBiz.getCountPage(map, 15);
		page = page > cont ? cont : page;
		map.put("page", page);
		List<Project> projects = projectBiz.findListProject(map);
		for (Project project : projects)
			project.setUser(user);
		view.addObject("projects", projects);
		view.addObject("page", page);
		view.addObject("cont", cont);

		return view;
	}

	@RequestMapping(value = "/findinfoA")
	public ModelAndView findInfoA(int id) {
		ModelAndView view = new ModelAndView("user/failure");
		User user = (User) AppUtils.findMap("user");
		map = AppUtils.getMap("id", id, "user", user);
		Project project = projectBiz.findInfoProject(map);
		if (StringUtils.isEmpty(project))
			return view;
		map = AppUtils.getMap("project", project);
		ItemInfoA itemInfoA = itemInfoABiz.findInfoItemInfoA(map);
		if (StringUtils.isEmpty(itemInfoA)) {
			itemInfoA = new ItemInfoA();
			itemInfoA.setConditio("");
			itemInfoA.setMaterial("");
			itemInfoA.setProject(project);
			itemInfoABiz.insertItemInfoA(itemInfoA);
		}
		view.setViewName("project/findinfoA");
		view.addObject("itemInfoA", itemInfoA);
		view.addObject("project", project);
		return view;
	}

	@RequestMapping(value = "/findinfoB")
	public ModelAndView findInfoB(int id) {
		ModelAndView view = new ModelAndView("user/failure");
		User user = (User) AppUtils.findMap("user");
		map = AppUtils.getMap("id", id, "user", user);
		Project project = projectBiz.findInfoProject(map);
		if (StringUtils.isEmpty(project))
			return view;
		map = AppUtils.getMap("project", project);
		ItemInfoB itemInfoB = itemInfoBBiz.findInfoItemInfoB(map);
		if (StringUtils.isEmpty(itemInfoB)) {
			itemInfoB = new ItemInfoB();
			itemInfoB.setProject(project);
			itemInfoBBiz.insertItemInfoB(itemInfoB);
		}
		map = AppUtils.getMap("company", user.getCompany());
		List<Device> devices = deviceBiz.findListDevice(map);
		List<Person> persons = personBiz.findListPerson(map);
		view.setViewName("project/findinfoB");
		view.addObject("itemInfoB", itemInfoB);
		view.addObject("project", project);
		view.addObject("devices", devices);
		view.addObject("persons", persons);
		return view;
	}

	@RequestMapping(value = "/findinfoC")
	public ModelAndView findInfoC(int id) {
		ModelAndView view = new ModelAndView("user/failure");
		User user = (User) AppUtils.findMap("user");
		map = AppUtils.getMap("id", id, "user", user);
		Project project = projectBiz.findInfoProject(map);
		if (StringUtils.isEmpty(project))
			return view;
		map = AppUtils.getMap("project", project);
		ItemInfoC itemInfoC = itemInfoCBiz.findInfoItemInfoC(map);
		if (StringUtils.isEmpty(itemInfoC)) {
			itemInfoC = new ItemInfoC();
			itemInfoC.setProject(project);
			itemInfoCBiz.insertItemInfoC(itemInfoC);
		}
		view.setViewName("project/findinfoC");
		view.addObject("itemInfoC", itemInfoC);
		view.addObject("project", project);
		return view;
	}
}
