package com.springboot.biz.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.springboot.biz.PipeBiz;
import com.springboot.dao.PipeDao;
import com.springboot.entity.Pipe;

@Service
public class PipeBizImpl implements PipeBiz {

	@Resource
	private PipeDao pipeDap;

	public void insertPipe(Pipe pipe) {
		pipeDap.insertPipe(pipe);
	}

	public void updatePipe(Pipe pipe) {
		pipeDap.updatePipe(pipe);
	}

	public void deletePipe(Pipe pipe) {
		pipeDap.deletePipe(pipe);
	}

	public Pipe findInfoPipe(Map<String, Object> map) {
		if (!StringUtils.isEmpty(map.get("no")))
			map.put("no", (int) map.get("no") - 1);
		return pipeDap.findInfoPipe(map);
	}

	public List<Pipe> findListPipe(Map<String, Object> map) {
		return pipeDap.findListPipe(map);
	}

	public List<String> findListName(Map<String, Object> map) {
		return pipeDap.findListName(map);
	}

	public int getCount(Map<String, Object> map) {
		return pipeDap.getCount(map);
	}

	public int appendPipe(Pipe pipe) {
		this.insertPipe(pipe);
		return 0;
	}

}
