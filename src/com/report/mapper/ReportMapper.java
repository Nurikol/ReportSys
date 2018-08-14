package com.report.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.report.pojo.Dailyreport;
import com.report.pojo.Weeklyreport;

public interface ReportMapper {
	// 添加周报内容
	void addWeeklyReport(Weeklyreport weeklyreport) throws Exception;

	// 根据用户ID和项目ID获取周报列表
	List<Weeklyreport> getWeeklyReportByUserIDAndProjectID(int userID,
			int projectID) throws Exception;

	// 根据用户ID和项目ID获取日报列表
	List<Dailyreport> getDailyReportByUserIDAndProjectID(int userID,
			int projectID) throws Exception;

	// 更新周报
	void updateWeeklyReport(Weeklyreport weeklyreport);

	// 查找当天周报是否存在
	Weeklyreport findTodayWeeklyReport(@Param("weekDate") String weekDate,
			@Param("userID") int userID, @Param("projectID") int projectID);

	// 查询所有日报
	List<Dailyreport> findAllDailyReport() throws Exception;

	// 查询所有周报
	List<Weeklyreport> findAllWeeklyReport() throws Exception;

	// 获取已提交周报数量
	int getWeeklyReportCount(String weekDate) throws Exception;

	// 获取已提交日报数量
	int getDailyReportCount(String date) throws Exception;

	// 添加日报内容
	void addDailyReport(Dailyreport dailyreport) throws Exception;

	// 更新日报
	void updateDailyReport(Dailyreport dailyreport) throws Exception;

	// 查找当天日报是否存在
	Dailyreport findTodayDailyReport(@Param("date") String date,
			@Param("userID") int userID, @Param("projectID") int projectID) throws Exception;

	// 查找当天所有日报列表
	List<Dailyreport> findTodayAllDailyReport(@Param("date") String date,
			@Param("userID") int userID) throws Exception;
}
