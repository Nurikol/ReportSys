package com.report.pojo;

/**
 * ClassName: Project
 * Function: 项目实体
 * @author Ironman
 * date: 2017年7月19日
 */
public class Project {
	// 项目编号
	private int projectID;
	// 项目名称
	private String projectName;
	// 负责小组编号
	private int groupID;

	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public int getGroupID() {
		return groupID;
	}

	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}

}
