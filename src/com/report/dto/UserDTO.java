package com.report.dto;

import com.report.pojo.User;

/**
 *  用于查看用户报表的传输对象
 * @author Vera Jiang
 *
 */
public class UserDTO extends User{
	private String daily;
	private String weekly;
	//用户名
	private String username;
	//账号
	private String account;
	//用户编号
	private int id;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDaily() {
		return daily;
	}
	public void setDaily(String daily) {
		this.daily = daily;
	}
	public String getWeekly() {
		return weekly;
	}
	public void setWeekly(String weekly) {
		this.weekly = weekly;
	}

	
}
