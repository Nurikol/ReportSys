package com.report.dto;

import java.util.List;

import com.report.pojo.User;

public class memberDTO {
	private List<User> users;
	private int groupid;
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public int getGroupid() {
		return groupid;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

}
