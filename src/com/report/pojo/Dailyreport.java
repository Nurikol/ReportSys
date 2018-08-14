package com.report.pojo;


/**
 * ClassName: Dailyreport
 * Function: 日报实体
 * @author Ironman
 * date: 2017年7月27日
 */
public class Dailyreport {
	//日报ID
	private int DayID;
	//用户ID
	private int userID;
	//用户所填写日报属于的项目ID
	private int projectID;
	//完成任务
	private String finishedContent;
	//后续安排
	private String arrangement;
	//日期
	private String date;

	public int getDayID() {
		return DayID;
	}

	public void setDayID(int dayID) {
		DayID = dayID;
	}

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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
