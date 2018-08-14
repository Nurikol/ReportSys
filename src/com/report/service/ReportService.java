package com.report.service;

import java.util.List;

import com.report.pojo.Dailyreport;
import com.report.pojo.Weeklyreport;

public interface ReportService {
	// 添加周报内容
	void addWeeklyReport(Weeklyreport weeklyreport, String weekDate,
			int userID, int projectID) throws Exception;

	// 根据用户ID和项目ID获取周报列表
	List<Weeklyreport> getWeeklyReportByUserIDAndProjectID(int userID,
			int projectID) throws Exception;

	// 根据用户ID和项目ID获取日报列表
	List<Dailyreport> getDailyReportByUserIDAndProjectID(int userID,
			int projectID) throws Exception;

	// 查找当天周报是否存在 存在为1 不存在为-1
	int findTodayWeeklyReport(String weekDate, int userID, int projectID);

	// 更新周报
	void updateWeeklyReport(Weeklyreport weeklyreport);

	// 查询所有日报
	List<Dailyreport> findAllDailyReport() throws Exception;

	// 查询所有周报
	List<Weeklyreport> findAllWeeklyReport() throws Exception;

	// 获取已提交周报数量
	int getWeeklyReportCount(String weekDate) throws Exception;

	// 获取已提交日报数量
	int getDailyReportCount(String nowDate) throws Exception;

	// 查找当天日报是否存在 存在为1 不存在为-1
	int findTodayDailyReport(String todaydate, int userID, int projectID) throws Exception;

	// 查找当天所有日报列表
	List<Dailyreport> findTodayAllDailyReport(String date, int userID) throws Exception;

	// 添加日报内容
	void addDailyReport(Dailyreport dailyreport, int userID, int projectID,
			String todaydate) throws Exception;

	// 更新日报
	void updateDailyReport(Dailyreport dailyreport) throws Exception;
}
