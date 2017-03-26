package com.demo.view;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.demo.service.userservice;

public class analysisdialogue {
	private JComboBox box;
	userservice us = new userservice();
	JFrame fr = new JFrame();

	public analysisdialogue() {

		fr.setTitle("智能分析");
		fr.setLayout(new GridLayout(3, 1));
		JLabel lb = new JLabel("请选择");

		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		String[] data = { "男女比例分析", "使用证件比例分析"};
		box = new JComboBox(data);
		JButton bu1 = new JButton("确定");
		JButton bu2 = new JButton("取消");
		panel1.add(lb, BorderLayout.WEST);
		panel2.add(box);
		panel3.add(bu1);
		panel3.add(bu2);
		fr.add(panel1);
		fr.add(panel2);
		fr.add(panel3);

		fr.setLocationRelativeTo(null);
		fr.setVisible(true);
		fr.setResizable(false);
		fr.setSize(250, 175);

		bu2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cl();// 这里应该是dispose（），但这里的analysisdialogue和sure_view中的调用JFrame中的方法不一样，没有继承过来。因此不能直接调用。需要把他放在另一个方法中使用
			}
		});

		bu1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ("男女比例分析".equalsIgnoreCase((String) box.getSelectedItem())) {
					us.showchart();

				} else if ("使用证件比例分析".equalsIgnoreCase((String) box
						.getSelectedItem())) {
					us.showchart2();

				}/*
				 * else if ("年龄分布分析".equalsIgnoreCase((String) box
				 * .getSelectedItem())) { JOptionPane.showMessageDialog(null,
				 * "还没实现"); }
				 */

			}
		});

	}

	public void cl() {
		fr.dispose();
	}

}
