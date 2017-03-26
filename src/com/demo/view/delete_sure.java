package com.demo.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class delete_sure extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public delete_sure() {

		JLabel lb = new JLabel("将清空数据库中的有关数据，是否继续？");
		JButton bu1 = new JButton("确定");
		JButton bu2 = new JButton("取消");
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		panel1.add(lb);
		panel2.add(bu1);
		panel2.add(bu2);
		this.setTitle("确定窗口");
		this.setLayout(new GridLayout(2, 1));
		this.add(panel1);
		this.add(panel2);
		this.setSize(300, 125);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		
		

		bu1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					 

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "操作失败!");

				}

			}
		});

		bu2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
			}
		});
	}
	
	
}
