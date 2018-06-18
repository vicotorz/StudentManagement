package com.demo.view;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

public class ChartDemo {
	// 创建柱状图
	public JPanel createChart() {
		// 创建数据的集合
		DefaultCategoryDataset dataset = getBarDataset();
		JFreeChart chart = ChartFactory.createBarChart3D("销售量分析", "水果", "产量",
				dataset, PlotOrientation.VERTICAL, true, false, false);
		// 获取柱状图的plot的对象
		CategoryPlot plot = chart.getCategoryPlot();
		// 获得标题类，设置标题的字体颜色大小
		TextTitle textTitle = chart.getTitle();
		// 取得纵轴
		NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();
		// PLAIN字体样式为普通， BOLD字体样式为粗体
		textTitle.setFont(new Font("黑体", Font.BOLD, 30));
		// 取得横轴
		CategoryAxis domainAxis = plot.getDomainAxis();
		// 设置柱状图整体距离y轴左侧10%
		domainAxis.setLowerMargin(0.1);
		// 设置柱状图整体距离y轴右侧10%
		domainAxis.setUpperMargin(0.1);
		// 设置X轴与X轴标签之间的距离为1个像素 setCategoryMargin设置距离百分比
		domainAxis.setCategoryLabelPositionOffset(1);
		// 设置横轴标尺值字体
		domainAxis.setTickLabelFont(new Font("sans-serif", Font.BOLD, 22));
		// 设置横轴显示标签的字体
		domainAxis.setLabelFont(new Font("宋体", Font.BOLD, 18));
		// 设置纵轴标尺值字体
		numberaxis.setTickLabelFont(new Font("sans-serif", Font.BOLD, 22));
		// 设置纵轴显示标签字体
		numberaxis.setLabelFont(new Font("黑体", Font.BOLD, 18));
		// char.getLegend 取得表的第一个图例，设置图例的字体
		chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 22));
		ChartPanel panel = new ChartPanel(chart);
		return panel;

	}

	// 获取柱状图数据
	private DefaultCategoryDataset getBarDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(33, "北京", "苹果");
		dataset.addValue(55, "上海", "梨子");
		dataset.addValue(77, "南昌", "葡萄");
		dataset.addValue(13, "海南", "香蕉");
		dataset.addValue(28, "北京", "荔枝");
		dataset.addValue(65, "上海", "荔枝");
		return dataset;
	}

	public void showchart() {

		JFrame frame = new JFrame();
		frame.setTitle("分析结果");
		frame.add(createChart());
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(frame);
		frame.setVisible(true);

	}

	ChartDemo() {
		showchart();
	}

	public static void main(String[] args) {
		new ChartDemo();

	}

}