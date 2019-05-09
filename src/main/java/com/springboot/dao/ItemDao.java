package com.springboot.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.entity.Item;

@Mapper
public interface ItemDao {

	public void insertItem(Item item);

	public void updateItem(Item item);

	public void deleteItem(Item item);

	public Item findInfoItem(Map<String, Object> map);

	public List<Item> findListItem(Map<String, Object> map);
	
}
