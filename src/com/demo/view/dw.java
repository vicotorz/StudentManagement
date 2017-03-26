package com.demo.view;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import com.demo.dao.JDBCoperation;
import com.demo.model.sexpie;

public class dw {
	private JDBCoperation dao = new JDBCoperation();

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

	dw() {
		showchart();
	}

	public static void main(String[] args) {
		new dw();
	}
}
