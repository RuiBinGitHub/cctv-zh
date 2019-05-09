package com.springboot.biz;

import java.util.List;
import java.util.Map;

import com.springboot.entity.Device;

public interface DeviceBiz {

	public void insertDevice(Device device);

	public void updateDevice(Device device);

	public void deleteDevice(Device device);

	public Device findInfoDevice(Map<String, Object> map);

	public List<Device> findListDevice(Map<String, Object> map);
	
	public int getCountPage(Map<String, Object> map, int size);
	
}
