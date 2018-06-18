package com.demo.view;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.demo.dao.JDBCoperation;
import com.demo.model.users;

public class EXCEL {
	/**
	 * @功能：手工构建一个简单格式的Excel
	 */
	JDBCoperation dao = new JDBCoperation();

	public List<users> getStudent() throws Exception {
		ArrayList<users> list = dao.infoall();
		return list;
	}

	public EXCEL() {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("学生信息表");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("学号");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("姓名");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("性别");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("年龄");
		cell.setCellStyle(style);
		cell = row.createCell((short) 4);
		cell.setCellValue("证件信息");
		cell.setCellStyle(style);
		cell = row.createCell((short) 5);
		cell.setCellValue("证件号码");
		cell.setCellStyle(style);
		cell = row.createCell((short) 6);
		cell.setCellValue("电话号码");
		cell.setCellStyle(style);
		cell = row.createCell((short) 7);

		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
		/* List list = CreateSimpleExcelToDisk.getStudent(); */
		ArrayList<users> list;
		try {
			list = (ArrayList<users>) getStudent();
			for (int i = 0; i < list.size(); i++) {
				row = sheet.createRow((int) i + 1);
				users user = (users) list.get(i);
				// 第四步，创建单元格，并设置值
				row.createCell((short) 0).setCellValue((double) user.getId());
				row.createCell((short) 1).setCellValue(user.getName());
				row.createCell((short) 2).setCellValue(user.getSex());
				row.createCell((short) 3).setCellValue((double) user.getAge());
				row.createCell((short) 4).setCellValue(
						user.getPassportquality());
				row.createCell((short) 5).setCellValue(user.getPhonenumber());
				row.createCell((short) 6).setCellValue(user.getPhonenumber());
				cell = row.createCell((short) 7);

			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// 第六步，将文件存到指定位置
		try {
			javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
			int returnVal = chooser.showOpenDialog(null);
			if (returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
				FileOutputStream fout = new FileOutputStream(chooser
						.getSelectedFile().getPath() + ".xls");
				wb.write(fout);
				fout.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}