package com.report.pojo;

import java.io.Serializable;

/**
 * ClassName: Report 
 * Function: 周报实体类
 * @author Ironman 
 * date: 2017年7月25日
 */
public class Weeklyreport implements Serializable{
	// 周报id
	private int weekID;
	// 上传日期
	private String weekDate;
	// 用户的id
	private int userID;
	// 项目ID
	private int projectID;
	// 历史遗留问题
	private String problem;
	// 本周进展
	private String summary;
	// 存在问题及解决方案
	private String solution;
	// 下周计划
	private String nextPlan;
	// 可能问题及解决方案
	private String futureSolution;

	public int getWeekID() {
		return weekID;
	}

	public void setWeekID(int weekID) {
		this.weekID = weekID;
	}

	public String getWeekDate() {
		return weekDate;
	}

	public void setWeekDate(String weekDate) {
		this.weekDate = weekDate;
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

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getNextPlan() {
		return nextPlan;
	}

	public void setNextPlan(String nextPlan) {
		this.nextPlan = nextPlan;
	}

	public String getFutureSolution() {
		return futureSolution;
	}

	public void setFutureSolution(String futureSolution) {
		this.futureSolution = futureSolution;
	}

}
