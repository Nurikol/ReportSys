package com.report.dto;

public class DailyReportDTO {
	//日期
	String date;
	//用户名
	String userName;
	//项目名
	String projectName;
	//完成任务
	String finishedContent;
	//后续安排
	String arrangement;

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

	public String getFinishedContent() {
		return finishedContent;
	}

	public void setFinishedContent(String finnishedContent) {
		this.finishedContent = finnishedContent;
	}

	public String getArrangement() {
		return arrangement;
	}

	public void setArrangement(String arrangement) {
		this.arrangement = arrangement;
	}
}
