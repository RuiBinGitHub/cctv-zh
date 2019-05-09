package com.springboot;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import com.springboot.entity.Item;
import com.springboot.entity.Pipe;
import com.springboot.entity.Project;

public class ApplicationTest {

	public static void main(String[] args) {
		Workbook workbook = getWorkbook();
		Sheet sheet = workbook.getSheetAt(0);
		Project project = new Project();
		Row row = sheet.getRow(2);
		project.setName(row.getCell(16).toString());
		project.setPerson(row.getCell(18).toString());
		project.setChecker(row.getCell(20).toString());

		int index = 1;
		Pipe pipe = null;
		Item item = null;
		List<Pipe> pipes = new ArrayList<>();
		List<Item> items = new ArrayList<>();
		for (int i = 2; i < 5; i++) {
			row = sheet.getRow(i); // 获取记录信息
			String smh = row.getCell(1).toString(); // 获取起始井编号
			String fmh = row.getCell(2).toString(); // 获取起始井编号
			if (!StringUtils.isEmpty(smh) && !StringUtils.isEmpty(fmh)) {
				pipe = new Pipe();
				pipe.setSmanhole(smh); // 起始井号
				pipe.setFmanhole(fmh); // 终止井号
				pipe.setDiameter(row.getCell(3).toString()); // 管道直径
				pipe.setMaterial(row.getCell(4).toString()); // 管道材质
				pipe.setPipelength(row.getCell(5).toString()); // 管道长度
				pipe.setTestlength(row.getCell(6).toString()); // 测量长度

				pipe.setDate(row.getCell(21).toString()); // 检测日期
				pipe.setMethod(row.getCell(22).toString()); // 检测方法
				pipe.setSdepth(getVlaue(row.getCell(23))); // 起始埋深
				pipe.setFdepth(getVlaue(row.getCell(24))); // 终止埋深
				pipe.setLaidyear(getVlaue(row.getCell(25))); // 敷设年代
				pipe.setPipetype(getVlaue(row.getCell(26))); // 管道类型
				pipe.setVideo(row.getCell(27).toString()); // 录像文件
				pipe.setDirection(row.getCell(28).toString()); // 检测方向
				pipes.add(pipe);
				index = 1;
			}
			item = new Item();
			item.setNo(index++);
			item.setDist(row.getCell(7).toString());
			item.setCode(getCode(row.getCell(8).toString()));
			item.setGrade(row.getCell(9).toString());
			item.setLocation(row.getCell(10).toString());
			item.setPicture(row.getCell(11).toString());
			item.setRemarks(row.getCell(13).toString());
			item.setPipe(pipe);
			items.add(item);
		}
		System.out.println(pipes.size());
		System.out.println(items.size());
	}

	public static Workbook getWorkbook() {
		try {
			File file = ResourceUtils.getFile("classpath:" + "0001.xls");
			InputStream stream = new FileInputStream(file);
			Workbook workbook = WorkbookFactory.create(stream);
			return workbook;
		} catch (IOException | InvalidFormatException e) {
			e.printStackTrace();
			return null;
		}
	}

	/** 获取单元格的值 */
	public static String getVlaue(Cell cell) {
		try {
			return cell.toString();
		} catch (Exception e) {
			return "";
		}
	}

	/** 获取小数值 */
	public static double getDouble(String value) {
		try {
			return Double.valueOf(value);
		} catch (Exception e) {
			return 0;
		}
	}

	/** 获取整数值 */
	public static int getInt(String value) {
		try {
			return Integer.valueOf(value);
		} catch (Exception e) {
			return 0;
		}
	}
	
	/** 获取code */
	public static String getCode(String name){
		if ("无异常".equals(name))
			return "";
		else if ("破裂".equals(name))
			return "PL";
		else if ("变形".equals(name))
			return "BX";
		else if ("腐蚀".equals(name))
			return "FS";
		else if ("错口".equals(name))
			return "CK";
		else if ("起伏".equals(name))
			return "QF";
		else if ("脱节".equals(name))
			return "TJ";
		else if ("接口材料脱落".equals(name))
			return "TL";
		else if ("支管暗接".equals(name))
			return "AJ";
		else if ("异物穿入".equals(name))
			return "CR";
		else if ("渗漏".equals(name))
			return "SL";
		else if ("沉积".equals(name))
			return "CJ";
		else if ("结垢".equals(name))
			return "JG";
		else if ("障碍物".equals(name))
			return "ZW";
		else if ("惨墙、坝根".equals(name))
			return "CQ";
		else if ("树根".equals(name))
			return "SG";
		else if ("浮渣".equals(name))
			return "FZ";
		return "";
	}
}
