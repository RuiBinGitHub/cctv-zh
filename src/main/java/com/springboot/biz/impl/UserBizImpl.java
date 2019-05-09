package com.springboot.biz.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.springboot.biz.UserBiz;
import com.springboot.dao.UserDao;
import com.springboot.entity.User;

@Service
public class UserBizImpl implements UserBiz {

	@Resource
	private UserDao usersDao;

	public void insertUser(User user) {
		usersDao.insertUser(user);
	}

	public void updateUser(User user) {
		usersDao.updateUser(user);
	}

	public void deleteUser(User user) {
		usersDao.deleteUser(user);
	}

	public User findInfoUser(Map<String, Object> map) {
		return usersDao.findInfoUser(map);
	}

	public List<User> findListUser(Map<String, Object> map) {
		if (!StringUtils.isEmpty(map.get("name")))
			map.put("name", "%" + map.get("name") + "%");
		if (!StringUtils.isEmpty(map.get("page")))
			map.put("page", ((int) map.get("page") - 1) * 15);
		return usersDao.findListUser(map);
	}
	
	public int getCountPage(Map<String, Object> map, int size) {
		if (!StringUtils.isEmpty(map.get("name")))
			map.put("name", "%" + map.get("name") + "%");
		int count = usersDao.getCount(map);
		return (int) Math.ceil((double) count / size);
	}

}
