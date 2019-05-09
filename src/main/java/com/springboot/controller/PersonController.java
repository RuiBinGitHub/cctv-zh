package com.springboot.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.biz.PersonBiz;
import com.springboot.entity.Person;
import com.springboot.entity.User;
import com.springboot.util.AppUtils;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

	@Resource
	private PersonBiz personBiz;

	private Map<String, Object> map = null;

	@RequestMapping(value = "/insert")
	public ModelAndView insert(Person person) {
		ModelAndView view = new ModelAndView("user/failure");
		if (StringUtils.isEmpty(person.getName()))
			return view;
		User user = (User) AppUtils.findMap("user");
		person.setDate(AppUtils.getDate(null));
		person.setCompany(user.getCompany());
		personBiz.insertPerson(person);
		view.setViewName("redirect:/success");
		return view;
	}

	@RequestMapping(value = "/update")
	public ModelAndView update(Person person) {
		ModelAndView view = new ModelAndView("user/failure");
		User user = (User) AppUtils.findMap("user");
		map = AppUtils.getMap("id", person.getId(), "company", user.getCompany());
		if (personBiz.findInfoPerson(map) == null)
			return view;
		person.setCompany(user.getCompany());
		personBiz.updatePerson(person);
		view.setViewName("redirect:/success");
		return view;
	}

	@RequestMapping(value = "/delete")
	public boolean delete(int id) {
		User user = (User) AppUtils.findMap("user");
		map = AppUtils.getMap("id", id, "company", user.getCompany());
		Person person = personBiz.findInfoPerson(map);
		if (!StringUtils.isEmpty(person))
			personBiz.deletePerson(person);
		return true;
	}

	@RequestMapping(value = "/updateview")
	public ModelAndView updateview(int id) {
		ModelAndView view = new ModelAndView("user/failure");
		User user = (User) AppUtils.findMap("user");
		map = AppUtils.getMap("id", id, "company", user.getCompany());
		Person person = personBiz.findInfoPerson(map);
		if (StringUtils.isEmpty(person))
			return view;
		view.setViewName("person/update");
		view.addObject("person", person);
		return view;
	}

	@RequestMapping(value = "/showlist")
	public ModelAndView showlist(String name, @RequestParam(defaultValue = "1") int page) {
		ModelAndView view = new ModelAndView("person/showlist");
		User user = (User) AppUtils.findMap("user");
		map = AppUtils.getMap("company", user.getCompany());
		if (!StringUtils.isEmpty(name))
			map.put("name", name);
		int cont = personBiz.getCountPage(map, 15);
		page = page > cont ? cont : page;
		map.put("page", page);
		List<Person> persons = personBiz.findListPerson(map);
		view.addObject("persons", persons);
		view.addObject("page", page);
		view.addObject("cont", cont);
		return view;
	}

}
