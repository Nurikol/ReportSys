package com.report.pojo;

/**
 * className:Notice
 * fuction:系统公告表实体
 * @author Vera Jiang
 *
 */
public class Notice {
	//公告id
	private String id;
	//公告内容
	private String content;
	//公告发布的日期
	private String createTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String string) {
		this.createTime = string;
	}
	
	
}
