package com.demo.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.demo.dao.JDBCoperation;
import com.demo.model.users;
import com.demo.service.userservice;

public class mainview extends JFrame {
	/**
	 * 学生管理系统主界面实现代码
	 */
	private static final long serialVersionUID = 1L;
	private JPanel leftpanel = new JPanel();
	private JPanel rightpanel = new JPanel();
	private JPanel underpanel = new JPanel();
	private boolean key = false;
	userservice us = new userservice();

	// ========================主界面建立=====================================//
	public mainview() throws Exception {
		this.setSize(1200, 700);
		this.setTitle("学生管理系统主界面");
		JPanel toppanel = new JPanel();

		JSplitPane spiltpanel = new JSplitPane();

		spiltpanel.setLeftComponent(leftpanel);
		spiltpanel.setRightComponent(rightpanel);
		leftpanel.setPreferredSize(new Dimension(this.getWidth() / 4, this
				.getHeight()));

		leftpanel.setLayout(new GridLayout());

		initpanel();
		initrightpanel();
		initrighttoppanel();

		JLabel toplb = new JLabel("<html><h2>学生信息管理系统<h2><html>");
		toppanel.add(toplb);

		this.setLayout(new BorderLayout());
		this.add(toppanel, BorderLayout.NORTH);

		this.add(spiltpanel, BorderLayout.CENTER);
		toppanel.setBorder(BorderFactory.createLineBorder(Color.black));

		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	// ========================================================================================//
	// ==========================================================================================//

	// =============================变量定义==================================================//

	private JTextField field1 = new JTextField(10);
	private JTextField field2 = new JTextField(10);
	private JTextField field3 = new JTextField(5);
	private JTextField field4 = new JTextField(5);
	private JTextField field5 = new JTextField(10);
	private JTextField field6 = new JTextField(10);
	private JTextField sf1 = new JTextField(10);
	private JTextField sf2 = new JTextField(10);
	private JTextField sf4 = new JTextField(10);
	private JTextField sf5 = new JTextField(10);
	private JTextField sf6 = new JTextField(10);
	private JRadioButton sexbutton1 = new JRadioButton("男");
	private JRadioButton sexbutton2 = new JRadioButton("女");
	private ButtonGroup group1 = new ButtonGroup();
	private ButtonGroup group = new ButtonGroup();

	private String[] data1 = { "", "学生证", "身份证", "其他证件" };
	private String[] data2 = { "", "男", "女" };
	private JComboBox box1 = new JComboBox(data1);
	private JComboBox box2 = new JComboBox(data1);
	private JComboBox box3 = new JComboBox(data2);
	private JComboBox box4 = new JComboBox(data1);

	private JButton selectbutton = new JButton("增加并保存学生信息");

	private JTextField defield1 = new JTextField(10);
	private JTextField defield2 = new JTextField(10);
	private JTextField defield3 = new JTextField(10);
	private JTextField defield4 = new JTextField(10);
	private JTextField defield5 = new JTextField(10);

	private JButton button = new JButton("确认修改");
	private JButton button2 = new JButton("删除该数据");
	private JButton button3 = new JButton("回收站信息");
	private int selectedRow;
	private users user;
	private String id;
	private String name;
	private String sex;
	private String age;
	private String passportquality;
	private String passportnumber;
	private String phonenumber;
	private JTabbedPane jtb = new JTabbedPane();
	private int ss = 0;// 判断是否点击表格上的行，ss=1是，ss=0否

	private JRadioButton sexselect1 = new JRadioButton("男");
	private JRadioButton sexselect2 = new JRadioButton("女");
	private JPanel selectpanel1 = new JPanel();

	// =======================初始化主界面左侧学生添加和查找部分======================================//

	private void initpanel() {

		JPanel selectpanel = new JPanel();
		JPanel searchpanel = new JPanel();
		JPanel deletepanel = new JPanel();

		JLabel lb1 = new JLabel("姓名：");
		JLabel lb2 = new JLabel("学号：");
		JLabel lb3 = new JLabel("年龄：");
		JLabel lb4 = new JLabel("-");
		JLabel lb5 = new JLabel("使用的证件");
		JLabel lb6 = new JLabel("证件号");
		JLabel lb7 = new JLabel("电话");
		JLabel lb8 = new JLabel("性别：");// ==================================================新加的东西

		JButton searchbutton1 = new JButton("查找");
		JButton searchbutton2 = new JButton("清空");
		JPanel searchpanel1 = new JPanel();
		JPanel searchpanel2 = new JPanel();
		JPanel searchpanel2_3 = new JPanel();
		JPanel searchpanel3 = new JPanel();
		JPanel searchpanel4 = new JPanel();
		JPanel searchpanel5 = new JPanel();
		JPanel searchpanel6 = new JPanel();
		JPanel searchpanel7 = new JPanel();

		searchpanel.setLayout(new GridLayout(8, 1));
		searchpanel1.add(lb1);
		searchpanel1.add(field1);
		searchpanel2.add(lb2);
		searchpanel2.add(field2);
		searchpanel2_3.add(lb8);
		searchpanel2_3.add(sexselect1);
		searchpanel2_3.add(sexselect2);
		group1.add(sexselect1);
		group1.add(sexselect2);
		searchpanel3.add(lb3);
		searchpanel3.add(field3);
		searchpanel3.add(lb4);
		searchpanel3.add(field4);

		searchpanel4.add(lb5);
		searchpanel4.add(box2);
		searchpanel5.add(lb6);
		searchpanel5.add(field5);
		searchpanel6.add(lb7);
		searchpanel6.add(field6);

		searchpanel7.add(searchbutton1);
		searchpanel7.add(searchbutton2);

		searchpanel.add(searchpanel2);
		searchpanel.add(searchpanel1);
		searchpanel.add(searchpanel2_3);
		searchpanel.add(searchpanel3);
		searchpanel.add(searchpanel4);
		searchpanel.add(searchpanel5);
		searchpanel.add(searchpanel6);
		searchpanel.add(searchpanel7);

		searchbutton1.addActionListener(new ActionListener() { // ==========================================================================查找触发的函数部分

					public void actionPerformed(ActionEvent e) {
						boolean key = false;
						try {
							String passqua = "";

							String sex = "";
							if (sexselect1.isSelected()) {
								sex = "男";
							} else if (sexselect2.isSelected()) {
								sex = "女";
							} else {
								sex = "";
							}

							if ("学生证".equalsIgnoreCase(((String) box2
									.getSelectedItem()))) {
								passqua = "学生证";
							} else if ("身份证".equalsIgnoreCase(((String) box2
									.getSelectedItem()))) {
								passqua = "身份证";
							} else if ("其他证件".equalsIgnoreCase(((String) box2
									.getSelectedItem()))) {
								passqua = "其他证件";
							} else {
								passqua = "";
							}

							key = checkpanel2(field1.getText(),
									field2.getText(), sex, field3.getText(),
									field4.getText(), field5.getText(),
									field6.getText(), passqua);

							if (key) {
								JDBCoperation dao = new JDBCoperation();
								Object[][] da = dao.searchinfo(
										field1.getText(),// 现在就差重新构建表格
										field2.getText(), sex,
										field3.getText(), field4.getText(),
										field5.getText(), field6.getText(),
										passqua);

								model.setRowCount(0);
								for (Object obj[] : da) {
									model.addRow(obj);
								}
								// 调用相关的清空函数
								searchreset();
							}
						} catch (Exception e1) {
						}

					}
				});

		searchbutton2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				searchreset();

			}
		});
		// 增加学生部分
		JLabel slb1 = new JLabel("学号：");
		JLabel slb2 = new JLabel("姓名：");
		JLabel slb3 = new JLabel("性别：");
		JLabel slb4 = new JLabel("年龄：");
		JLabel slb5 = new JLabel("证件号：");
		JLabel slb6 = new JLabel("电话：");// !!!!!!!!!!!!!!!!!!!!!!!!!!!!1

		JPanel selectpanel2 = new JPanel();
		JPanel selectpanel3 = new JPanel();
		JPanel selectpanel4 = new JPanel();
		JPanel selectpanel5 = new JPanel();
		JPanel selectpanel5_6 = new JPanel();
		JPanel selectpanel6 = new JPanel();
		JPanel selectpanel7 = new JPanel();

		selectpanel1.add(slb1);// (sf1.getText(),sf2.getText(),sex,sf4.getText(),"",sf5.getText(),sf6.getText())
		selectpanel1.add(sf1);//sf1学号
		selectpanel2.add(slb2);
		selectpanel2.add(sf2);// sf2姓名
		selectpanel3.add(slb3);

		group.add(sexbutton1);
		group.add(sexbutton2);

		selectpanel3.add(sexbutton1);
		selectpanel3.add(sexbutton2);
		selectpanel4.add(slb4);
		selectpanel4.add(sf4);//sf4年龄
		selectpanel5.add(slb5);

		selectpanel5.add(box1);//box1证件类型

		selectpanel5_6.add(sf5); //sf5证件号
		sf5.setEditable(false);

		selectpanel6.add(slb6);
		selectpanel6.add(sf6);//sf6电话号码
		selectpanel7.add(selectbutton);

		selectpanel.setLayout(new GridLayout(8, 1));
		selectpanel.add(selectpanel1);
		selectpanel.add(selectpanel2);
		selectpanel.add(selectpanel3);
		selectpanel.add(selectpanel4);
		selectpanel.add(selectpanel5);
		selectpanel.add(selectpanel5_6);
		selectpanel.add(selectpanel6);
		selectpanel.add(selectpanel7);

		// ============修改学生实现代码===================//
		JLabel slb11 = new JLabel("学号：");
		JLabel slb22 = new JLabel("姓名：");
		JLabel slb33 = new JLabel("性别：");
		JLabel slb44 = new JLabel("年龄：");
		JLabel slb55 = new JLabel("证件号：");
		JLabel slb66 = new JLabel("电话：");

		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();
		JPanel jp4 = new JPanel();
		JPanel jp5 = new JPanel();
		JPanel jp6 = new JPanel();
		JPanel jp7 = new JPanel();
		JPanel jp8 = new JPanel();
		deletepanel.setLayout(new GridLayout(8, 1));

		jp1.add(slb11);
		jp1.add(defield1);

		jp2.add(slb22);
		jp2.add(defield2);

		jp3.add(slb33);

		jp3.add(box3);

		jp4.add(slb44);
		jp4.add(defield3);

		jp5.add(slb55);

		jp5.add(box4);

		jp6.add(defield4);

		jp7.add(slb66);
		jp7.add(defield5);

		jp8.add(button);
		jp8.add(button2);
		jp8.add(button3);

		deletepanel.add(jp1);
		deletepanel.add(jp2);
		deletepanel.add(jp3);
		deletepanel.add(jp4);
		deletepanel.add(jp5);
		deletepanel.add(jp6);
		deletepanel.add(jp7);
		deletepanel.add(jp8);

		deletepanel.setBorder(BorderFactory.createTitledBorder(null,
				"学生信息修改（请按规则修改）"));

		selectpanel.setBorder(BorderFactory.createTitledBorder(null,
				"学生信息增加（请按规则添加）"));
		searchpanel.setBorder(BorderFactory.createTitledBorder(null,
				"查找学生信息（输入信息即可）"));
		leftpanel.setLayout(new GridLayout(2, 1));

		JPanel copyrightpanel = new JPanel();
		JPanel copyrightpanel1 = new JPanel();
		JPanel copyrightpanel2 = new JPanel();
		JPanel copyrightpanel12 = new JPanel();
		copyrightpanel.setLayout(new GridLayout());
		JLabel copyright1 = new JLabel("本软件由吉林大学软件学院学生张迪开发");
		JLabel copyright2 = new JLabel("请尊重开发者版权！");
		copyrightpanel1.add(copyright1);
		copyrightpanel2.add(copyright2);
		copyrightpanel12.add(copyright1);
		copyrightpanel12.add(copyright2);
		copyrightpanel.add(copyrightpanel12, BorderLayout.CENTER);
		jtb.add("增加学生", selectpanel);
		jtb.add("修改学生", deletepanel);
		jtb.add("使用版权声明", copyrightpanel);
		leftpanel.add(jtb);
		leftpanel.add(searchpanel);
		deletereset();

		// =======================================================================================//
		box1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!"".equalsIgnoreCase((String) box1.getSelectedItem()))
					sf5.setEditable(true);

			}
		});

		selectbutton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					checkpanel1();
				} catch (Exception e1) {
					System.out.println("检验出现错误！");
				}

				if (key) {
					String sex;
					String passqua = null;
					if (sexbutton1.isSelected()) {
						sex = "男";
					} else {
						sex = "女";
					}
					if (key) {
						if ("学生证".equalsIgnoreCase(((String) box1
								.getSelectedItem()))) {
							passqua = "学生证";
						} else if ("身份证".equalsIgnoreCase(((String) box1
								.getSelectedItem()))) {
							passqua = "身份证";
						} else if ("其他证件".equalsIgnoreCase(((String) box1
								.getSelectedItem()))) {
							passqua = "其他证件";
						} else {
							passqua = "输入错误，系统无法识别！";
						}
					}

					int num = Integer.parseInt(sf1.getText().trim());
					String nam = sf2.getText().trim();
					int age = Integer.parseInt(sf4.getText().trim());
					String passnum = sf5.getText().trim();
					String phonenum = sf6.getText().trim();
					users u = new users(num, nam, sex, age, passqua, passnum,
							phonenum);

					/* key = us.alreadyshowinfo(u); */
					if (key) {
						String[] rowData = { sf1.getText().trim(),
								sf2.getText().trim(), sex,
								sf4.getText().trim(), passqua,
								sf5.getText().trim(), sf6.getText().trim() };
						model.addRow(rowData);
						us.insert(u);
						JOptionPane.showMessageDialog(null, "添加成功！");
					} else if (key == false) {
						JOptionPane.showMessageDialog(null, "添加失败！");
					}

					selectreset();// 添加成功后，窗口自动清空
				}
			}
		});

	}

	// ===============================初始化功能键部分====================================================//
	JTextField fd11;
	int t = 1;

	private void initrightpanel() {

		JPanel grouppanel = new JPanel();
		JLabel lb = new JLabel("一页显示");
		fd11 = new JTextField(1);
		JLabel lbb = new JLabel("数据");
		JButton jb2 = new JButton("确定");
		JButton button0 = new JButton("刷新并查看已有数据");
		JLabel lbfront = new JLabel("上一页");
		JLabel lbbehind = new JLabel("下一页");

		grouppanel.add(lb);
		grouppanel.add(fd11);
		grouppanel.add(lbb);
		grouppanel.add(jb2);
		grouppanel.add(lbfront);
		grouppanel.add(lbbehind);
		grouppanel.add(button0);

		grouppanel.setBorder(BorderFactory.createTitledBorder(null, "跳转功能"));
		JButton button00 = new JButton("数据回收站");
		JButton button1 = new JButton("打印");
		JButton button2 = new JButton("导出excel表格");
		JButton button3 = new JButton("清空显示列表");
		JButton button4 = new JButton("智能分析");
		JButton button5 = new JButton("退出");

		rightpanel.setLayout(new BorderLayout());
		underpanel.setBorder(BorderFactory.createTitledBorder(null, "功能实现"));
		underpanel.add(grouppanel);
		underpanel.add(button1);
		underpanel.add(button2);
		underpanel.add(button3);
		underpanel.add(button4);
		underpanel.add(button5);
		// 监听label,基本上都是接口的方法，基本上没用上//
		lbfront.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (t <= 1) {
					JOptionPane.showMessageDialog(null, "对不起，没有上一页");

				} else {
					t--;
					rowrow(t);
				}
			}
		});

		lbbehind.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				t++;
				rowrow(t);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		//以上label监听结束//
		button0.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				rowrow2(1);
				JOptionPane.showMessageDialog(null, "内容刷新成功！");
			}
		});

		button00.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rowrow(1);
				fd11.setText("");

			}
		});

		jb2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				rowrow(1);
			}
		});

		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					table.print();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "目前无法实现这项功能！请确认有表结构！");
					e1.printStackTrace();

				}

			}
		});
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new EXCEL();
			}
		});

		button3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				final JFrame fr = new JFrame();
				try {
					JLabel lb = new JLabel("清空后将不再显示数据库中的数据，是否继续？");
					JButton bu1 = new JButton("确定");
					JButton bu2 = new JButton("取消");
					JPanel panel1 = new JPanel();
					JPanel panel2 = new JPanel();
					panel1.add(lb);
					panel2.add(bu1);
					panel2.add(bu2);
					fr.setTitle("确定窗口");
					fr.setLayout(new GridLayout(2, 1));
					fr.add(panel1);
					fr.add(panel2);
					fr.setSize(300, 125);
					fr.setLocationRelativeTo(null);
					fr.setVisible(true);
					fr.setResizable(false);

					bu1.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								model.setRowCount(0);
								fr.dispose();
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, "操作失败!");
							}

						}
					});

					bu2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							fr.dispose();
						}
					});
				} catch (Exception e1) {

					e1.printStackTrace();
				}
			}
		});

		button4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new analysisdialogue();

			}
		});

		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		rightpanel.add(underpanel, BorderLayout.SOUTH);
	}

	public void rowrow(int t) {// 注意这里的开始页初始值为1，不为0.
		int i;
		if (fd11.getText().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "请输入一页显示的数据量!");
			fd11.requestFocus();
		} else if (!userservice.checknumber(fd11.getText())) {
			JOptionPane.showMessageDialog(null, "请输入正确格式！（数字）");
		} else {
			i = Integer.parseInt(fd11.getText());//Interger.parseInt

			Object[][] ra = us.rowoperation(i, t);

			model.setRowCount(0);
			for (Object obj[] : ra)
				model.addRow(obj);
		}

	}

	public void rowrow2(int t) {// 添加成功后调用的重载刷新

		Object[][] ra = us.rowoperation(10000, t);

		model.setRowCount(0);
		for (Object obj[] : ra)
			model.addRow(obj);

	}

	// =================================建立表格部分=================================================//
	protected DefaultTableModel model; // 这里定义了下面就不用定义了。否则这里的定义无法和下面的关联
	private JTable table;
	private Object[][] data;
	Object[] columns = { "学号", "姓名", "性别", "年龄", "注册证件", "相关证件号", "电话" };
	JScrollPane resultsetpanel1;

	private void initrighttoppanel() {// ==================================================================initrighttoppanel()用于创建初始化表格
		JDBCoperation jd = new JDBCoperation();
		data = jd.getdata();
		model = new DefaultTableModel(data, columns);// data是你变化的数据量，clumns是表头
		table = new JTable(model);
		table.getTableHeader().setReorderingAllowed(false);
		resultsetpanel1 = new JScrollPane(table);

		rightpanel.add(resultsetpanel1, BorderLayout.CENTER);
		selectedExcel();

	}

	public void selectedExcel() {// ================================================================================选中所选行
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				jtb.setSelectedIndex(1);
				ss = 1;// 选择了表格中的行
				selectedRow = table.getSelectedRow();
				id = model.getValueAt(selectedRow, 0).toString();
				name = model.getValueAt(selectedRow, 1).toString();
				sex = model.getValueAt(selectedRow, 2).toString();
				age = model.getValueAt(selectedRow, 3).toString();
				passportquality = model.getValueAt(selectedRow, 4).toString();
				passportnumber = model.getValueAt(selectedRow, 5).toString();
				phonenumber = model.getValueAt(selectedRow, 6).toString();
				defield1.setText(id);
				defield2.setText(name);
				if ("男".equalsIgnoreCase((String) sex)) {
					box3.setSelectedIndex(1);
				} else {
					box3.setSelectedIndex(2);
				}

				defield3.setText(age);
				if ("身份证".equalsIgnoreCase((String) passportquality)) {
					box4.setSelectedIndex(2);
				} else if ("学生证".equalsIgnoreCase((String) box4
						.getSelectedItem())) {
					box4.setSelectedIndex(1);
				} else {
					box4.setSelectedIndex(3);
				}
				defield4.setText(passportnumber);
				defield5.setText(phonenumber);
				user = new users(Integer.parseInt(id.trim()), name, sex,
						Integer.parseInt(age), passportquality, passportnumber,
						phonenumber);

				button.addActionListener(new ActionListener() {//确认修改的触发按钮
					public void actionPerformed(ActionEvent e) {
						if (ss == 1) { 
//需要用ss判定当前执行的列情况，并需要使用ss值来控制被选中，这里如果没有ss值，该程序代码将会被重复执行多次

							String idd = defield1.getText().trim();
							String namee = defield2.getText().trim();
							String sexx;
							String agee = defield3.getText().trim();
							String passportqualityy;
							String passportnumberr = defield4.getText().trim();
							String phonenumberr = defield5.getText().trim();

							try {
								checkdeletepanel();
								if ("男".equalsIgnoreCase((String) box3
										.getSelectedItem())) {
									sexx = "男";
								} else {
									sexx = "女";
								}

								if ("身份证".equalsIgnoreCase((String) box4
										.getSelectedItem())) {
									passportqualityy = "身份证";
								} else if ("学生证".equalsIgnoreCase((String) box4
										.getSelectedItem())) {
									passportqualityy = "学生证";
								} else {
									passportqualityy = "其他证件";
								}
								users uu = new users(Integer.parseInt(idd),
										namee, sexx, Integer.parseInt(agee),
										passportqualityy, passportnumberr,
										phonenumberr);
								us.updatedata(user, uu);
								rowrow2(1);
								JOptionPane.showMessageDialog(null, "修改成功！");
								selectedRow = 0;
								deletereset();
								ss = 0;
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null,
										e1.getMessage());
								ss = 1;
							}

						}
					}
				});

				button2.addActionListener(new ActionListener() {
					private JFrame fr;

					public void actionPerformed(ActionEvent e) {
						if (ss == 1) { // 这里也需要ss进行判断
							fr = new JFrame();
							fr.setTitle("确认删除信息");
							JLabel lb = new JLabel("是否确认删除？");
							JButton surebutton = new JButton("确定");
							JButton notsurebutton = new JButton("取消");
							JPanel surelbpanel = new JPanel();
							JPanel surebuttonpanel = new JPanel();
							surelbpanel.add(lb);
							surebuttonpanel.add(surebutton);
							surebuttonpanel.add(notsurebutton);
							fr.setLayout(new GridLayout(2, 1));
							fr.add(surelbpanel);
							fr.add(surebuttonpanel);
							fr.setVisible(true);
							fr.setResizable(false);
							fr.setSize(300, 125);
							fr.setLocationRelativeTo(null);
							surebutton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									fr.dispose();
									us.deletedata(user);
									model.removeRow(selectedRow);
									JOptionPane
											.showMessageDialog(null, "删除成功！");
									deletereset();
									selectedRow = 0;
									ss = 0;
								}
							});

							notsurebutton
									.addActionListener(new ActionListener() {
										public void actionPerformed(
												ActionEvent e) {
											fr.dispose();
											// 取消部分不需要将ss置为0
										}
									});
							// button3实现相应的回收站功能
							button3.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub

								}
							});

						}

					}
				});

			}

		});

	}

	//添加成功后执行的清空方法//
	private void selectreset() {
		sf1.setText("");
		sf2.setText("");
		/* group1.setSelected(null, false); */// group1这个不好使
		group.clearSelection();

		// 证件号回归，下一个不能编辑
		{
			box1.setSelectedIndex(0);
			sf5.setEditable(false);
		}
		sf4.setText("");
		sf5.setText("");
		sf6.setText("");
		sf1.requestFocus();

	}

	//修改部分的执行成功的清空//
	private void deletereset() {

		defield1.setText("0");
		defield2.setText("-");
		box3.setSelectedIndex(0);// 用这个方法
		defield3.setText("0");
		defield4.setText("-");
		box4.setSelectedIndex(0);// 用这个方法
		defield5.setText("-");
		defield1.requestFocus();

	}

	//查找部分的清空方法//

	private void searchreset() {
		field1.setText("");
		field2.setText("");
		group1.clearSelection();// 用sexbutton1.setselected(false)不起作用，取消JRdioButton需要这样写
		box2.setSelectedIndex(0);
		field3.setText("");
		field4.setText("");
		field5.setText("");
		field6.setText("");
		field2.requestFocus();

	}

	//查找部分检查用户一个都不填写情况//

	public boolean checkpanel2(String a, String b, String c, String d,
			String e, String f, String g, String h) throws Exception {
		boolean key = true;
		try {
			if (a.equals("") && b.equals("") && c.equals("") && d.equals("")
					&& e.equals("") && f.equals("") && g.equals("")
					&& h.equals("")) {
				{
					key = false;
					throw new Exception("请至少填写一项信息");

				}
			}
		} catch (Exception ee) {
			JOptionPane.showMessageDialog(null, ee.getMessage());
		}
		return key;

	}

	//修改部分的容错行检验//
	private boolean checkdeletepanel() throws Exception { 
		// 这里涉及到判断，注意在进行判断时把每一次ss值置为0

		if (ss == 1) {
			String regex = "\\d{3}";
			String phonenumber = "1(([3][456789])|(47)|([5][012789])|([8][287]))[0-9]{8}$";
			if (defield1.getText().equals("0")
					&& defield2.getText().equals("null")
					&& box3.getSelectedItem().equals("")
					&& defield3.getText().equals("0")
					&& defield4.getText().equals("-")
					&& box3.getSelectedItem().equals("")
					&& defield5.getText().equals("-")) {
				sf1.requestFocus();
				ss = 0;
				throw new Exception("请填写必要的信息！");
			}
			if (defield1.getText().equals("0")) {
				defield1.requestFocus();

				throw new Exception("请填写学号！");
			}
			if (defield2.getText().equals("-")) {
				ss = 0;
				throw new Exception("请填写姓名！");
			}
			if (box3.getSelectedItem().equals("")) {
				ss = 0;
				throw new Exception("请输入性别！");
			}
			if (defield3.getText().equals("0")) {
				defield3.requestFocus();
				ss = 0;
				throw new Exception("填写年龄！");
			}

			if (box3.getSelectedItem().equals("")) {

				ss = 0;
				throw new Exception("请输入证件！");
			}

			if (defield4.getText().equals("-")) {
				defield4.requestFocus();
				ss = 0;
				throw new Exception("请输入证件号码！");
			}

			if (defield5.getText().equals("-")) {
				defield5.requestFocus();
				ss = 0;
				throw new Exception("请输入电话号码！");
			}
			if (!(defield1.getText().matches(regex))) {
				defield1.requestFocus();
				key = false;
				ss = 0;
				throw new Exception("请按标准填写！(学号格式为***（三位数字）)");
			}

			Pattern pattern = Pattern.compile("[\u4E00-\u9FA5]");
			Matcher m = pattern.matcher(defield2.getText());

			if (!m.find()) {
				key = false;
				defield2.requestFocus();
				ss = 0;
				throw new Exception("请输入汉字（不准有英文字母以及其他特殊字符）");
			}
			if ((!userservice.checknumber(defield3.getText()) && Integer
					.parseInt(defield3.getText()) > 50)
					|| defield3.getText().equals("0")) {
				key = false;
				ss = 0;
				throw new Exception("请输入合理年龄！");
			}
			if ("".equalsIgnoreCase((String) box3.getSelectedItem())) {
				ss = 0;
				throw new Exception("请填写男/女");
			}

			if ("".equalsIgnoreCase((String) box4.getSelectedItem())) {

				ss = 0;
				throw new Exception("请输入（身份证/学生证/其他证件）");
			}

			if (!defield5.getText().matches(phonenumber)) {
				defield5.requestFocus();
				ss = 0;
				throw new Exception("请正确填写电话号码");
			} else
				key = true;
			ss = 0;

		}

		return key;
	}

	//添加内容的容错性检查//

	private boolean checkpanel1() throws Exception {

		try {
			String regex = "\\d{3}";
			String phonenumber = "1(([3][456789])|(47)|([5][012789])|([8][287]))[0-9]{8}$";
			if (sf1.getText().equals("") && sf2.getText().equals("")
					&& sf4.getText().equals("") && sf5.getText().equals("")
					&& sf6.getText().equals("") && sf5.equals("")
					&& box1.getSelectedItem().equals("")
					&& (!sexbutton1.isSelected() || !sexbutton2.isSelected())) {
				sf1.requestFocus();
				throw new Exception("请填写必要的信息！");
			}
			if (sf1.getText().equals("")) {
				sf1.requestFocus();
				/*
				 * sf1.setBorder(BorderFactory.createLineBorder(new
				 * Color(255,//这种方法可以，但有点影响外观 0, 0)));
				 */
				selectpanel1.setBackground(getBackground());

				throw new Exception("请填写学号！");
			}
			if (sf2.getText().equals("")) {
				sf2.requestFocus();

				throw new Exception("请填写姓名！");
			}
			if (sf4.getText().equals("")) {
				sf4.requestFocus();
				throw new Exception("请输入年龄！");
			}
			if (box1.getSelectedItem().equals("")) {
				box1.requestFocus();

				throw new Exception("选择添加证件类型！");
			}
			if (sf5.getText().equals("")) {
				sf5.requestFocus();

				throw new Exception("请输入证件号码！");
			}

			if (sf6.getText().equals("")) {
				sf6.requestFocus();

				throw new Exception("请填写电话！");
			}

			if (!(sexbutton1.isSelected() || sexbutton2.isSelected())) {
				sexbutton1.requestFocus();
				throw new Exception("请选择性别信息");
			}

			if (!(sf1.getText().matches(regex))) {
				key = false;
				sf1.requestFocus();
				throw new Exception("请按标准填写！(学号格式为***（三位数字）)");
			}

			Pattern pattern = Pattern.compile("[\u4E00-\u9FA5]");
			Matcher m = pattern.matcher(sf2.getText());

			if (!m.find()) {
				key = false;
				sf2.requestFocus();
				throw new Exception("请输入汉字（不准有英文字母以及其他特殊字符）");

			}

			if (!userservice.checknumber(sf4.getText())
					|| Integer.parseInt(sf4.getText()) <= 0) {
				key = false;
				sf4.requestFocus();
				throw new Exception("请输入合理年龄！");
			}

			if (!sf6.getText().matches(phonenumber)) {
				key = false;
				sf6.requestFocus();
				throw new Exception("请正确填写电话号码");
			} else
				key = true;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		return key;
	}

	// =================================主函数==================================================//
	public static void main(String[] args) throws Exception {

		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		// 必须要启动这个线程，不然无法达到换肤效果，具体原因我也没深究
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager
							.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"Substance Graphite failed to initialize");
				}
				try {
					new mainview();
				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		});
	}
	//类结束//

}
