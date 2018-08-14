package com.report.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.report.dto.UserDTO;
import com.report.mapper.UserMapper;
import com.report.pojo.Dailyreport;
import com.report.pojo.Groups;
import com.report.pojo.Project;
import com.report.pojo.Report;
import com.report.pojo.Role;
import com.report.pojo.Statistics;
import com.report.pojo.User;
import com.report.pojo.Weeklyreport;
import com.report.service.GroupService;
import com.report.service.ProjectService;
import com.report.service.ReportService;
import com.report.service.UserService;
import com.report.util.DateTimeUtil;
import com.report.util.Tools;
import com.report.util.ValidateUtil;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 统计分析周报日报提交数量
 * 
 * @author Vera Jiang
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/statistics")
public class StatisticsController {
	@Autowired
	private ReportService reportService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private GroupService groupService;

	static int suid;

	/**
	 * 使用日历方式展现报表统计
	 * 
	 * @param session
	 * @param response
	 * @param type
	 * @throws Exception
	 */
	// 获取报表列表
	@ResponseBody
	// 向前端传值，最终产生json
	@RequestMapping(value = "/getReportList/{type}", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String getReportList(HttpSession session,
			HttpServletRequest request, @PathVariable("type") String type)
			throws Exception {

		// 统计日报表提交情况
		if (type.equals("studentDaily")) {

			User user = (User) session.getAttribute("user");
			List<Role> roles = userService.getRoleByUserId(user.getId());
			if (roles.get(0).getId() == 2) {
				user = userService.getUserById(suid);
			}
			Map<String, Object> jsonMap = new HashMap<>();

			// 找到用户的所有项目
			List<Project> projects = new ArrayList<>();
			List<Groups> groups = groupService.findMyGroups(String.valueOf(user
					.getId()));
			for (Groups groups2 : groups) {
				List<Project> projects2 = projectService.findMyProjects(groups2
						.getId());
				for (Project project : projects2) {
					projects.add(project);
				}
			}
			// 查找所有日报
			List<Dailyreport> list = new ArrayList<>();
			for (Project project : projects) {
				List<Dailyreport> dailyreports = reportService
						.getDailyReportByUserIDAndProjectID(user.getId(),
								project.getProjectID());
				for (Dailyreport dailyreport : dailyreports) {
					list.add(dailyreport);
				}
			}

			// 判断一天所有项目日报都提交后打勾

			List<Dailyreport> result = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				int count = 0;
				for (int j = i + 1; j < list.size(); j++) {
					if (list.get(i).getDate().equals(list.get(j).getDate())) {
						count++;
					}
				}
				if ((count + 1) == projects.size()) {
					result.add(list.get(i));
				}
			}	

			jsonMap.put("rows", result);

			JsonConfig jsonConfig = Tools.getJsonConfig();
			// 排除不需要转换的字段new String[]{"id", "type", "userId" }
			jsonConfig.setExcludes(new String[] { "arrangement",
					"finishedContent", "projectID", "userID", "dayID" });
			JSONObject jsonObject = JSONObject.fromObject(jsonMap, jsonConfig);

			return jsonObject.toString();
		} else if (type.equals("studentWeekly")) {

			User user = (User) session.getAttribute("user");
			List<Role> roles = userService.getRoleByUserId(user.getId());
			if (roles.get(0).getId() == 2) {
				user = userService.getUserById(suid);
			}
			Map<String, Object> jsonMap = new HashMap<>();

			// 找到用户的所有项目所对应的周报
			List<Weeklyreport> weeklyreports = new ArrayList<Weeklyreport>();
			List<Project> projects = new ArrayList<Project>();
			List<Groups> groups = groupService.findMyGroups(String.valueOf(user
					.getId()));
			for (Groups groups2 : groups) {
				List<Project> projects1 = projectService.findMyProjects(groups2
						.getId());
				for (Project project : projects1) {
					projects.add(project);
					List<Weeklyreport> weeklyreport = reportService
							.getWeeklyReportByUserIDAndProjectID(user.getId(),
									project.getProjectID());
					for (Weeklyreport w : weeklyreport) {
						weeklyreports.add(w);
					}
				}
			}

			// 判断一天所有项目周报都提交后打勾

			List<Weeklyreport> result = new ArrayList<>();
			for (int i = 0; i < weeklyreports.size(); i++) {
				int count = 0;
				for (int j = i + 1; j < weeklyreports.size(); j++) {
					if (weeklyreports.get(i).getWeekDate()
							.equals(weeklyreports.get(j).getWeekDate())) {
						count++;
					}
				}
				if ((count + 1) == projects.size()) {
					result.add(weeklyreports.get(i));
				}
			}

			List<String> date = new ArrayList<String>();
			for (Weeklyreport r : result) {
				String weekDate = r.getWeekDate();
				String sunday = DateTimeUtil.getSunday(
						Integer.parseInt(weekDate.substring(0, 4)),
						Integer.parseInt(weekDate.substring(4)));
				date.add(sunday);
			}

			jsonMap.put("rows", date);

			JsonConfig jsonConfig = Tools.getJsonConfig();
			JSONObject jsonObject = JSONObject.fromObject(jsonMap, jsonConfig);
			// JSONObject jsonObject2 = JSONObject.fromObject(jsonMap);

			return jsonObject.toString();

		} else
			return null;
	}
	
	@RequestMapping("/dailyReportStatistics")
	public String dailyReportStatistics(HttpServletRequest request){
		String suID = (String) request.getParameter("suID");
		suid = Integer.parseInt(suID);
		return "dailyReportStatistics";
	}

	@RequestMapping("/weelyReportStatistics")
	public String dailyReportStatistics(HttpServletRequest request,
			HttpSession session, HttpServletResponse resp)
			throws UnsupportedEncodingException {
		String suID = (String) request.getParameter("suID");
		String name= (String) request.getParameter("suN");
		String suN= new String(name.getBytes("ISO-8859-1"), "UTF-8");
		session.setAttribute("suN", suN);
		suid = Integer.parseInt(suID);
		return "weeklyReportStatistics";
	}

}
