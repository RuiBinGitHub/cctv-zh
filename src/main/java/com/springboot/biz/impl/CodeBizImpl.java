package com.springboot.biz.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springboot.biz.CodeBiz;
import com.springboot.dao.CodeDao;
import com.springboot.entity.Code;
import com.springboot.util.AppUtils;

@Service
public class CodeBizImpl implements CodeBiz {

	@Resource
	private CodeDao codeDao;

	private Map<String, Object> map = null;

	public Code findInfoCode(Map<String, Object> map) {
		return codeDao.findInfoCode(map);
	}

	public Code findInfoCode(String code, String grade) {
		map = AppUtils.getMap("code", code, "grade", grade);
		return codeDao.findInfoCode(map);
	}

	public List<Code> findListCode(Map<String, Object> map) {
		return codeDao.findListCode(map);
	}

}
