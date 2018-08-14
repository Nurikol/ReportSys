package com.report.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.chainsaw.Main;

public class DateTimeUtil {
	public static String getDateAndTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间
	}

	public static String getTime() {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");// 设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间
	}

	public static String getDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间
	}

	// 获取本周的周末对应的日期
	public static String getWeekDate() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("MM月dd日");
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY); // 获取本周天的日期
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		return df.format(cal.getTime());

	}

	// 获取本周的周6对应的日期
	public static String getWeekendDate() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("MM月dd日");
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY); // 获取本周6的日期
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		return df.format(cal.getTime());

	}

	// 获取本周的周一对应的日期
	public static String getWeekFirstDate() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("MM月dd日");
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
		cal.add(Calendar.WEEK_OF_YEAR, 0);
		return df.format(cal.getTime());

	}

	// 获得年数
	public static int getYearNumber() {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);// 多少年
		return year;
	}

	// 获得周数
	public static int getWeekNumber() {
		Calendar c = Calendar.getInstance();
		int i = c.get(Calendar.WEEK_OF_YEAR);// 第几周
		return i;
	}

	// 获得周数的日期范围
	public static String getWeekTime() {
		String a = DateTimeUtil.getWeekDate();
		String b = DateTimeUtil.getWeekFirstDate();
		return b + "~" + a;
	}

	// 存储到数据库的日期格式
	public static String weeklyReportDate() {
		Calendar c = Calendar.getInstance();
		int i = c.get(Calendar.WEEK_OF_YEAR);// 第几周
		int year = c.get(Calendar.YEAR);// 多少年
		String iString = String.valueOf(i);
		String yString = String.valueOf(year);
		return yString + iString;
	}

	public static String getTime1(int year, int week) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("MM月dd日");
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.WEEK_OF_YEAR, week);
		calendar.set(Calendar.DAY_OF_WEEK, 2);
		Date date1 = calendar.getTime();
		String s1 = df.format(date1);

		// 周天
		calendar.set(Calendar.WEEK_OF_YEAR, week + 1);
		calendar.set(Calendar.DAY_OF_WEEK, 1);
		Date date2 = calendar.getTime();
		String s2 = df.format(date2);
		return s1 + "~" + s2;
	}
	
	/**
	 * 根据周数获取这周的周日
	 * @param year
	 * @param week
	 * @return
	 */
	public static String getSunday(int year, int week) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.WEEK_OF_YEAR, week+1);
		calendar.set(Calendar.DAY_OF_WEEK, 1);
		Date date1 = calendar.getTime();
		String s1 = df.format(date1);
		return s1;
	}
}
