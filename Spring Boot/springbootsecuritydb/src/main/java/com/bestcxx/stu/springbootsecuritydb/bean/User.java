package com.bestcxx.stu.springbootsecuritydb.bean;

import java.io.Serializable;

public class User implements Serializable{

	/**
	 * 和实体业务对应的用户实体
	 */
	private static final long serialVersionUID = -7549254798751179167L;
	/**用户名*/
	private String userName;
	
	/**密码*/
	private String password;
	
	/**年龄*/
	private int age;
	
	
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	

}
