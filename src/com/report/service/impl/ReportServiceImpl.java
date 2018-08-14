package com.report.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.report.mapper.ReportMapper;
import com.report.pojo.Dailyreport;
import com.report.pojo.Weeklyreport;
import com.report.service.ReportService;

@Service("reportService")
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportMapper reportMapper;

	@Override
	public void addWeeklyReport(Weeklyreport weeklyreport, String weekDate,
			int userID, int projectID) throws Exception {

		weeklyreport.setWeekDate(weekDate);
		weeklyreport.setUserID(userID);
		weeklyreport.setProjectID(projectID);

		reportMapper.addWeeklyReport(weeklyreport);
	}

	/**
	 * 根据用户id和项目id获取周日报表
	 */
	@Override
	public List<Weeklyreport> getWeeklyReportByUserIDAndProjectID(int userID,
			int projectID) throws Exception {
		return reportMapper.getWeeklyReportByUserIDAndProjectID(userID,
				projectID);
	}

	public List<Dailyreport> getDailyReportByUserIDAndProjectID(int userID,
			int projectID) throws Exception {
		return reportMapper.getDailyReportByUserIDAndProjectID(userID,
				projectID);
	}

	@Override
	public void updateWeeklyReport(Weeklyreport weeklyreport) {
		reportMapper.updateWeeklyReport(weeklyreport);
	}

	@Override
	public int findTodayWeeklyReport(String weekDate, int userID, int projectID) {
		Weeklyreport weeklyreport = new Weeklyreport();
		weeklyreport = reportMapper.findTodayWeeklyReport(weekDate, userID,
				projectID);
		if (weeklyreport != null) {
			return 1;
		}
		return -1;
	}

	@Override
	public List<Dailyreport> findAllDailyReport() throws Exception {
		return reportMapper.findAllDailyReport();
	}

	@Override
	public List<Weeklyreport> findAllWeeklyReport() throws Exception {
		// TODO Auto-generated method stub
		return reportMapper.findAllWeeklyReport();
	}

	/**
	 * 获取已提交周报数量
	 */
	@Override
	public int getWeeklyReportCount(String weekDate) throws Exception {
		return reportMapper.getWeeklyReportCount(weekDate);
	}

	/**
	 * 获取已提交日报数量
	 */
	@Override
	public int getDailyReportCount(String date) throws Exception {

		return reportMapper.getDailyReportCount(date);
	}

	// 添加日报内容
	public void addDailyReport(Dailyreport dailyreport, int userID,
			int projectID, String date) throws Exception {

		dailyreport.setDate(date);
		dailyreport.setUserID(userID);
		dailyreport.setProjectID(projectID);

		reportMapper.addDailyReport(dailyreport);
	}

	// 更新日报
	@Override
	public void updateDailyReport(Dailyreport dailyreport) throws Exception {
		reportMapper.updateDailyReport(dailyreport);
	}

	@Override
	public int findTodayDailyReport(String date, int userID, int projectID) throws Exception {
		Dailyreport dailyreport = new Dailyreport();
		dailyreport = reportMapper
				.findTodayDailyReport(date, userID, projectID);
		if (dailyreport != null) {
			return 1;
		}
		return -1;
	}

	@Override
	public List<Dailyreport> findTodayAllDailyReport(String date, int userID) throws Exception {
		List<Dailyreport> dailyreports = new ArrayList<>();
		dailyreports = reportMapper.findTodayAllDailyReport(date, userID);
		return dailyreports;
	}
}