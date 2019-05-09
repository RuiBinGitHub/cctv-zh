package com.springboot.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.springboot.biz.CodeBiz;
import com.springboot.biz.DeviceBiz;
import com.springboot.biz.ItemBiz;
import com.springboot.biz.ItemInfoABiz;
import com.springboot.biz.ItemInfoBBiz;
import com.springboot.biz.ItemInfoCBiz;
import com.springboot.biz.PersonBiz;
import com.springboot.biz.PipeBiz;
import com.springboot.entity.Code;
import com.springboot.entity.Device;
import com.springboot.entity.Item;
import com.springboot.entity.ItemInfoA;
import com.springboot.entity.ItemInfoB;
import com.springboot.entity.ItemInfoC;
import com.springboot.entity.Person;
import com.springboot.entity.Pipe;
import com.springboot.entity.Project;

@Component
public class AppInitPDF {

	@Value(value = "${mypath}")
	private String Imgpath;
	
	@Resource
	private DeviceBiz deviceBiz;
	@Resource
	private PersonBiz personBiz;
	@Resource
	private Computes computes;
	@Resource
	private PipeBiz pipeBiz;
	@Resource
	private ItemBiz itemBiz;
	@Resource
	private CodeBiz codeBiz;
	@Resource
	private ItemInfoABiz itemInfoABiz;
	@Resource
	private ItemInfoBBiz itemInfoBBiz;
	@Resource
	private ItemInfoCBiz itemInfoCBiz;

	private Document document = null;
	private Map<String, Object> map = null;

	public static void main(String[] args) {
		AppInitPDF appInitPDF = new AppInitPDF();
		appInitPDF.initPDF(null, "d:/");
	}

	public void initPDF(Project project, String path) {
		try {
			/*****************************************************************/
			document = new Document(PageSize.A4);
			document.setMargins(20, 20, 15, 10);
			String FileName = path + "\\" + project.getName();
			OutputStream output = new FileOutputStream(FileName + ".PDF");
			PdfWriter writer = PdfWriter.getInstance(document, output);
			MyPageEvent header = new MyPageEvent();
			writer.setPageEvent(header);
			document.open(); // 打开文档
			/*****************************************************************/
			Font font = getFont(24, 1, null);
			Paragraph paragraph1 = getParagraph("污  水  管  道", font, 1);
			Paragraph paragraph2 = getParagraph("电视检测与评估报告", font, 1);
			font = getFont(16, 0, null);
			Paragraph paragraph3 = getParagraph("深圳市麦斯迪埃高科技有限责任公司", font, 1);
			Paragraph paragraph4 = getParagraph("2013年07月12日", font, 1);
			int[] border1 = new int[] { 0, 0, 0, 0 };
			int[] border2 = new int[] { 1, 1, 1, 1 };
			int[] border3 = new int[] { 1, 0, 0, 0 };
			int[] border4 = new int[] { 0, 0, 1, 0 };
			int[] border5 = new int[] { 0, 1, 0, 1 };
			// 项目信息表格1
			int[] widths = new int[] { 5, 12 };
			PdfPTable table1 = getTable(2, 340, widths);
			input(table1, "报告编号：", font, 36, 2, 1, 1, border1);
			input(table1, project.getNo(), font, 36, 0, 1, 1, border4);
			input(table1, "报告编号：", font, 36, 2, 1, 1, border1);
			input(table1, project.getName(), font, 36, 0, 1, 1, border4);
			input(table1, "任务地点：", font, 36, 2, 1, 1, border1);
			input(table1, project.getSite(), font, 36, 0, 1, 1, border4);
			input(table1, "委托单位：", font, 36, 2, 1, 1, border1);
			input(table1, project.getClient(), font, 36, 0, 1, 1, border4);
			input(table1, "报告页数：", font, 36, 2, 1, 1, border1);
			input(table1, "共 X 页(含此页)", font, 36, 0, 1, 1, border4);
			// 项目信息表格2
			PdfPTable table2 = getTable(2, 340, widths);
			input(table2, "现场操作员：", font, 36, 2, 1, 1, border1);
			input(table2, project.getPerson(), font, 36, 0, 1, 1, border4);
			input(table2, "报告编写人：", font, 36, 2, 1, 1, border1);
			input(table2, project.getWriter(), font, 36, 0, 1, 1, border4);
			input(table2, "报告校核人：", font, 36, 2, 1, 1, border1);
			input(table2, project.getChecker(), font, 36, 0, 1, 1, border4);
			input(table2, "报告批准人：", font, 36, 2, 1, 1, border1);
			input(table2, project.getApprover(), font, 36, 0, 1, 1, border4);
			/*****************************************************************/
			document.newPage();
			appendWrap(document, 90);
			document.add(paragraph1);
			appendWrap(document, 20);
			document.add(paragraph2);
			appendWrap(document, 160);
			document.add(table1);
			appendWrap(document, 200);
			document.add(paragraph3);
			document.add(paragraph4);
			document.newPage();
			appendWrap(document, 90);
			document.add(paragraph1);
			appendWrap(document, 20);
			document.add(paragraph2);
			appendWrap(document, 180);
			document.add(table2);
			appendWrap(document, 215);
			document.add(paragraph3);
			document.add(paragraph4);
			/*****************************************************************/
			map = AppUtils.getMap("project", project);
			List<Pipe> pipes = pipeBiz.findListPipe(map);
			ItemInfoA itemInfoA = itemInfoABiz.findInfoItemInfoA(map);
			widths = new int[] { 1 };
			font = getFont(14, 0, null);
			PdfPTable tableC1 = getTable(1, 540, widths);
			inputValue(tableC1, "工程名称：" + project.getName(), font, 25, 1, 1, border4);
			inputValue(tableC1, "(1)、待检测管道相关单位", font, 25, 1, 1, border5);
			inputValue(tableC1, "管理单位：" + itemInfoA.getUnit1(), font, 25, 1, 1, border5);
			inputValue(tableC1, "施工单位：" + itemInfoA.getUnit2(), font, 25, 1, 1, border5);
			inputValue(tableC1, "设计单位：" + itemInfoA.getUnit3(), font, 25, 1, 1, border5);
			inputValue(tableC1, "监理单位：" + itemInfoA.getUnit4(), font, 25, 1, 1, border5);
			inputValue(tableC1, "质量监督站：" + itemInfoA.getUnit5(), font, 25, 1, 1, border5);
			inputValue(tableC1, "(2)、检测范围：" + itemInfoA.getExtents(), font, 25, 1, 1, border5);
			inputValue(tableC1, "(3)、检测目的：" + itemInfoA.getPurpose(), font, 25, 1, 1, border5);
			String dates = itemInfoA.getDate1() + "～" + itemInfoA.getDate2();
			inputValue(tableC1, "(4)、检测时间：" + dates, font, 25, 1, 1, border5);
			inputValue(tableC1, "(5)、待检测管道的状况：", font, 25, 1, 1, border5);
			nestTable(tableC1, getTableC_1(pipes), border5, 0, 0);
			inputValue(tableC1, "(6)、现场交通条件：检测地点位于" + itemInfoA.getConditio(), font, 25, 1, 1, border5);
			inputValue(tableC1, "(7)、相关历史资料：根据钻探揭露，埋设管道的岩土层主要为" + itemInfoA.getMaterial(), font, 25, 1, 1, border5);
			inputValue(tableC1, "(8)、检测依据：" + getStandard(itemInfoA.getStandard()), font, 25, 1, 1, border5);
			inputValue(tableC1, "", font, (int) (720 - tableC1.getTotalHeight()), 1, 1, border5);
			inputValue(tableC1, "检测单位：深圳市麦斯迪埃高科技有限责任公司", font, 25, 1, 1, border3);
			document.newPage();
			document.add(getParagraph("C.1 检测任务概况", getFont(20, 1, null), 1));
			appendWrap(document, 15);
			document.add(tableC1);
			/*****************************************************************/
			map = AppUtils.getMap("project", project);
			ItemInfoB itemInfoB = itemInfoBBiz.findInfoItemInfoB(map);
			PdfPTable tableC2 = getTable(1, 540, widths);
			inputValue(tableC2, "工程名称：" + project.getName(), font, 25, 1, 1, border4);
			inputValue(tableC2, "(1)、本项目投入的仪器设备：", font, 25, 1, 1, border5);
			nestTable(tableC2, getTableC_2(itemInfoB), border5, 0, 0);
			inputValue(tableC2, "(2)、本项目组成员：", font, 25, 1, 1, border5);
			nestTable(tableC2, getTableC_3(itemInfoB), border5, 0, 0);
			inputValue(tableC2, "", font, (int) (720 - tableC2.getTotalHeight()), 1, 1, border5);
			inputValue(tableC2, "检测单位：深圳市麦斯迪埃高科技有限责任公司", font, 25, 1, 1, border3);
			document.newPage();
			document.add(getParagraph("C.2 投入检测的设备和人员", getFont(20, 1, null), 1));
			appendWrap(document, 15);
			document.add(tableC2);
			/*****************************************************************/
			map = AppUtils.getMap("project", project);
			ItemInfoC itemInfoC = itemInfoCBiz.findInfoItemInfoC(map);
			PdfPTable tableC3 = getTable(1, 540, widths);
			inputValue(tableC3, "工程名称：" + project.getName(), font, 25, 1, 1, border4);
			inputValue(tableC3, "(1)、工作进程：", font, 25, 1, 1, border5);
			inputValue(tableC3, " ①、现场踏勘日期：" + itemInfoC.getInspection(), font, 25, 1, 1, border5);
			dates = itemInfoC.getWorkdate1() + "～" + itemInfoC.getWorkdate2();
			inputValue(tableC3, " ②、进场工作日期：" + dates, font, 25, 1, 1, border5);
			inputValue(tableC3, " ③、资料整理日期：" + itemInfoC.getCollect(), font, 25, 1, 1, border5);
			inputValue(tableC3, " ④、报告编写日期：" + itemInfoC.getCompile(), font, 25, 1, 1, border5);
			dates = itemInfoC.getInspection() + "～" + itemInfoC.getCompile();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = format.parse(itemInfoC.getInspection());
			Date date2 = format.parse(itemInfoC.getCompile());
			int day = (int) ((date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000));
			inputValue(tableC3, " ⑤、总工程期：" + dates + "，合计" + day + "天", font, 25, 1, 1, border5);
			inputValue(tableC3, "(2)、工作量及检测方法：", font, 25, 1, 1, border5);
			nestTable(tableC3, getTableC_4(pipes), border5, 0, 0);
			inputValue(tableC3, "", font, (int) (720 - tableC3.getTotalHeight()), 1, 1, border5);
			inputValue(tableC3, "检测单位：深圳市麦斯迪埃高科技有限责任公司", font, 25, 1, 1, border3);
			document.newPage();
			document.add(getParagraph("C.3 工作进程及完成工作量", getFont(20, 1, null), 1));
			appendWrap(document, 15);
			document.add(tableC3);
			/*****************************************************************/
			widths = new int[] { 150, 150, 50, 150, 150 };
			PdfPTable table4 = getTable(5, 540, widths);
			DecimalFormat foramt1 = new DecimalFormat("#0.0");
			DecimalFormat foramt2 = new DecimalFormat("#0.00");
			int count[] = new int[8], grade[] = new int[8];
			double length = 0, total[] = new double[8];
			for (int i = 0; pipes != null && i < pipes.size(); i++) {
				Pipe pipe = computes.computePipe(project, pipes.get(i));
				length += getValue(pipe.getPipelength());
				if (pipe.getValue()[3] <= 1) {
					total[0] += getValue(pipe.getPipelength());
					count[0]++;
				} else if (1 < pipe.getValue()[3] && pipe.getValue()[3] <= 3) {
					total[1] += getValue(pipe.getPipelength());
					count[1]++;
				} else if (3 < pipe.getValue()[3] && pipe.getValue()[3] <= 6) {
					total[2] += getValue(pipe.getPipelength());
					count[2]++;
				} else if (pipe.getValue()[3] > 6) {
					total[3] += getValue(pipe.getPipelength());
					count[3]++;
				}
				if (pipe.getValue()[8] <= 1) {
					total[4] += getValue(pipe.getPipelength());
					count[4]++;
				} else if (1 < pipe.getValue()[8] && pipe.getValue()[8] <= 3) {
					total[5] += getValue(pipe.getPipelength());
					count[5]++;
				} else if (3 < pipe.getValue()[8] && pipe.getValue()[8] <= 6) {
					total[6] += getValue(pipe.getPipelength());
					count[6]++;
				} else if (pipe.getValue()[8] > 6) {
					total[7] += getValue(pipe.getPipelength());
					count[7]++;
				}
				if (pipe.getRI() <= 1)
					grade[0]++;
				else if (1 < pipe.getRI() && pipe.getRI() <= 4)
					grade[1]++;
				else if (4 < pipe.getRI() && pipe.getRI() <= 7)
					grade[2]++;
				else if (pipe.getRI() > 7)
					grade[3]++;
				if (pipe.getMI() <= 1)
					grade[4]++;
				else if (1 < pipe.getMI() && pipe.getMI() <= 4)
					grade[5]++;
				else if (4 < pipe.getMI() && pipe.getMI() <= 7)
					grade[6]++;
				else if (pipe.getMI() > 7)
					grade[7]++;
			}
			String text1 = ""; // 结构性总体状况
			String text2 = ""; // 修复建议
			String text3 = ""; // 功能性总体状况
			String text4 = ""; // 养护建议
			// 状况
			if (count[2] != 0)
				text1 += count[2] + "个管段缺陷等级为Ⅲ，管道缺陷严重。";
			if (count[3] != 0)
				text1 += count[3] + "个管段缺陷等级为Ⅳ，管道存在重大缺陷，损坏严重。";
			if (count[6] != 0)
				text2 += count[6] + "个管段缺陷等级为Ⅲ，管道过流受阻比较严重，运行受到明显影响";
			if (count[6] != 0)
				text2 += count[6] + "个管段缺陷等级为Ⅳ，管道过流受阻非常严重，已经导致运行瘫痪。";
			// 建议
			if (grade[2] != 0)
				text3 += grade[2] + "个管段修复等级为Ⅲ，结构在短期内可能发生破坏，应尽快修复。";
			if (grade[3] != 0)
				text3 += grade[3] + "个管段修复等级为Ⅳ，结构已经发生或将发生破损，硬立即修复。";
			if (grade[6] != 0)
				text4 += grade[6] + "个管段修复等级为Ⅲ，根据基础数据进行全面考虑，应尽快处理。";
			if (grade[7] != 0)
				text4 += grade[7] + "个管段修复等级为Ⅳ，输水功能受到严重影响，应立即进行修复。";

			inputValue(table4, "工程名称：排水管道示范性工程", font, 25, 5, 1, border4);
			font = getFont(11, 0, null);
			input(table4, "项目", font, 25, 1, 1, 1, border2);
			input(table4, "检测结论", font, 0, 1, 3, 1, border2);
			input(table4, "备注", font, 0, 1, 1, 1, border2);
			input(table4, "管道长度(m)", font, 25, 1, 1, 1, border2);
			input(table4, foramt1.format(length), font, 0, 1, 3, 1, border2);
			input(table4, "", font, 0, 1, 1, 1, border2);
			input(table4, "管道数量(段)", font, 25, 1, 1, 1, border2);
			input(table4, pipes.size() + "", font, 0, 1, 3, 1, border2);
			input(table4, "", font, 0, 1, 1, 1, border2);
			/** 管道结构性 */
			input(table4, "管道结构性状况", font, 0, 1, 1, 7, border2);
			input(table4, "管段的缺陷等级", font, 25, 1, 1, 1, border2);
			input(table4, "个数", font, 0, 1, 1, 1, border2);
			input(table4, "管道累计长度(m)", font, 0, 1, 1, 1, border2);
			input(table4, "占检测总长度(%)", font, 0, 1, 1, 1, border2);

			input(table4, "Ⅰ", font, 25, 1, 1, 1, border2);
			input(table4, count[0] + "", font, 0, 1, 1, 1, border2);
			input(table4, foramt1.format(total[0]), font, 0, 1, 1, 1, border2);
			input(table4, foramt2.format(total[0] / length * 100), font, 0, 1, 1, 1, border2);
			input(table4, "Ⅱ", font, 25, 1, 1, 1, border2);
			input(table4, count[1] + "", font, 0, 1, 1, 1, border2);
			input(table4, foramt1.format(total[1]), font, 0, 1, 1, 1, border2);
			input(table4, foramt2.format(total[1] / length * 100), font, 0, 1, 1, 1, border2);
			input(table4, "Ⅲ", font, 25, 1, 1, 1, border2);
			input(table4, count[2] + "", font, 0, 1, 1, 1, border2);
			input(table4, foramt1.format(total[2]), font, 0, 1, 1, 1, border2);
			input(table4, foramt2.format(total[2] / length * 100), font, 0, 1, 1, 1, border2);
			input(table4, "Ⅳ", font, 25, 1, 1, 1, border2);
			input(table4, count[3] + "", font, 0, 1, 1, 1, border2);
			input(table4, foramt1.format(total[3]), font, 0, 1, 1, 1, border2);
			input(table4, foramt2.format(total[3] / length * 100), font, 0, 1, 1, 1, border2);
			input(table4, "管段总体结构性状况", font, 50, 1, 1, 1, border2);
			input(table4, text1, font, 0, 0, 3, 1, border2);
			input(table4, "修复建议", font, 50, 1, 1, 1, border2);
			input(table4, text3, font, 0, 0, 3, 1, border2);
			/** 管道功能性 */
			input(table4, "管道功能性状况", font, 0, 1, 1, 7, border2);
			input(table4, "管段的缺陷等级", font, 25, 1, 1, 1, border2);
			input(table4, "个数", font, 0, 1, 1, 1, border2);
			input(table4, "管道累计长度(m)", font, 0, 1, 1, 1, border2);
			input(table4, "占检测总长度(%)", font, 0, 1, 1, 1, border2);

			input(table4, "Ⅰ", font, 25, 1, 1, 1, border2);
			input(table4, count[4] + "", font, 0, 1, 1, 1, border2);
			input(table4, foramt1.format(total[4]), font, 0, 1, 1, 1, border2);
			input(table4, foramt2.format(total[4] / length), font, 0, 1, 1, 1, border2);
			input(table4, "Ⅱ", font, 25, 1, 1, 1, border2);
			input(table4, count[5] + "", font, 0, 1, 1, 1, border2);
			input(table4, foramt1.format(total[5]), font, 0, 1, 1, 1, border2);
			input(table4, foramt2.format(total[5] / length), font, 0, 1, 1, 1, border2);
			input(table4, "Ⅲ", font, 25, 1, 1, 1, border2);
			input(table4, count[6] + "", font, 0, 1, 1, 1, border2);
			input(table4, foramt1.format(total[6]), font, 0, 1, 1, 1, border2);
			input(table4, foramt2.format(total[6] / length), font, 0, 1, 1, 1, border2);
			input(table4, "Ⅳ", font, 25, 1, 1, 1, border2);
			input(table4, count[7] + "", font, 0, 1, 1, 1, border2);
			input(table4, foramt1.format(total[7]), font, 0, 1, 1, 1, border2);
			input(table4, foramt2.format(total[7] / length), font, 0, 1, 1, 1, border2);
			input(table4, "管段总体功能性状况", font, 50, 1, 1, 1, border2);
			input(table4, text2, font, 0, 0, 3, 1, border2);
			input(table4, "养护建议", font, 50, 1, 1, 1, border2);
			input(table4, text4, font, 0, 0, 3, 1, border2);
			input(table4, "其他", font, 25, 1, 1, 1, border2);
			input(table4, "", font, 0, 1, 4, 1, border2);
			document.newPage();
			document.add(getParagraph("C.4 检测结论", getFont(20, 1, null), 1));
			appendWrap(document, 15);
			document.add(table4);
			/*****************************************************************/
			font = getFont(10, 0, null);
			widths = new int[] { 60, 50, 50, 80, 50, 50, 40, 40, 40, 40, 40, 135, 40, 40, 40, 40, 40, 135 };
			PdfPTable table5 = getTable(18, 780, widths);
			for (int i = 0; pipes != null && i < pipes.size(); i++) {
				if (i == 0) {
					document.newPage();
					input(table5, "C.5 检测成果表", getFont(20, 1, null), 30, 1, 18, 1, border1);
					input(table5, "C.5.1 管段状况评估表", getFont(14, 1, null), 25, 0, 18, 1, border1);
					inputValue(table5, "工程名称：排水管道示范性工程", getFont(14, 0, null), 25, 18, 1, border1);
					input(table5, "管段", font, 0, 1, 1, 2, border2);
					input(table5, "管径(mm)", font, 0, 1, 1, 2, border2);
					input(table5, "长度(m)", font, 0, 1, 1, 2, border2);
					input(table5, "材质", font, 0, 1, 1, 2, border2);
					input(table5, "埋深(m)", font, 20, 1, 2, 1, border2);
					input(table5, "结构性缺陷", font, 0, 1, 6, 1, border2);
					input(table5, "功能性缺陷", font, 0, 1, 6, 1, border2);
					input(table5, "起点", font, 30, 1, 1, 1, border2);
					input(table5, "终点", font, 0, 1, 1, 1, border2);
					input(table5, "平均值", font, 0, 1, 1, 1, border2);
					input(table5, "最大值", font, 0, 1, 1, 1, border2);
					input(table5, "缺陷\n等级", font, 0, 1, 1, 1, border2);
					input(table5, "缺陷\n密度", font, 0, 1, 1, 1, border2);
					input(table5, "修复\n指数", font, 0, 1, 1, 1, border2);
					input(table5, "综合状况", font, 0, 1, 1, 1, border2);
					input(table5, "平均值", font, 0, 1, 1, 1, border2);
					input(table5, "最大值", font, 0, 1, 1, 1, border2);
					input(table5, "缺陷\n等级", font, 0, 1, 1, 1, border2);
					input(table5, "缺陷\n密度", font, 0, 1, 1, 1, border2);
					input(table5, "修复\n指数", font, 0, 1, 1, 1, border2);
					input(table5, "综合状况", font, 0, 1, 1, 1, border2);
				} else if ((i + 1) % 11 == 0) {
					PdfPTable table6 = getTable(1, 540, new int[] { 1 });
					nestTable(table6, table5, new int[] { 0, 0, 0, 0 }, 0, 90);
					document.add(table6);
					/*********************************************************/
					table5 = getTable(18, 780, widths);
					input(table5, "", font, 40, 1, 18, 1, border1); // 添加空行
					input(table5, "管段", font, 0, 1, 1, 2, border2);
					input(table5, "管径(mm)", font, 0, 1, 1, 2, border2);
					input(table5, "长度(m)", font, 0, 1, 1, 2, border2);
					input(table5, "材质", font, 0, 1, 1, 2, border2);
					input(table5, "埋深(m)", font, 20, 1, 2, 1, border2);
					input(table5, "结构性缺陷", font, 0, 1, 6, 1, border2);
					input(table5, "功能性缺陷", font, 0, 1, 6, 1, border2);
					input(table5, "起点", font, 30, 1, 1, 1, border2);
					input(table5, "终点", font, 0, 1, 1, 1, border2);
					input(table5, "平均值", font, 0, 1, 1, 1, border2);
					input(table5, "最大值", font, 0, 1, 1, 1, border2);
					input(table5, "缺陷\n等级", font, 0, 1, 1, 1, border2);
					input(table5, "缺陷\n密度", font, 0, 1, 1, 1, border2);
					input(table5, "修复\n指数", font, 0, 1, 1, 1, border2);
					input(table5, "综合状况", font, 0, 1, 1, 1, border2);
					input(table5, "平均值", font, 0, 1, 1, 1, border2);
					input(table5, "最大值", font, 0, 1, 1, 1, border2);
					input(table5, "缺陷\n等级", font, 0, 1, 1, 1, border2);
					input(table5, "缺陷\n密度", font, 0, 1, 1, 1, border2);
					input(table5, "修复\n指数", font, 0, 1, 1, 1, border2);
					input(table5, "综合状况", font, 0, 1, 1, 1, border2);
				}
				Pipe pipe = pipes.get(i);
				String context1 = "", context2 = "", context3 = "", context4 = "";
				// 结构性缺陷等级
				if (pipe.getValue()[3] >= 1) {
					context1 = "Ⅰ";
					context3 = "无明显缺陷；";
				} else if (1 < pipe.getValue()[3] && pipe.getValue()[3] <= 3) {
					context1 = "Ⅱ";
					context3 = "管段缺陷明显超过一级，有变坏的趋势；";
				} else if (3 < pipe.getValue()[3] && pipe.getValue()[3] <= 6) {
					context1 = "Ⅲ";
					context3 = "管段缺陷严重，结构状况受到影响；";
				} else if (pipe.getValue()[3] > 6) {
					context1 = "Ⅳ";
					context3 = "管段存在重大缺陷，损坏严重或即将导致破坏；";
				}
				// 功能性缺陷等级
				if (pipe.getValue()[8] >= 1) {
					context2 = "Ⅰ";
					context4 = "无明显缺陷；";
				} else if (1 < pipe.getValue()[8] && pipe.getValue()[8] <= 3) {
					context2 = "Ⅱ";
					context4 = "管段过流有一定的受阻，运行影响不大；";
				} else if (3 < pipe.getValue()[8] && pipe.getValue()[8] <= 6) {
					context2 = "Ⅲ";
					context4 = "管段过流受阻比较严重，运行受到严重影响；";
				} else if (pipe.getValue()[8] > 6) {
					context2 = "Ⅳ";
					context4 = "管段过流受阻非常严重，即将或已经导致运行瘫痪；";
				}
				// 结构性缺陷密度
				if (pipe.getValue()[4] < 0.1)
					context3 += "局部缺陷";
				else if (0.1 <= pipe.getValue()[4] && pipe.getValue()[4] <= 0.5)
					context3 += "部分或整体缺陷";
				else
					context3 += "整体缺陷";
				// 功能性缺陷密度
				if (pipe.getValue()[9] < 0.1)
					context4 += "局部缺陷";
				else if (0.1 <= pipe.getValue()[9] && pipe.getValue()[9] <= 0.5)
					context4 += "部分或整体缺陷";
				else
					context4 += "整体缺陷";

				input(table5, pipe.getSmanhole() + "～\n" + pipe.getFmanhole(), font, 40, 1, 1, 1, border2);
				input(table5, pipe.getDiameter(), font, 0, 1, 1, 1, border2);
				input(table5, pipe.getPipelength(), font, 0, 1, 1, 1, border2);
				input(table5, pipe.getMaterial(), font, 0, 1, 1, 1, border2);
				input(table5, pipe.getSdepth(), font, 0, 1, 1, 1, border2);
				input(table5, pipe.getFdepth(), font, 0, 1, 1, 1, border2);
				input(table5, foramt2.format(pipe.getValue()[1]), font, 0, 1, 1, 1, border2);
				input(table5, foramt2.format(pipe.getValue()[0]), font, 0, 1, 1, 1, border2);
				input(table5, context1, font, 0, 1, 1, 1, border2);
				input(table5, foramt2.format(pipe.getValue()[4]), font, 0, 1, 1, 1, border2);
				input(table5, foramt2.format(pipe.getRI()), font, 0, 1, 1, 1, border2);
				input(table5, context3, font, 0, 1, 1, 1, border2);
				input(table5, foramt2.format(pipe.getValue()[6]), font, 0, 1, 1, 1, border2);
				input(table5, foramt2.format(pipe.getValue()[5]), font, 0, 1, 1, 1, border2);
				input(table5, context2, font, 0, 1, 1, 1, border2);
				input(table5, foramt2.format(pipe.getValue()[9]), font, 0, 1, 1, 1, border2);
				input(table5, foramt2.format(pipe.getMI()), font, 0, 1, 1, 1, border2);
				input(table5, context4, font, 0, 1, 1, 1, border2);
			}
			int height = (int) (540 - table5.getTotalHeight());
			input(table5, "", font, height, 1, 18, 1, border1);
			PdfPTable table6 = getTable(1, 540, new int[] { 1 });
			nestTable(table6, table5, new int[] { 0, 0, 0, 0 }, 0, 90);
			document.add(table6);
			/*****************************************************************/
			font = getFont(10, 1, null);
			widths = new int[] { 40, 90, 50, 80, 80, 80, 180, 60 };
			PdfPTable table7 = getTable(8, 540, widths);
			PdfPTable table8 = getTable(8, 540, widths);
			input(table7, "C.5.2 排水管道缺陷统计表", getFont(14, 1, null), 25, 0, 18, 1, border1);
			/** 道结构性缺陷统计表 */
			input(table7, "排水管道结构性缺陷统计表", getFont(12, 1, null), 25, 1, 18, 1, border1);
			input(table7, "序号", font, 30, 1, 1, 1, border2);
			input(table7, "管道编号", font, 0, 1, 1, 1, border2);
			input(table7, "管径", font, 0, 1, 1, 1, border2);
			input(table7, "材质", font, 0, 1, 1, 1, border2);
			input(table7, "检测长度\n(m)", font, 0, 1, 1, 1, border2);
			input(table7, "缺陷距离\n(m)", font, 0, 1, 1, 1, border2);
			input(table7, "缺陷名称及位置", font, 0, 1, 1, 1, border2);
			input(table7, "缺陷等级", font, 0, 1, 1, 1, border2);
			/** 功能性缺陷统计表 */
			input(table8, "排水管道功能性缺陷统计表", getFont(12, 1, null), 25, 1, 18, 1, border1);
			input(table8, "序号", font, 30, 1, 1, 1, border2);
			input(table8, "管道编号", font, 0, 1, 1, 1, border2);
			input(table8, "管径", font, 0, 1, 1, 1, border2);
			input(table8, "材质", font, 0, 1, 1, 1, border2);
			input(table8, "检测长度\n(m)", font, 0, 1, 1, 1, border2);
			input(table8, "缺陷距离\n(m)", font, 0, 1, 1, 1, border2);
			input(table8, "缺陷名称及位置", font, 0, 1, 1, 1, border2);
			input(table8, "缺陷等级", font, 0, 1, 1, 1, border2);
			font = getFont(10, 0, null);
			for (int i = 0; pipes != null && i < pipes.size(); i++) {
				Pipe pipe = pipes.get(i);
				String context1 = "", context2 = "", context3 = "";
				String context4 = "", context5 = "", context6 = "";
				map = AppUtils.getMap("pipe", pipe);
				List<Item> items = itemBiz.findListItem(map);
				for (int j = 0; items != null && j < items.size(); j++) {
					Item item = items.get(j);
					String deCode = item.getCode();
					if (StringUtils.isEmpty(deCode))
						continue; // 結束本次循环
					deCode = deCode.length() == 4 ? deCode.substring(2, 4) : deCode;
					map = AppUtils.getMap("code", deCode, "grade", item.getGrade());
					Code code = codeBiz.findInfoCode(map);
					if (StringUtils.isEmpty(code))
						continue; // 結束本次循环
					if ("type1".equals(code.getType())) {
						context1 += item.getDist();
						if (item.getCode().indexOf("KS") != -1)
							context2 += "开始" + code.getName();
						else if (item.getCode().indexOf("JS") != -1)
							context2 += "结束" + code.getName();
						else
							context2 += code.getName();
						context3 += code.getGrade();
						if (!StringUtils.isEmpty(item.getLocation()))
							context2 += "，位置：" + item.getLocation();
						context1 += "\n";
						context2 += "\n";
						context3 += "\n";
					} else {
						context4 += item.getDist();
						if (item.getCode().indexOf("KS") != -1)
							context5 += "开始" + code.getName();
						else if (item.getCode().indexOf("JS") != -1)
							context5 += "结束" + code.getName();
						else
							context5 += code.getName();
						context6 += code.getGrade();
						if (!StringUtils.isEmpty(item.getLocation()))
							context5 += "，位置：" + item.getLocation();
						context4 += "\n";
						context5 += "\n";
						context6 += "\n";
					}
				}
				if (context1.length() != 0) {
					input(table7, pipe.getNo() + "", font, 30, 1, 1, 1, border2);
					input(table7, pipe.getSmanhole() + "～" + pipe.getFmanhole(), font, 0, 1, 1, 1, border2);
					input(table7, pipe.getDiameter(), font, 0, 1, 1, 1, border2);
					input(table7, pipe.getMaterial(), font, 0, 1, 1, 1, border2);
					input(table7, pipe.getTestlength(), font, 0, 1, 1, 1, border2);
					input(table7, context1, font, 0, 1, 1, 1, border2);
					input(table7, context2, font, 0, 1, 1, 1, border2);
					input(table7, context3, font, 0, 1, 1, 1, border2);
				}
				if (context4.length() != 0) {
					input(table8, pipe.getNo() + "", font, 30, 1, 1, 1, border2);
					input(table8, pipe.getSmanhole() + "～" + pipe.getFmanhole(), font, 0, 1, 1, 1, border2);
					input(table8, pipe.getDiameter(), font, 0, 1, 1, 1, border2);
					input(table8, pipe.getMaterial(), font, 0, 1, 1, 1, border2);
					input(table8, pipe.getTestlength(), font, 0, 1, 1, 1, border2);
					input(table8, context4, font, 0, 1, 1, 1, border2);
					input(table8, context5, font, 0, 1, 1, 1, border2);
					input(table8, context6, font, 0, 1, 1, 1, border2);
				}
			}
			document.newPage();
			document.add(table7);
			appendWrap(document, 20);
			document.add(table8);
			/*****************************************************************/

			for (int i = 0; pipes != null && i < pipes.size(); i++) {
				Pipe pipe = pipes.get(i);
				document.newPage();
				border1 = new int[] { 0, 0, 0, 0 };
				border2 = new int[] { 1, 1, 1, 1 };
				widths = new int[] { 40, 40, 20, 20, 40, 40, 40 };
				PdfPTable table9 = getTable(7, 540, widths);
				input(table9, "C.5.3 排水管道检测成功表", getFont(14, 1, null), 25, 0, 7, 1, border1);
				input(table9, "排水管道检测成果表 (序号" + pipe.getNo() + ")", getFont(12, 1, null), 25, 1, 7, 1, border1);
				inputValue(table9, "序号：" + pipe.getNo(), font, 25, 7, 1, border1);

				input(table9, "录像文件", font, 30, 1, 1, 1, border2);
				input(table9, pipe.getVideo(), font, 0, 1, 1, 1, border2);
				input(table9, "起始井号", font, 0, 1, 2, 1, border2);
				input(table9, pipe.getSmanhole(), font, 0, 1, 1, 1, border2);
				input(table9, "终止井号", font, 0, 1, 1, 1, border2);
				input(table9, pipe.getFmanhole(), font, 0, 1, 1, 1, border2);

				input(table9, "敷设年代", font, 30, 1, 1, 1, border2);
				input(table9, pipe.getLaidyear(), font, 0, 1, 1, 1, border2);
				input(table9, "起点埋深", font, 0, 1, 2, 1, border2);
				input(table9, pipe.getSdepth(), font, 0, 1, 1, 1, border2);
				input(table9, "终点埋深", font, 0, 1, 1, 1, border2);
				input(table9, pipe.getFdepth(), font, 0, 1, 1, 1, border2);

				input(table9, "管道类型", font, 30, 1, 1, 1, border2);
				input(table9, pipe.getPipetype(), font, 0, 1, 1, 1, border2);
				input(table9, "管道材质", font, 0, 1, 2, 1, border2);
				input(table9, pipe.getMaterial(), font, 0, 1, 1, 1, border2);
				input(table9, "管段直径", font, 0, 1, 1, 1, border2);
				input(table9, pipe.getDiameter(), font, 0, 1, 1, 1, border2);

				input(table9, "检测方向", font, 30, 1, 1, 1, border2);
				input(table9, pipe.getDirection(), font, 0, 1, 1, 1, border2);
				input(table9, "管段长度", font, 0, 1, 2, 1, border2);
				input(table9, pipe.getPipelength(), font, 0, 1, 1, 1, border2);
				input(table9, "检测长度", font, 0, 1, 1, 1, border2);
				input(table9, pipe.getTestlength(), font, 0, 1, 1, 1, border2);

				input(table9, "修复指数", font, 30, 1, 1, 1, border2);
				input(table9, pipe.getRI() + "", font, 0, 1, 1, 1, border2);
				input(table9, "养护指数", font, 0, 1, 2, 1, border2);
				input(table9, pipe.getMI() + "", font, 0, 1, 1, 1, border2);
				input(table9, "检测方法", font, 0, 1, 1, 1, border2);
				input(table9, pipe.getMethod(), font, 0, 1, 1, 1, border2);

				input(table9, "检测地点", font, 30, 1, 0, 1, border2);
				input(table9, pipe.getSite(), font, 0, 1, 4, 1, border2);
				input(table9, "检测日期", font, 0, 1, 1, 1, border2);
				input(table9, pipe.getDate(), font, 0, 1, 1, 1, border2);

				input(table9, "距离 (m)", font, 30, 1, 1, 1, border2);
				input(table9, "缺陷代码", font, 0, 1, 1, 1, border2);
				input(table9, "分值", font, 0, 1, 1, 1, border2);
				input(table9, "等级", font, 0, 1, 1, 1, border2);
				input(table9, "管道内部状况描述", font, 0, 1, 2, 1, border2);
				input(table9, "图片序号", font, 0, 1, 1, 1, border2);

				List<Item> items = pipe.getItems();
				List<Item> ilist = new ArrayList<Item>();
				// widths = new int[] { 40, 20, 20, 20, 100, 40 };
				// PdfPTable table10 = getTable(7, 540, widths);
				for (int j = 0; items != null && j < items.size(); j++) {
					Item item = items.get(j);
					input(table9, item.getDist(), font, 30, 1, 1, 1, border2);
					input(table9, item.getCode(), font, 0, 1, 1, 1, border2);
					input(table9, "", font, 0, 1, 1, 1, border2);
					input(table9, item.getGrade(), font, 0, 1, 1, 1, border2);
					input(table9, item.getRemarks(), font, 0, 1, 2, 1, border2);
					input(table9, item.getPicture(), font, 0, 1, 1, 1, border2);
					if (!StringUtils.isEmpty(item.getPath()))
						ilist.add(item);
				}
				for (int k = 0; k < 8 - items.size(); k++) {
					input(table9, "", font, 30, 1, 1, 1, border2);
					input(table9, "", font, 0, 1, 1, 1, border2);
					input(table9, "", font, 0, 1, 1, 1, border2);
					input(table9, "", font, 0, 1, 1, 1, border2);
					input(table9, "", font, 0, 1, 2, 1, border2);
					input(table9, "", font, 0, 1, 1, 1, border2);
				}
				input(table9, "备注", font, 30, 1, 1, 1, border2);
				input(table9, pipe.getRemarks(), font, 0, 1, 6, 1, border2);

				if (ilist.size() >= 1){
					table9.addCell(getImageCell(ilist.get(0).getPath()));
				}
				else
					input(table9, "", font, 200, 1, 4, 1, border2);
				if (ilist.size() >= 2)
					table9.addCell(getImageCell(ilist.get(1).getPath()));
				else
					input(table9, "", font, 200, 1, 4, 1, border2);
				if (ilist.size() >= 1)
					input(table9, "图片1：" + ilist.get(0).getRemarks(), font, 30, 1, 4, 1, border2);
				if (ilist.size() >= 2)
					input(table9, "图片1：" + ilist.get(1).getRemarks(), font, 30, 1, 4, 1, border2);
				document.add(table9);
			}
			/*****************************************************************/
			/****************************** END ******************************/
			/*****************************************************************/
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Font getFont(int size, int bold, BaseColor color) {
		try {
			BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", false);
			Font font = new Font(baseFont, size, bold);
			font.setFamily("Times New Roman");
			if (color != null)
				font.setColor(color);
			return font;
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/** 创建表格 */
	private PdfPTable getTable(int colspan, float width, int[] widths) {
		try {
			PdfPTable table = new PdfPTable(colspan);
			table.setTotalWidth(width);
			table.setWidths(widths);
			table.setLockedWidth(true);
			return table;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Paragraph getParagraph(String text, Font font, int algin) {
		Paragraph paragraph = new Paragraph(text, font);
		paragraph.setAlignment(algin);
		return paragraph;
	}

	private PdfPCell getImageCell(String path) {
		try {
			System.out.println(Imgpath + path + ".png");
			Image image = Image.getInstance(Imgpath + path + ".png");
			image.scaleAbsolute(240, 180);
			PdfPCell pdfPCell = new PdfPCell(image);
			pdfPCell.setColspan(4);
			pdfPCell.setFixedHeight(200);
			pdfPCell.setUseAscender(true);
			pdfPCell.setHorizontalAlignment(1);
			pdfPCell.setVerticalAlignment(5);
			return pdfPCell;
		} catch (Exception e) {
			return null;
		}
	}

	private void input(PdfPTable table, String text, Font font, int height, int algin, int col, int row, int border[]) {
		text = text == null ? "" : text;
		Paragraph paragraph = new Paragraph(text, font);
		PdfPCell cell = new PdfPCell(paragraph);
		cell.setHorizontalAlignment(algin);
		cell.setVerticalAlignment(5);
		cell.setMinimumHeight(height);
		cell.setUseAscender(true);
		cell.setColspan(col);
		cell.setRowspan(row);
		if (border[0] == 0)
			cell.setBorderWidthTop(0);
		if (border[1] == 0)
			cell.setBorderWidthRight(0);
		if (border[2] == 0)
			cell.setBorderWidthBottom(0);
		if (border[3] == 0)
			cell.setBorderWidthLeft(0);
		table.addCell(cell);
	}

	/** 表格添加文本 */
	private void inputValue(PdfPTable table, String text, Font font, int height, int col, int row, int border[]) {
		text = text == null ? "" : text;
		Paragraph paragraph = new Paragraph(text, font);
		PdfPCell cell = new PdfPCell(paragraph);
		cell.setIndent(15);
		cell.setColspan(col);
		cell.setRowspan(row);
		cell.setMinimumHeight(height);
		cell.setVerticalAlignment(5);
		cell.setUseAscender(true);
		if (border[0] == 0)
			cell.setBorderWidthTop(0);
		if (border[1] == 0)
			cell.setBorderWidthRight(0);
		if (border[2] == 0)
			cell.setBorderWidthBottom(0);
		if (border[3] == 0)
			cell.setBorderWidthLeft(0);
		table.addCell(cell);
	}

	private void nestTable(PdfPTable table1, PdfPTable table2, int[] border, int height, int rotation) {
		PdfPCell cell = new PdfPCell(table2);
		cell.setHorizontalAlignment(1);
		cell.setRotation(rotation);
		if (border[0] == 0)
			cell.setBorderWidthTop(0);
		if (border[1] == 0)
			cell.setBorderWidthRight(0);
		if (border[2] == 0)
			cell.setBorderWidthBottom(0);
		if (border[3] == 0)
			cell.setBorderWidthLeft(0);
		if (height != 0)
			cell.setMinimumHeight(height);
		table1.addCell(cell);
	}

	private PdfPTable getTableC_1(List<Pipe> pipes) {
		Font font1 = getFont(11, 1, null);
		Font font2 = getFont(11, 0, null);
		int[] border1 = new int[] { 0, 0, 0, 0 };
		int[] border2 = new int[] { 1, 1, 1, 1 };
		int[] widths = new int[] { 100, 105, 80, 125, 100, 75 };
		PdfPTable table = getTable(6, 540, widths);
		// 统计管道数据
		double length = 0;
		HashSet<String> hashSet1 = new HashSet<String>();
		HashSet<String> hashSet2 = new HashSet<String>();
		for (int i = 0; pipes != null && i < pipes.size(); i++) {
			length += getValue(pipes.get(i).getTestlength());
			hashSet1.add(pipes.get(i).getDiameter());
			hashSet2.add(pipes.get(i).getMaterial());
		}
		String text1 = hashSet1.toString();
		String text2 = hashSet2.toString();
		text1 = text1.substring(1, text1.length() - 1);
		text2 = text2.substring(1, text2.length() - 1);
		// 表格标题
		input(table, "待检测管道概况", getFont(12, 1, null), 30, 1, 6, 1, border1);
		input(table, "检测数量/长度", font1, 40, 1, 1, 1, border2);
		input(table, "管材直径 (mm)", font1, 0, 1, 1, 1, border2);
		input(table, "管道材质", font1, 0, 1, 1, 1, border2);
		input(table, "接口形式", font1, 0, 1, 1, 1, border2);
		input(table, "管道敷设年代", font1, 0, 1, 1, 1, border2);
		input(table, "备注", font1, 0, 1, 1, 1, border2);
		// 表格数据
		input(table, pipes.size() + "段/" + length + "m", font2, 120, 1, 1, 1, border2);
		input(table, text1.replace(", ", "\n"), font2, 0, 1, 1, 1, border2);
		input(table, text2.replace(", ", "\n"), font2, 0, 1, 1, 1, border2);
		input(table, "", font2, 0, 1, 1, 1, border2);
		input(table, "", font2, 0, 1, 1, 1, border2);
		input(table, "", font2, 0, 1, 1, 1, border2);
		return table;
	}

	private PdfPTable getTableC_2(ItemInfoB itemInfoB) {
		Font font1 = getFont(11, 1, null);
		Font font2 = getFont(11, 0, null);
		int[] border1 = new int[] { 0, 0, 0, 0 };
		int[] border2 = new int[] { 1, 1, 1, 1 };
		int[] widths = new int[] { 50, 125, 75, 150, 125, 150 };
		PdfPTable table = getTable(6, 540, widths);
		List<String> list = null;
		if (StringUtils.isEmpty(itemInfoB.getDevices()))
			list = Arrays.asList(itemInfoB.getDevices().split(","));
		map = AppUtils.getMap("list", list);
		List<Device> devices = deviceBiz.findListDevice(map);
		input(table, "仪器设备表", getFont(12, 1, null), 30, 1, 6, 1, border1);
		input(table, "序号", font1, 30, 1, 1, 1, border2);
		input(table, "设备名称", font1, 0, 1, 1, 1, border2);
		input(table, "设备型号", font1, 0, 1, 1, 1, border2);
		input(table, "生产厂家", font1, 0, 1, 1, 1, border2);
		input(table, "出厂编号", font1, 0, 1, 1, 1, border2);
		input(table, "校准证书编号/有效期", font1, 0, 1, 1, 1, border2);
		for (int i = 0; devices != null && i < devices.size(); i++) {
			Device device = devices.get(i);
			input(table, "" + (i + 1), font2, 30, 1, 1, 1, border2);
			input(table, device.getName(), font2, 0, 1, 1, 1, border2);
			input(table, device.getModel(), font2, 0, 1, 1, 1, border2);
			input(table, device.getFactory(), font2, 0, 1, 1, 1, border2);
			input(table, device.getNumber1(), font2, 0, 1, 1, 1, border2);
			input(table, device.getNumber2() + "\n" + device.getDate(), font2, 0, 1, 1, 1, border2);
		}
		return table;
	}

	private PdfPTable getTableC_3(ItemInfoB itemInfoB) {
		Font font1 = getFont(11, 1, null);
		Font font2 = getFont(11, 0, null);
		int[] border1 = new int[] { 0, 0, 0, 0 };
		int[] border2 = new int[] { 1, 1, 1, 1 };
		int[] widths = new int[] { 50, 125, 125, 125, 125, 100 };
		PdfPTable table = getTable(6, 540, widths);
		List<String> list = null;
		if (StringUtils.isEmpty(itemInfoB.getPersons()))
			list = Arrays.asList(itemInfoB.getPersons().split(","));
		map = AppUtils.getMap("list", list);
		List<Person> persons = personBiz.findListPerson(map);
		input(table, "项目组成员名单", getFont(12, 1, null), 30, 1, 6, 1, border1);
		input(table, "序号", font1, 30, 1, 1, 1, border2);
		input(table, "姓名", font1, 0, 1, 1, 1, border2);
		input(table, "职务", font1, 0, 1, 1, 1, border2);
		input(table, "职称", font1, 0, 1, 1, 1, border2);
		input(table, "证件名称及编号", font1, 0, 1, 1, 1, border2);
		input(table, "备注", font1, 0, 1, 1, 1, border2);
		for (int i = 0; persons != null && i < persons.size(); i++) {
			Person person = persons.get(i);
			input(table, "" + (i + 1), font2, 30, 1, 1, 1, border2);
			input(table, person.getName(), font2, 0, 1, 1, 1, border2);
			input(table, person.getPost(), font2, 0, 1, 1, 1, border2);
			input(table, person.getTitle(), font2, 0, 1, 1, 1, border2);
			input(table, person.getPapers() + "\n" + person.getNumber(), font2, 0, 1, 1, 1, border2);
			input(table, person.getRemark(), font2, 0, 1, 1, 1, border2);
		}
		return table;
	}

	private PdfPTable getTableC_4(List<Pipe> pipes) {
		Font font1 = getFont(11, 1, null);
		Font font2 = getFont(11, 0, null);
		int[] border1 = new int[] { 0, 0, 0, 0 };
		int[] border2 = new int[] { 1, 1, 1, 1 };
		int[] widths = new int[] { 100, 100, 100, 100, 240 };
		PdfPTable table = getTable(5, 540, widths);
		int count1 = 0, count2 = 0, count3 = 0;
		double total1 = 0, total2 = 0, total3 = 0;
		DecimalFormat foramt = new DecimalFormat("#0.0");
		for (int i = 0; pipes != null && i < pipes.size(); i++) {
			if ("污水".equals(pipes.get(i).getPipetype())) {
				total1 += getValue(pipes.get(i).getTestlength());
				count1++;
			} else if ("雨水".equals(pipes.get(i).getPipetype())) {
				total2 += getValue(pipes.get(i).getTestlength());
				count2++;
			} else if ("合流".equals(pipes.get(i).getPipetype())) {
				total3 += getValue(pipes.get(i).getTestlength());
				count3++;
			}
		}
		input(table, "本项目工作量及检测方法", getFont(12, 1, null), 30, 1, 6, 1, border1);
		input(table, "项目内容", font1, 30, 1, 1, 1, border2);
		input(table, "段数", font1, 30, 1, 1, 1, border2);
		input(table, "总长度(m)", font1, 30, 1, 1, 1, border2);
		input(table, "检测方法", font1, 30, 1, 1, 1, border2);
		input(table, "备注", font1, 30, 1, 1, 1, border2);

		input(table, "污水管", font2, 30, 1, 1, 1, border2);
		input(table, count1 == 0 ? "" : count1 + "", font2, 0, 1, 1, 1, border2);
		input(table, total1 == 0 ? "" : foramt.format(total1), font2, 0, 1, 1, 1, border2);
		input(table, count1 == 0 ? "" : "电视检测", font2, 0, 1, 1, 1, border2);
		input(table, "", font2, 0, 1, 1, 1, border2);

		input(table, "雨水管", font2, 30, 1, 1, 1, border2);
		input(table, count2 == 0 ? "" : count2 + "", font2, 0, 1, 1, 1, border2);
		input(table, total2 == 0 ? "" : foramt.format(total2), font2, 0, 1, 1, 1, border2);
		input(table, count2 == 0 ? "" : "电视检测", font2, 0, 1, 1, 1, border2);
		input(table, "", font2, 0, 1, 1, 1, border2);

		input(table, "合流管", font2, 30, 1, 1, 1, border2);
		input(table, count3 == 0 ? "" : count3 + "", font2, 0, 1, 1, 1, border2);
		input(table, total3 == 0 ? "" : foramt.format(total3), font2, 0, 1, 1, 1, border2);
		input(table, count3 == 0 ? "" : "电视检测", font2, 0, 1, 1, 1, border2);
		input(table, "", font2, 0, 1, 1, 1, border2);

		input(table, "检查井", font2, 30, 1, 1, 1, border2);
		input(table, "", font2, 0, 1, 1, 1, border2);
		input(table, "", font2, 0, 1, 1, 1, border2);
		input(table, "", font2, 0, 1, 1, 1, border2);
		input(table, "", font2, 0, 1, 1, 1, border2);
		input(table, "雨水口", font2, 30, 1, 1, 1, border2);
		input(table, "", font2, 0, 1, 1, 1, border2);
		input(table, "", font2, 0, 1, 1, 1, border2);
		input(table, "", font2, 0, 1, 1, 1, border2);
		input(table, "", font2, 0, 1, 1, 1, border2);
		return table;
	}

	/** 文档添加空行 */
	public void appendWrap(Document document, double height) {
		try {
			int[] width = new int[] { 1 };
			PdfPTable table = getTable(1, 560f, width);
			PdfPCell cell = new PdfPCell();
			cell.setFixedHeight((float) height);
			cell.setBorder(0);
			table.addCell(cell);
			document.add(table);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public double getValue(String text) {
		try {
			return Double.valueOf(text);
		} catch (Exception e) {
			return 0;
		}
	}

	public String getStandard(String text) {
		if ("国家建设部标准".equals(text))
			return "《城镇排水管道检测与评估技术规程》(CJJ 181-2012)";
		else if ("上海市地方标准".equals(text))
			return "《排水管道电视和声纳检测评估技术规程》(DB31/T 444-2009)";
		else if ("广东省地方标准".equals(text))
			return "《城镇公共排水管道检测与评估技术规程》(DB44/T 1025-2012)";
		else if ("北京排水集团企业标准".equals(text))
			return "《排水管渠结构与功能等级评定标准》(QBDG JS001-GW05-2012)";
		else
			return "";
	}

}

/** 设置页面事件 */
class MyPageEvent extends PdfPageEventHelper {
	public void onEndPage(PdfWriter writer, Document document) {
		try {
			BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", false);
			Font font = new Font(bfChinese, 8, 0);
			Phrase phrase = new Phrase(writer.getPageNumber() + "", font);
			ColumnText.showTextAligned(writer.getDirectContent(), 1, phrase, 300, 16, 0);
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
		}
	}
}
