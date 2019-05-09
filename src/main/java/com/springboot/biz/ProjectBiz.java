package com.springboot.biz;

import java.util.List;
import java.util.Map;

import com.springboot.entity.Project;

public interface ProjectBiz {

	public void insertProject(Project project);

	public void updateProject(Project project);

	public void deleteProject(Project project);

	public Project findInfoProject(Map<String, Object> map);

	public List<Project> findListProject(Map<String, Object> map);

	public int getCountPage(Map<String, Object> map, int size);

	public int appendProject(Project project);

}
