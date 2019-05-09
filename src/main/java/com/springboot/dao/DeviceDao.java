package com.springboot.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.entity.Device;

@Mapper
public interface DeviceDao {

	public void insertDevice(Device device);

	public void updateDevice(Device device);

	public void deleteDevice(Device device);

	public Device findInfoDevice(Map<String, Object> map);

	public List<Device> findListDevice(Map<String, Object> map);
	
	public int getCount(Map<String, Object> map);
}
