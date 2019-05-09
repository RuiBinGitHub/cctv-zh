package com.springboot.biz;

import java.util.List;
import java.util.Map;

import com.springboot.entity.User;

public interface UserBiz {

	public void insertUser(User user);

	public void updateUser(User user);

	public void deleteUser(User user);

	public User findInfoUser(Map<String, Object> map);

	public List<User> findListUser(Map<String, Object> map);
}
