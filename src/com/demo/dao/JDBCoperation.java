package com.demo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.sun.rowset.*;
import javax.sql.rowset.CachedRowSet;
import javax.swing.JOptionPane;

import com.demo.model.passportporpation;
import com.demo.model.qualityshow;
import com.demo.model.sexpie;
import com.demo.model.users;

//================数据库操作的一些操作=======================//

public class JDBCoperation {
	private PreparedStatement stmt;
	private ResultSet rs;

	// =====================保存数据================================//
	public void save(users u) {
		String sql = "insert into tb_user(id,name,sex,age,passportquality,passportnumber,phonenumber)values(?,?,?,?,?,?,?)";
		try {

			stmt = jdbcmanager.getConn().prepareStatement(sql);
			stmt.setInt(1, u.getId());
			stmt.setString(2, u.getName());
			stmt.setString(3, u.getSex());
			stmt.setInt(4, u.getAge());
			stmt.setString(5, u.getPassportquality());
			stmt.setString(6, u.getPassportnumber());
			stmt.setString(7, u.getPhonenumber());
			stmt.execute();// ==========================================不能写成stmt.excuteupdate(sql)这种形式！！！

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, "连接数据库发生故障！请检查相应的程序环境！");
			e.printStackTrace();
			System.exit(1);

		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			jdbcmanager.close();
		}
	}

	// ===============================查所有的信息，返回值是一个list方法=====================================================//
	public ArrayList<users> infoall() {
		ArrayList<users> list = null;
		try {
			list = new ArrayList<users>();
			String sql = "select * from tb_user";
			stmt = jdbcmanager.getConn().prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String sex = rs.getString(3);
				int age = rs.getInt(4);
				String passportquality = rs.getString(5);
				String passportnumber = rs.getString(6);
				String phonenumber = rs.getString(7);
				list.add(new users(id, name, sex, age, passportquality,
						passportnumber, phonenumber));
			}
			return list;
		} catch (SQLException e) {
			// e.printStackTrace();
		}
		return list;

	}

	// ==================================(初始取)打开界面取出数据库数据的方法==================================================//
	private Object[][] getDataArray() throws SQLException {

		ArrayList<Object[]> list = new ArrayList<Object[]>();
		String sql = "select * from tb_user";
		stmt = jdbcmanager.getConn().prepareStatement(sql);
		rs = stmt.executeQuery();
		while (rs.next()) {
			Object[] objects = new Object[] { rs.getInt(1), rs.getString(2),
					rs.getString(3), rs.getInt(4), rs.getString(5),
					rs.getString(6), rs.getString(7) };
			list.add(objects);
		}
		return list.toArray(new Object[0][0]);
	}

	public Object[][] getdata() {
		Object[][] data = null;
		try {
			data = getDataArray();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;

	}

	// ===================================================================================//
	public void readinfo() {

		try {
			while (rs.next()) {

				new users(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getInt(4), rs.getString(5), rs.getString(6),
						rs.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// ==============================查找部分的实现代码=========================================================//
	public Object[][] searchinfo(String a, String b, String b_c, String c,
			String d, String e, String f, String g) {

		ArrayList<Object[]> list = new ArrayList<Object[]>();
		try {
			String sql = "select * from tb_user where '1'='1' ";// ====================拼接SQL语句

			if (!a.equals(""))
				sql = sql.concat(" and name like '%" + a + "%'");
			if (!b.equals(""))
				sql = sql
						.concat(" and id like '%" + Integer.parseInt(b) + "%'");
			if (!b_c.equals(""))
				sql = sql.concat("and sex ='" + b_c + "'");
			if (!c.equals(""))
				sql = sql.concat(" and age>= '" + c + "'");
			if (!d.equals(""))
				sql = sql.concat(" and age<= '" + d + "'");
			if (!g.equals(""))
				sql = sql.concat(" and PASSPORTQUALITY like '%" + g + "%'");
			if (!e.equals(""))
				sql = sql.concat(" and PASSPORTNUMBER like '%" + e + "%'");
			if (!f.equals(""))
				sql = sql.concat(" and PHONENUMBER like '%" + f + "%'");

			stmt = jdbcmanager.getConn().prepareStatement(sql);
			rs = stmt.executeQuery();// =================================拼接SQL语句出现了问题
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
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			jdbcmanager.close();
		}

		return list.toArray(new Object[0][0]);

	}

	// ============================查找得到相应的用户信息（得到给定的信息查询list）=======================================//
	public void selectuser(users u, users uu) {
		// 注意这里的updeate语句不是and 是，都是，，，，，不用重复说明

		String sql = " update tb_user set id=?,name=?,sex=?,age=?,passportquality=?,passportnumber=?,phonenumber=? where id=?";// 注意这里的where值填写一个就行，多了容易乱
		try {
			stmt = jdbcmanager.getConn().prepareStatement(sql);
			stmt.setInt(1, uu.getId());
			stmt.setString(2, uu.getName());
			stmt.setString(3, uu.getSex());
			stmt.setInt(4, uu.getAge());
			stmt.setString(5, uu.getPassportquality());
			stmt.setString(6, uu.getPassportnumber());
			stmt.setString(7, uu.getPhonenumber());

			stmt.setInt(8, u.getId());

			stmt.executeUpdate();// 这里不用再次写sql.记住以后不能这么用
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			jdbcmanager.close();
		}
	}

	// ============================删除部分的实现代码==========================================================//

	public void deleteuser(users uu) {

		String sql = "delete from tb_user where id=? and name=? and sex=? and age=? and passportquality=? and passportnumber=? and phonenumber=? ";

		try {
			stmt = jdbcmanager.getConn().prepareStatement(sql);
			stmt.setInt(1, uu.getId());
			stmt.setString(2, uu.getName());
			stmt.setString(3, uu.getSex());
			stmt.setInt(4, uu.getAge());
			stmt.setString(5, uu.getPassportquality());
			stmt.setString(6, uu.getPassportnumber());
			stmt.setString(7, uu.getPhonenumber());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			jdbcmanager.close();
		}
	}

	// ====================================取证件信息函数=====================================================//
	public ArrayList<passportporpation> fetchsexinfo2() {
		String sql = " select PASSPORTQUALITY,count(PASSPORTQUALITY) from tb_user group by PASSPORTQUALITY";
		ArrayList<passportporpation> list = new ArrayList<passportporpation>();
		try {
			stmt = jdbcmanager.getConn().prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				passportporpation pass = new passportporpation(rs.getString(1),
						rs.getInt(2));

				list.add(pass);
			}

		} catch (SQLException e) {

		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

			jdbcmanager.close();
		}
		return list;
	}

	// ===================================取性别数据函数======================================================//
	public ArrayList<sexpie> fetchsexinfo() {
		String sql = " select sex,count(sex) from tb_user group by sex";
		ArrayList<sexpie> list = new ArrayList<sexpie>();
		try {
			stmt = jdbcmanager.getConn().prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				sexpie sexp = new sexpie(rs.getString(1), rs.getInt(2));
				list.add(sexp);
			}

		} catch (SQLException e) {

		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

			jdbcmanager.close();
		}
		return list;
	}
	
	//=================================获取相关年龄段信息的函数==============================//
	public ArrayList<qualityshow> fechinfo2(){
		String sql = " select sex,count(sex) from tb_user group by sex";
		ArrayList<qualityshow> list = new ArrayList<qualityshow>();
		try {
			stmt = jdbcmanager.getConn().prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				qualityshow qual=new qualityshow(Integer.parseInt(rs.getString(1)), rs.getInt(2));
				list.add(qual);
			}

		} catch (SQLException e) {

		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

			jdbcmanager.close();
		}
		return list;
	}

	// ================================分页操作==============================================//
	public CachedRowSet getEmp(int Pagesize, int Pagenumber) {// 从数据库中读入数据放入内存中。rowset
		CachedRowSet rowset = null;// 在这里定义为了保证返回值
		String sql = " select * from tb_user ";
		try {
			stmt = jdbcmanager.getConn().prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);// 滚动，只读
			rs = stmt.executeQuery();
			rowset = new CachedRowSetImpl();// 没匹配，这里的语法可能出现错误。/*
											// rowset.populate(rs); // 缓存进内存 */
			rowset.setPageSize(Pagesize);// 显示数据行的个数
			int startRow = (Pagenumber - 1) * Pagesize + 1;// 开始的主页
			rowset.populate(rs, startRow);// 从哪开始显示多少条

		} catch (SQLException e) {

		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}

			jdbcmanager.close();
		}
		return rowset;

	}

}
