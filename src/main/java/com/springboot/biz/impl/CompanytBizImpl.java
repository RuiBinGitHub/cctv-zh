package com.springboot.biz.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springboot.biz.CompanyBiz;
import com.springboot.dao.CompanyDao;
import com.springboot.entity.Company;

@Service
public class CompanytBizImpl implements CompanyBiz {

	@Resource
	private CompanyDao companyDao;

	public void insertCompany(Company company) {
		companyDao.insertCompany(company);
	}

	public void updateCompany(Company company) {
		companyDao.updateCompany(company);
	}

	public void deleteCompany(Company company) {
		companyDao.deleteCompany(company);
	}

	public Company findInfoCompany(Map<String, Object> map) {
		return companyDao.findInfoCompany(map);
	}

	public List<Company> findListCompany(Map<String, Object> map) {
		return companyDao.findListCompany(map);
	}

}
