package com.springboot.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.entity.User;

@Mapper
public interface UserDao {

	public void insertUser(User user);

	public void updateUser(User user);

	public void deleteUser(User user);

	public User findInfoUser(Map<String, Object> map);

	public List<User> findListUser(Map<String, Object> map);

	public int getCount(Map<String, Object> map);

}
