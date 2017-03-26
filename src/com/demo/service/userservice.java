package com.demo.service;

import java.awt.Font;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import com.demo.dao.JDBCoperation;
import com.demo.model.passportporpation;
import com.demo.model.sexpie;
import com.demo.model.users;

/**
 * 
 * mainview的所有操作都通过这里实现，userservice通过调用JDB实现
 * 
 */
public class userservice {
	private JDBCoperation dao = new JDBCoperation();

	// ==========================加入数据库中=======================================//
	public void insert(users u) {
		dao.save(u);
	}

	// =========================更新数据库==========================================//
	public void updatedata(users u, users uu) {
		dao.selectuser(u, uu);
	}

	// =================================删除数据部分=================================//
	public void deletedata(users u) {
		dao.deleteuser(u);
	}

	// ==================================查看数据库中信息是否为0=====================//

	// ===============================判断是否为数字的函数===========================//
	public static boolean checknumber(String a) {
		boolean key1 = true;
		String num = a;
		int len = a.length();
		for (int o = 0; o < len; o++) {
			if (!Character.isDigit(num.charAt(o))) {
				key1 = false;
			}
		}
		return key1;
	}

	// ==============================创建分析图表方法===============================//

	public PieDataset createchart() {

		DefaultPieDataset dataset = new DefaultPieDataset();
		ArrayList<sexpie> ee = dao.fetchsexinfo();
		for (sexpie qq : ee) {
			dataset.setValue(qq.getSex(), qq.getMam_women());//
		}
		return dataset;

	}

	public JPanel createChart() {
		JFreeChart chart = ChartFactory.createPieChart("男女比例分析图表",
				createchart(), true, true, false);

		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 16));
		chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 12));

		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelFont(new Font("宋体", Font.BOLD, 12));

		ChartPanel panel = new ChartPanel(chart);
		return panel;
	}

	public void showchart() {
		JFrame frame = new JFrame();
		frame.setTitle("分析结果");
		frame.add(createChart());
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(frame);
		frame.setVisible(true);
	}

	// ==============================证件比例分析函数================================//

	public PieDataset passport() {

		DefaultPieDataset dataset = new DefaultPieDataset();
		ArrayList<passportporpation> ee = dao.fetchsexinfo2();
		for (passportporpation qq : ee) {
			dataset.setValue(qq.getName(), qq.getTotalnumber());//
		}
		return dataset;

	}

	public JPanel passprty() {
		JFreeChart chart = ChartFactory.createPieChart("证件比例分析结果", passport(),
				true, true, false);

		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 16));
		chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 12));

		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelFont(new Font("宋体", Font.BOLD, 12));

		ChartPanel panel = new ChartPanel(chart);
		return panel;
	}

	public void showchart2() {
		JFrame frame = new JFrame();
		frame.setTitle("分析结果");
		frame.add(passprty());
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(frame);
		frame.setVisible(true);
	}

	// =============================查找数据库中已经有的数据=====================//

	// =========================分页操作的rowset方法============================//
	public Object[][] rowoperation(int Pagesize, int Pagenumber) {

		CachedRowSet rs = dao.getEmp(Pagesize, Pagenumber);// 获得rowset对象
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		try {
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String sex = rs.getString(3);
				int age = rs.getInt(4);
				String passportquality = rs.getString(5);
				String passportnumber = rs.getString(6);
				String phonenumber = rs.getString(7);

				Object[] objects = new Object[] { id, name, sex, age,
						passportquality, passportnumber, phonenumber };
				list.add(objects);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list.toArray(new Object[0][0]);
	}

}
