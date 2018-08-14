package com.report.service;

import java.util.List;

import com.report.pojo.Notice;

public interface NoticeService {
	// 添加系统公告至数据库
	void addNotice(Notice notice) throws Exception;

	// 从数据库删除系统公告
	void deleteNotice(int id) throws Exception;

	// 获取所有公告列表
	List<Notice> findAllNotices() throws Exception;
}
