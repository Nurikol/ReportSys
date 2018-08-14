package com.report.dto;

import org.springframework.format.annotation.DateTimeFormat;

public class WeeklyReportDTO {
	//日期

	String date;
	//用户名
	String userName;
	//项目名
	String projectName;
	//用户Id
	int userID;
	//项目ID
	int projectID;

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
}
