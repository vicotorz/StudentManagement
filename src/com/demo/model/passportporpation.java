package com.demo.model;

public class passportporpation {

	private String name;
	private int totalnumber;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalnumber() {
		return totalnumber;
	}

	public void setTotalnumber(int totalnumber) {
		this.totalnumber = totalnumber;
	}

	public passportporpation(String name, int totalnumber) {
		super();
		this.name = name;
		this.totalnumber = totalnumber;
	}

	public passportporpation() {
		super();
	}

}
