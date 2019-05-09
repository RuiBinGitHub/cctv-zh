package com.springboot.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.entity.ItemInfoC;

@Mapper
public interface ItemInfoCDao {

	public void insertItemInfoC(ItemInfoC itemInfoC);

	public void updateItemInfoC(ItemInfoC itemInfoC);

	public void deleteItemInfoC(ItemInfoC itemInfoC);

	public ItemInfoC findInfoItemInfoC(Map<String, Object> map);

}
