package com.springboot.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.biz.ProjectBiz;
import com.springboot.entity.Project;
import com.springboot.util.AppInitPDF;
import com.springboot.util.AppUtils;

@RestController
@RequestMapping(value = "/file")
public class FileController {

	@Value(value = "${myfile}")
	private String path;
	@Resource
	private ProjectBiz projectBiz;
	@Resource
	private AppInitPDF appInitPDF;

	private Map<String, Object> map = null;

	@RequestMapping(value = "/download")
	public void download(int id, HttpServletResponse response) {
		try {
			map = AppUtils.getMap("id", id);
			Project project = projectBiz.findInfoProject(map);
			if (StringUtils.isEmpty(project))
				return;
			String srcPath = path + "report\\";
			String name = AppUtils.UUIDCode();
			File report = new File(srcPath + name);
			report.mkdirs(); // 创建文件夹
			appInitPDF.initPDF(project, srcPath + name + "\\");
			String zipPath = report.getPath() + "\\" + project.getName() + ".PDF";
			String fileName = project.getName() + ".PDF";
			fileName = URLEncoder.encode(fileName, "utf-8");
			response.setContentType("application/force-download");
			response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
			byte[] buffer = new byte[1024];
			InputStream fstream = new FileInputStream(zipPath);
			InputStream bstream = new BufferedInputStream(fstream);
			OutputStream outputStream = response.getOutputStream();
			int len = -1;
			while ((len = bstream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, len);
				outputStream.flush();
			}
			bstream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
