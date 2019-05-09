package com.springboot.biz;

import java.util.List;
import java.util.Map;

import com.springboot.entity.Person;

public interface PersonBiz {

	public void insertPerson(Person person);

	public void updatePerson(Person person);

	public void deletePerson(Person person);

	public Person findInfoPerson(Map<String, Object> map);

	public List<Person> findListPerson(Map<String, Object> map);

	public int getCountPage(Map<String, Object> map, int size);
}
