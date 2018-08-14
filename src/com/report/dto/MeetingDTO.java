package com.report.dto;

public class MeetingDTO {
	//会议id
	private int id;
	// 会议主题
	private String theme;
	// 会议内容
	private String meetingContent;
	// 在系统中的与会人员
	private String meetingMember;
	// 会议记录者
	private String recorder;
	// 会议时间
	private String meetingDate;
	// 不在系统中的与会人员
	private String otherMember;
	// 填写时间
	private String createDate;
	//会议年份
	private String year;
	//会议月份
	private String month;
	//参加会议的人数
	private int numbers;
	//周数
	private String weekNumber;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getMeetingContent() {
		return meetingContent;
	}
	public void setMeetingContent(String meetingContent) {
		this.meetingContent = meetingContent;
	}
	public String getMeetingMember() {
		return meetingMember;
	}
	public void setMeetingMember(String meetingMember) {
		this.meetingMember = meetingMember;
	}
	public String getRecorder() {
		return recorder;
	}
	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}
	public String getMeetingDate() {
		return meetingDate;
	}
	public void setMeetingDate(String meetingDate) {
		this.meetingDate = meetingDate;
	}
	public String getOtherMember() {
		return otherMember;
	}
	public void setOtherMember(String otherMember) {
		this.otherMember = otherMember;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getNumbers() {
		return numbers;
	}
	public void setNumbers(int numbers) {
		this.numbers = numbers;
	}
	public String getWeekNumber() {
		return weekNumber;
	}
	public void setWeekNumber(String weekNumber) {
		this.weekNumber = weekNumber;
	}
	
	

}
