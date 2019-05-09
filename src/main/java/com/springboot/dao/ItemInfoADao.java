package com.springboot.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.entity.ItemInfoA;

@Mapper
public interface ItemInfoADao {

	public void insertItemInfoA(ItemInfoA itemInfoA);

	public void updateItemInfoA(ItemInfoA itemInfoA);

	public void deleteItemInfoA(ItemInfoA itemInfoA);

	public ItemInfoA findInfoItemInfoA(Map<String, Object> map);

}
