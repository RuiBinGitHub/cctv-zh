package com.springboot;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.biz.DeviceBiz;
import com.springboot.biz.PipeBiz;
import com.springboot.biz.ProjectBiz;
import com.springboot.biz.UserBiz;
import com.springboot.entity.Device;
import com.springboot.entity.Project;
import com.springboot.util.AppInitPDF;
import com.springboot.util.AppUtils;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MainApplicationTests {

	@Resource
	private AppInitPDF appInitPDF;
	@Resource
	private ProjectBiz projectBiz;
	@Resource
	private PipeBiz pipeBiz;
	@Resource
	private UserBiz userBiz;
	@Resource
	private DeviceBiz deviceBiz;

	public Map<String, Object> map = null;

	@Test
	public void contextLoads() {
		String name = "1,2";
		List<String> list = Arrays.asList(name.split(","));
		map = AppUtils.getMap("list", list);
		List<Device> devices = deviceBiz.findListDevice(map);
		System.out.println(devices.size());
	}

	@Test
	public void fun() {
		map = AppUtils.getMap("id", 1);
		Project project = projectBiz.findInfoProject(map);
		appInitPDF.initPDF(project, "d:/");
		System.out.println("--");
	}
}
