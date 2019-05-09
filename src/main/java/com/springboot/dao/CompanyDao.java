package com.springboot.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.entity.Company;

@Mapper
public interface CompanyDao {

	public void insertCompany(Company company);

	public void updateCompany(Company company);

	public void deleteCompany(Company company);

	public Company findInfoCompany(Map<String, Object> map);

	public List<Company> findListCompany(Map<String, Object> map);

}
