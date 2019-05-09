package com.springboot.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.entity.Code;

@Mapper
public interface CodeDao {

	public Code findInfoCode(Map<String, Object> map);

	public List<Code> findListCode(Map<String, Object> map);
	
}
