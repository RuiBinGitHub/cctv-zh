package com.springboot.biz.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.springboot.biz.PersonBiz;
import com.springboot.dao.PersonDao;
import com.springboot.entity.Person;

@Service
public class PersonBizImpl implements PersonBiz {

	@Resource
	private PersonDao personDao;

	public void insertPerson(Person person) {
		personDao.insertPerson(person);
	}

	public void updatePerson(Person person) {
		personDao.updatePerson(person);
	}

	public void deletePerson(Person person) {
		personDao.deletePerson(person);
	}

	public Person findInfoPerson(Map<String, Object> map) {
		return personDao.findInfoPerson(map);
	}

	public List<Person> findListPerson(Map<String, Object> map) {
		if (!StringUtils.isEmpty(map.get("name")))
			map.put("name", "%" + map.get("name") + "%");
		if (!StringUtils.isEmpty(map.get("page")))
			map.put("page", ((int) map.get("page") - 1) * 15);
		return personDao.findListPerson(map);
	}

	public int getCountPage(Map<String, Object> map, int size) {
		if (!StringUtils.isEmpty(map.get("name")))
			map.put("name", "%" + map.get("name") + "%");
		int count = personDao.getCount(map);
		return (int) Math.ceil((double) count / size);
	}

}
