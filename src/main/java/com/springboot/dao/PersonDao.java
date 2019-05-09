package com.springboot.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.entity.Person;

@Mapper
public interface PersonDao {

	public void insertPerson(Person person);

	public void updatePerson(Person person);

	public void deletePerson(Person person);

	public Person findInfoPerson(Map<String, Object> map);

	public List<Person> findListPerson(Map<String, Object> map);

	public int getCount(Map<String, Object> map);
	
}
