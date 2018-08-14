package com.report.dto;

/**
 * className:groupDTO
 * function:获取小组长或成员的id
 * @author Vera Jiang
 *
 */
public class GroupDTO {
	//小组组长id
	private String leaderId;
	//小组成员id
	private String memberId;
	//小组组长名称
	private String leaderName;
	//小组成员名称
	private String memberName;
	
	public String getLeaderId() {
		return leaderId;
	}
	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getLeaderName() {
		return leaderName;
	}
	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
}
