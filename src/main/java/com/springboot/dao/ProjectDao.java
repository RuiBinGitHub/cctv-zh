package com.springboot.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.entity.Project;

@Mapper
public interface ProjectDao {

	public void insertProject(Project project);

	public void updateProject(Project project);

	public void deleteProject(Project project);

	public Project findInfoProject(Map<String, Object> map);

	public List<Project> findListProject(Map<String, Object> map);

	public int getCount(Map<String, Object> map);
}
