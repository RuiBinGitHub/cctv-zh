package com.springboot.biz;

import java.util.List;
import java.util.Map;


import com.springboot.entity.Company;

public interface CompanyBiz {

	public void insertCompany(Company company);

	public void updateCompany(Company company);

	public void deleteCompany(Company company);

	public Company findInfoCompany(Map<String, Object> map);

	public List<Company> findListCompany(Map<String, Object> map);

}
