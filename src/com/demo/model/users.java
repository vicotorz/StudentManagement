package com.demo.model;

/**
 * @author dell
 * 
 */
// =============================用户类=================================//
/**
 * @author dell
 * 
 */
public class users {
	private int id;
	private String name;
	private String sex;
	private int age;
	private String passportquality;
	private String passportnumber;
	private String phonenumber;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPassportquality() {
		return passportquality;
	}

	public void setPassportquality(String passportquality) {
		this.passportquality = passportquality;
	}

	public String getPassportnumber() {
		return passportnumber;
	}

	public void setPassportnumber(String passportnumber) {
		this.passportnumber = passportnumber;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public users() {
	}

	public users(int id, String name, String sex, int age,
			String passportquality, String passportnumber, String phonenumber) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.passportquality = passportquality;
		this.passportnumber = passportnumber;
		this.phonenumber = phonenumber;

	}

}
