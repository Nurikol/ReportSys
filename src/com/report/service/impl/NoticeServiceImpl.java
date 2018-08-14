package com.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.report.mapper.NoticeMapper;
import com.report.pojo.Notice;
import com.report.service.NoticeService;

/**
 * fuction:实现NoticeService接口
 * @author Vera Jiang
 *
 */
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService{
	@Autowired
	private NoticeMapper noticeMapper;

	// 添加系统公告至数据库
	@Override
	public void addNotice(Notice notice) throws Exception {
		// TODO Auto-generated method stub
		noticeMapper.addNotice(notice);
		
	}

	// 从数据库删除系统公告
	@Override
	public void deleteNotice(int id) throws Exception {
		// TODO Auto-generated method stub
		noticeMapper.deleteNotice(id);
	}

	@Override
	public List<Notice> findAllNotices() throws Exception {
		
		return noticeMapper.findAllNotices();
	}
	
	

}
