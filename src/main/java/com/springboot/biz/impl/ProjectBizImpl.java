package com.springboot.biz.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.springboot.biz.PipeBiz;
import com.springboot.biz.ProjectBiz;
import com.springboot.dao.ProjectDao;
import com.springboot.entity.Pipe;
import com.springboot.entity.Project;

@Service
public class ProjectBizImpl implements ProjectBiz {

	@Resource
	private ProjectDao projectDao;
	@Resource
	private PipeBiz pipeBiz;

	public void insertProject(Project project) {
		projectDao.insertProject(project);
	}

	public void updateProject(Project project) {
		projectDao.updateProject(project);
	}

	public void deleteProject(Project project) {
		projectDao.deleteProject(project);
	}

	public Project findInfoProject(Map<String, Object> map) {
		return projectDao.findInfoProject(map);
	}

	public List<Project> findListProject(Map<String, Object> map) {
		if (!StringUtils.isEmpty(map.get("name")))
			map.put("name", "%" + map.get("name") + "%");
		if (!StringUtils.isEmpty(map.get("page")))
			map.put("page", ((int) map.get("page") - 1) * 15);
		return projectDao.findListProject(map);
	}

	public int getCountPage(Map<String, Object> map, int size) {
		if (!StringUtils.isEmpty(map.get("name")))
			map.put("name", "%" + map.get("name") + "%");
		int count = projectDao.getCount(map);
		return (int) Math.ceil((double) count / size);
	}

	@Transactional
	public int appendProject(Project project) {
		this.insertProject(project);
		Pipe pipe = new Pipe();
		pipe.setNo(1);
		pipe.setMethod("--");
		pipe.setPipetype("--");
		pipe.setMaterial("--");
		pipe.setDirection("--");
		pipe.setProject(project);
		pipeBiz.appendPipe(pipe);
		return project.getId();
	}

}
