package com.demo.model;

public class sexpie {

	private String sex;
	private int mam_women;

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getMam_women() {
		return mam_women;
	}

	public void setMam_women(int mam_women) {
		this.mam_women = mam_women;
	}

	public sexpie(String sex, int mam_women) {
		super();
		this.sex = sex;
		this.mam_women = mam_women;
	}

	public sexpie() {
		super();
	}

}
