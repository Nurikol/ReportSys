package com.report.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.report.mapper.MeetingMapper;
import com.report.pojo.Meeting;
import com.report.service.MeetingService;
import com.report.util.DateSort;

/**
 * className:MeetingServiceImpl
 * function:实现会议记录接口
 * @author Vera Jiang
 *
 */
@Service("meetingService")
public class MeetingServiceImpl implements MeetingService{
	
	@Autowired
	private MeetingMapper meetingMapper;	

	@Override
	public void addMeeting(Meeting metting) throws Exception {
		// TODO Auto-generated method stub
		
		meetingMapper.addMeeting(metting);
	}
	
	
	/**
	 * 从数据库中提取会议记录，并且按时间排序
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Meeting> findAllMeetings() throws Exception {
		// TODO Auto-generated method stub
		List<Meeting> meeting=meetingMapper.findAllMeetings();
		DateSort sort=new DateSort();
		Collections.sort(meeting,sort);
		return meeting;
	}

	/**
	 * 更新会议内容
	 */
	@Override
	public void updateMeeting(Meeting meeting) throws Exception {
		//判断前端传来的值是否重复
		String meetingMembers=meeting.getMeetingMember();
		String meetingMember=clearRepitition(meetingMembers.split(","));
		
		meeting.setMeetingMember(meetingMember);
		String recorder=meeting.getRecorder();
		
		String recorder1=clearRepitition(recorder.split(","));
		meeting.setRecorder(recorder1);
		meetingMapper.updateMeeting(meeting);
	}

	/**
	 * 根据会议id获取会议内容
	 */
	@Override
	public Meeting findMeetingByid(int id) throws Exception {
		// TODO Auto-generated method stub
		return meetingMapper.findMeetingByid(id);
	}

	//删除重复数据
	public String clearRepitition(String[] a) {

		//将String转化为list
		List<String> md=new ArrayList<String>();
		for(String str:a){
			md.add(str);
		}
		
		//删除重复数据
		for (int i = 0; i < md.size(); i++) {
			String mdi = md.get(i);
			for (int j = i + 1; j < md.size(); j++) {
				String mdj = md.get(j);
				if (mdi.equals(mdj)) {
					md.remove(j);
					j--;
				}
			}
		}
		
		//将list转化为String[]
		String[] newStrings = md.toArray(new String[md.size()]); 
		
		//将String[]转化为string
		StringBuffer sb2 = new StringBuffer();
		for (String m : newStrings) {
			sb2.append(m + ",");
		}
		String s2 = sb2.toString();
		String newString=s2.substring(0, s2.length() - 1);
		return newString;
	}

	/**
	 * 根据id删除会议记录（管理员功能）
	 */
	@Override
	public void deleteMeeting(int id) throws Exception {
		// TODO Auto-generated method stub
		meetingMapper.deleteMeeting(id);
	}
}
