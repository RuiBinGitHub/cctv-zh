package com.springboot.biz.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.springboot.biz.DeviceBiz;
import com.springboot.dao.DeviceDao;
import com.springboot.entity.Device;

@Service
public class DeviceBizImpl implements DeviceBiz {

	@Resource
	private DeviceDao deviceDao;

	public void insertDevice(Device device) {
		deviceDao.insertDevice(device);
	}

	public void updateDevice(Device device) {
		deviceDao.updateDevice(device);
	}

	public void deleteDevice(Device device) {
		deviceDao.deleteDevice(device);
	}

	public Device findInfoDevice(Map<String, Object> map) {
		return deviceDao.findInfoDevice(map);
	}

	public List<Device> findListDevice(Map<String, Object> map) {
		if (!StringUtils.isEmpty(map.get("name")))
			map.put("name", "%" + map.get("name") + "%");
		if (!StringUtils.isEmpty(map.get("page")))
			map.put("page", ((int) map.get("page") - 1) * 15);
		return deviceDao.findListDevice(map);
	}

	public int getCountPage(Map<String, Object> map, int size) {
		if (!StringUtils.isEmpty(map.get("name")))
			map.put("name", "%" + map.get("name") + "%");
		int count = deviceDao.getCount(map);
		return (int) Math.ceil((double) count / size);
	}

}
