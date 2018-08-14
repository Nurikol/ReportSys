package com.report.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


public class Meeting {
	//会议id
	private int id;
	//会议主题
	private String Theme;
	//会议内容
	private String meetingContent;
	//在系统中的与会人员
	private String meetingMember;
	//会议记录者
	private String recorder;
	//会议时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date meetingDate;
	//不在系统中的与会人员
	private String otherMember;
	//填写时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTheme() {
		return Theme;
	}
	public void setTheme(String theme) {
		Theme = theme;
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
	public Date getMeetingDate() {
		return meetingDate;
	}
	public void setMeetingDate(Date meetingDate) {
		this.meetingDate = meetingDate;
	}
	public String getOtherMember() {
		return otherMember;
	}
	public void setOtherMember(String otherMember) {
		this.otherMember = otherMember;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
