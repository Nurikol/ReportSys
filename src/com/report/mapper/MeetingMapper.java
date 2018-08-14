package com.report.mapper;

import java.util.List;

import com.report.pojo.Meeting;

public interface MeetingMapper {

	// 添加会议记录至数据库
	void addMeeting(Meeting metting) throws Exception;

	// 从数据库中查找会议记录
	List<Meeting> findAllMeetings() throws Exception;

	// 从数据库中修改会议记录
	void updateMeeting(Meeting meeting) throws Exception;

	// 根据id获取会议记录
	Meeting findMeetingByid(int id) throws Exception;

	// 根据id删除会议记录
	void deleteMeeting(int id) throws Exception;

}
