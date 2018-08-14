package com.report.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.report.dto.DailyReportDTO;
import com.report.dto.UserDTO;
import com.report.dto.WeeklyReportDTO;
import com.report.pojo.Dailyreport;
import com.report.pojo.Groups;
import com.report.pojo.Project;
import com.report.pojo.Role;
import com.report.pojo.User;
import com.report.pojo.Weeklyreport;
import com.report.service.GroupService;
import com.report.service.ProjectService;
import com.report.service.ReportService;
import com.report.service.UserService;
import com.report.util.DateTimeUtil;

@Controller
@Scope("prototype")
@RequestMapping("/report")
public class ReportController {
	@Autowired
	private ReportService reportService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private GroupService groupService;

	static int UID;
	static int PID;

	@RequestMapping("/dailyReport")
	public String dailyReport(HttpSession session, HttpServletRequest request)
			throws Exception {
		// 显示当天日期
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
		String dailyDate = sf.format(date);
		session.setAttribute("dailyDate", dailyDate);

		// 显示日报
		List<Dailyreport> dailyreports = new ArrayList<>();
		User user = (User) session.getAttribute("user");

		// 得到今天的所有日报，显示当天输入框内容
		List<Dailyreport> todayAlldailyreports = new ArrayList<>();
		String todayDaily = DateTimeUtil.getDate();
		todayAlldailyreports = reportService.findTodayAllDailyReport(
				todayDaily, user.getId());
		List<DailyReportDTO> todayDailyReportDTOs = new ArrayList<>();
		for (Dailyreport dailyreport : todayAlldailyreports) {
			DailyReportDTO dailyReportDTO = new DailyReportDTO();
			Project project = new Project();
			project = projectService.getProjectById(dailyreport.getProjectID());
			dailyReportDTO.setProjectName(project.getProjectName());
			dailyReportDTO.setArrangement(dailyreport.getArrangement());
			dailyReportDTO.setFinishedContent(dailyreport.getFinishedContent());
			todayDailyReportDTOs.add(dailyReportDTO);
		}
		session.setAttribute("todayDailyReportDTOs", todayDailyReportDTOs);

		// 得到当前用户全部小组
		String userID = String.valueOf(user.getId());
		List<Groups> groups = groupService.findMyGroups(userID);
		// 显示集
		List<Project> myAllProject = new ArrayList<>();
		// 查找每个小组对应的所有项目
		for (Groups group : groups) {
			List<Project> myprojects = projectService.findMyProjects(group
					.getId());
			for (Project project : myprojects) {
				myAllProject.add(project);
			}
		}

		List<DailyReportDTO> projectdailyreports = new ArrayList<>();
		// 把查找到的所有日报汇总传给前端
		for (Project project : myAllProject) {
			dailyreports = reportService.getDailyReportByUserIDAndProjectID(
					user.getId(), project.getProjectID());
			for (int i = dailyreports.size() - 1; i >= 0; i--) {
				DailyReportDTO dailyReportDTO = new DailyReportDTO();

				User user2 = new User();
				user2 = userService
						.getUserById(dailyreports.get(i).getUserID());
				dailyReportDTO.setUserName(user2.getUsername());
				Project project1 = new Project();
				project1 = projectService.getProjectById(dailyreports.get(i)
						.getProjectID());
				dailyReportDTO.setProjectName(project1.getProjectName());
				dailyReportDTO.setArrangement(dailyreports.get(i)
						.getArrangement());
				dailyReportDTO.setFinishedContent(dailyreports.get(i)
						.getFinishedContent());
				dailyReportDTO.setDate(dailyreports.get(i).getDate());
				projectdailyreports.add(dailyReportDTO);
			}
		}

		session.setAttribute("Projectdailyreports", projectdailyreports);
		return "redirect:/project/findProject/dailyReport";
	}

	@RequestMapping("/weeklyReport")
	public String weeklyReport() {
		return "redirect:/project/findProject/weeklyReport";
	}

	// 添加日报
	@RequestMapping(value = "/addDailyReport", method = RequestMethod.POST)
	public String addDailyReport(Dailyreport dailyreport,
			HttpServletRequest request, HttpSession session) throws Exception {
		int pID = Integer.parseInt((String) request.getParameter("dailypID"));
		User user = (User) session.getAttribute("user");
		// 获得今天的日期
		String todaydate = DateTimeUtil.getDate();
		// 1为存在，-1为数据库不存在
		int choose = reportService.findTodayDailyReport(todaydate,
				user.getId(), pID);
		if (choose < 0) {
			reportService.addDailyReport(dailyreport, user.getId(), pID,
					todaydate);
		} else {
			dailyreport.setDate(todaydate);
			dailyreport.setProjectID(pID);
			dailyreport.setUserID(user.getId());
			reportService.updateDailyReport(dailyreport);
		}

		return "redirect:/report/dailyReport";
	}

	// 填写添加周报
	@RequestMapping(value = "/addWeeklyReport", method = RequestMethod.POST)
	public String addWeeklyReport(Weeklyreport weeklyreport,
			HttpServletRequest request, HttpSession session) throws Exception {

		String weeklyReportDate = DateTimeUtil.weeklyReportDate();
		User user = (User) session.getAttribute("user");
		int pID = Integer.parseInt((String) session.getAttribute("pID"));

		// 1为存在 -1为不存在
		int choose = reportService.findTodayWeeklyReport(weeklyReportDate,
				user.getId(), pID);
		if (choose < 0) {
			reportService.addWeeklyReport(weeklyreport, weeklyReportDate,
					user.getId(), pID);
		} else {
			weeklyreport.setUserID(user.getId());
			weeklyreport.setProjectID(pID);
			weeklyreport.setWeekDate(weeklyReportDate);
			reportService.updateWeeklyReport(weeklyreport);

		}

		return "redirect:/report/changeWeek/weeklyReport2?n=0";
	}

	@RequestMapping(value = "/changeWeek/{page}")
	public String changeWeek(HttpSession session, HttpServletRequest request,
			@PathVariable("page") String page) throws Exception {
		User user = (User) session.getAttribute("user");
		String projectID = (String) session.getAttribute("pID");
		Weeklyreport weeklyreport = new Weeklyreport();
		List<Weeklyreport> weeklyreports = new ArrayList<>();
		weeklyreports = reportService.getWeeklyReportByUserIDAndProjectID(
				user.getId(), Integer.parseInt(projectID));
		int num = weeklyreports.size() - 1;
		int n = 0;
		String temp = (String) request.getParameter("n");// -1为前一周 1为后一周
		n = Integer.parseInt(temp);

		if (n < 0 && num > 0) {
			num = num - 1;
		} else if (n > 0 && num < weeklyreports.size() - 1) {
			num = num + 1;
		} else {
			num = weeklyreports.size() - 1;
		}
		weeklyreport = weeklyreports.get(num);
		session.setAttribute("wrYear",
				weeklyreport.getWeekDate().substring(0, 4));
		session.setAttribute(
				"wrWeek",
				weeklyreport.getWeekDate().substring(4,
						weeklyreport.getWeekDate().length()));
		session.setAttribute("wrProblem", weeklyreport.getProblem());
		session.setAttribute("wrSummary", weeklyreport.getSummary());
		session.setAttribute("wrSolution", weeklyreport.getSolution());
		session.setAttribute("wrNextPlan", weeklyreport.getNextPlan());
		session.setAttribute("wrFutureSolution",
				weeklyreport.getFutureSolution());

		int year = Integer.valueOf(weeklyreport.getWeekDate().substring(0, 4));
		int week = Integer.valueOf(weeklyreport.getWeekDate().substring(4,
				weeklyreport.getWeekDate().length()));
		String weekTime = DateTimeUtil.getTime1(year, week);
		session.setAttribute("weekTime", weekTime);

		return "redirect:/project/findProject/" + page;
	}

	// 老师页面显示日报
	@RequestMapping(value = "/findAllDailyReport")
	public String findAllDailyReport(Model model, HttpSession session)
			throws Exception {

		List<Dailyreport> dailyreports = new ArrayList<>();
		List<DailyReportDTO> dailyReportDTOs = new ArrayList<>();

		dailyreports = reportService.findAllDailyReport();

		User user = new User();
		Project project = new Project();
		for (int i = dailyreports.size() - 1; i >= 0; i--) {
			DailyReportDTO dailyReportDTO = new DailyReportDTO();
			dailyReportDTO.setDate(dailyreports.get(i).getDate());
			dailyReportDTO.setArrangement(dailyreports.get(i).getArrangement());
			dailyReportDTO.setFinishedContent(dailyreports.get(i)
					.getFinishedContent());
			user = userService.getUserById(dailyreports.get(i).getUserID());
			dailyReportDTO.setUserName(user.getUsername());
			project = projectService.getProjectById(dailyreports.get(i)
					.getProjectID());
			dailyReportDTO.setProjectName(project.getProjectName());
			dailyReportDTOs.add(dailyReportDTO);
		}

		for (int i = 0; i < dailyReportDTOs.size(); i++) {
			for (int j = i + 1; j < dailyReportDTOs.size(); j++) {
				if (dailyReportDTOs.get(i).getDate()
						.equals(dailyReportDTOs.get(j).getDate())
						&& dailyReportDTOs.get(i).getUserName()
								.equals(dailyReportDTOs.get(j).getUserName())) {
					dailyReportDTOs.remove(dailyReportDTOs.get(i));
					i--;
				}
				break;
			}
		}
		model.addAttribute("dailyreports", dailyReportDTOs);

		return "dailyReportView";
	}

	@RequestMapping(value = "/findAllWeeklyReport")
	public String findAllWeeklyReport(Model model) throws Exception {
		List<Weeklyreport> weeklyreports = new ArrayList<>();
		List<WeeklyReportDTO> weeklyReportDTOs = new ArrayList<>();
		List<WeeklyReportDTO> weeklyReportDTOs2 = new ArrayList<>();
		weeklyreports = reportService.findAllWeeklyReport();
		User user = new User();
		Project project = new Project();
		for (Weeklyreport weeklyReport : weeklyreports) {
			WeeklyReportDTO weeklyReportDTO = new WeeklyReportDTO();
			String date = weeklyReport.getWeekDate().substring(0, 4) + "年第"
					+ weeklyReport.getWeekDate().substring(4);

			user = userService.getUserById(weeklyReport.getUserID());
			project = projectService
					.getProjectById(weeklyReport.getProjectID());
			weeklyReportDTO.setDate(date);
			weeklyReportDTO.setProjectName(project.getProjectName());
			weeklyReportDTO.setUserName(user.getUsername());

			weeklyReportDTO.setUserID(weeklyReport.getUserID());
			weeklyReportDTO.setProjectID(weeklyReport.getProjectID());
			weeklyReportDTOs.add(weeklyReportDTO);
			weeklyReportDTOs2.add(weeklyReportDTO);
		}

		for (int i = 0; i < weeklyReportDTOs.size(); i++) {
			for (int j = 0; j < weeklyReportDTOs2.size(); j++) {
				if (weeklyReportDTOs.get(i).getUserID() == weeklyReportDTOs2
						.get(j).getUserID()
						&& weeklyReportDTOs.get(i).getProjectID() == weeklyReportDTOs2
								.get(j).getProjectID()) {
					if (Integer.parseInt(weeklyReportDTOs.get(i).getDate()
							.substring(6)) >= Integer
							.parseInt(weeklyReportDTOs2.get(j).getDate()
									.substring(6))) {
						continue;
					} else {
						weeklyReportDTOs.remove(weeklyReportDTOs.get(i));
						i--;
						break;
					}
				}
			}
		}

		model.addAttribute("Weeklyreports", weeklyReportDTOs);
		return "weeklyReportView";
	}

	@RequestMapping("/weeklyReportViewSingle")
	public String weeklyReportViewSingle(HttpServletRequest request,
			HttpSession session) throws Exception {
		String cuid = (String) request.getParameter("cuid");
		String cpid = (String) request.getParameter("cpid");

		if (cpid == null || cpid == "") {
			cuid = "1000000";
			cpid = "1000000";
		}
		int uid = Integer.parseInt(cuid);
		int pid = Integer.parseInt(cpid);
		if (uid == 1000000) {
			uid = UID;
			pid = PID;
		}
		UID = uid;
		PID = pid;

		User user = new User();
		user = userService.getUserById(UID);
		session.setAttribute("clickUser", user.getUsername());
		Project project = new Project();
		project = projectService.getProjectById(PID);
		session.setAttribute("clickProject", project.getProjectName());

		/*
		 * User user = (User) session.getAttribute("user"); String projectID =
		 * (String) session.getAttribute("pID");
		 */
		Weeklyreport weeklyreport = new Weeklyreport();
		List<Weeklyreport> weeklyreports = new ArrayList<>();
		weeklyreports = reportService.getWeeklyReportByUserIDAndProjectID(uid,
				pid);

		int num = weeklyreports.size() - 1;
		int n = 0;
		String temp = (String) request.getParameter("n");// -1为前一周 1为后一周
		n = Integer.parseInt(temp);

		if (n < 0 && num > 0) {
			num = num - 1;
		} else if (n > 0 && num < weeklyreports.size() - 1) {
			num = num + 1;
		} else {
			num = weeklyreports.size() - 1;
		}
		weeklyreport = weeklyreports.get(num);
		session.setAttribute("showYear",
				weeklyreport.getWeekDate().substring(0, 4));
		session.setAttribute(
				"showWeek",
				weeklyreport.getWeekDate().substring(4,
						weeklyreport.getWeekDate().length()));
		session.setAttribute("showProblem", weeklyreport.getProblem());
		session.setAttribute("showSummary", weeklyreport.getSummary());
		session.setAttribute("showSolution", weeklyreport.getSolution());
		session.setAttribute("showNextPlan", weeklyreport.getNextPlan());
		session.setAttribute("showFutureSolution",
				weeklyreport.getFutureSolution());

		int year = Integer.valueOf(weeklyreport.getWeekDate().substring(0, 4));
		int week = Integer.valueOf(weeklyreport.getWeekDate().substring(4,
				weeklyreport.getWeekDate().length()));
		String weekTime = DateTimeUtil.getTime1(year, week);
		session.setAttribute("weekTime", weekTime);

		return "weeklyReportViewSingle";
	}

	/**
	 * 获取提交报表数量
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getReportCount")
	public String getReportCount(HttpSession session, Model model)
			throws Exception {

		// 获取应提交的日报数量
		Date date = new Date();
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = sdfDate.format(date);
		int DailyReportCount = reportService.getDailyReportCount(nowDate);
		session.setAttribute("DailyReportCount", DailyReportCount);

		// 获取应提交的周报数量
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyw");
		String weekDate = sdf.format(date);

		int WeeklyReportCount = reportService.getWeeklyReportCount(weekDate);
		session.setAttribute("WeeklyReportCount", WeeklyReportCount);

		// 获取应提交报表的总数量
		List<User> users = userService.findMemberUser();
		int sum = 0;
		for (User u : users) {
			List<Groups> groups = groupService.findMyGroups(String.valueOf(u
					.getId()));
			for (Groups g : groups) {
				List<Project> myprojects = projectService.findMyProjects(g
						.getId());
				sum += myprojects.size();
			}
		}
		session.setAttribute("ReportCount", sum);

		// 显示用户列表
		List<User> users2 = userService.findAllUser();
		List<UserDTO> userDTOs = new ArrayList<>();
		for (User user : users2) {
			UserDTO userDTO = new UserDTO();
			List<Role> roles = userService.getRoleByUserId(user.getId());
			if (roles.get(0).getId() == 3) {
				userDTO.setAccount(user.getAccount());
				userDTO.setUsername(user.getUsername());
				userDTO.setId(user.getId());

				userDTOs.add(userDTO);
			}

		}
		model.addAttribute("showusers", userDTOs);

		return "statistics";
	}

	@RequestMapping("/showDailyReportSingle/{page}")
	public String showDailyReportSingle(HttpSession session,
			@PathVariable("page") String page, Model model,
			HttpServletRequest request) throws Exception {
		User user = (User) session.getAttribute("user");
		// 格外加左边列表周报的项目展示
		String userID = Integer.toString(user.getId());
		List<Groups> groups2 = groupService.findMyGroups(userID);
		// 显示集
		List<Project> myAllProject = new ArrayList<>();
		for (Groups group : groups2) {
			List<Project> myprojects = projectService.findMyProjects(group
					.getId()); // group.getId()
			for (Project project : myprojects) {
				myAllProject.add(project);
			}
		}
		model.addAttribute("myprojects", myAllProject);
		List<DailyReportDTO> projectdailyreports = new ArrayList<>();
		List<Dailyreport> showdailyreports = new ArrayList<>();
		showdailyreports = reportService.findAllDailyReport();
		// 把查找到的所有日报汇总传给前端
		for (int i = showdailyreports.size() - 1; i >= 0; i--) {
			DailyReportDTO dailyReportDTO = new DailyReportDTO();

			User user2 = new User();
			user2 = userService
					.getUserById(showdailyreports.get(i).getUserID());
			dailyReportDTO.setUserName(user2.getUsername());
			Project project1 = new Project();
			project1 = projectService.getProjectById(showdailyreports.get(i)
					.getProjectID());
			dailyReportDTO.setProjectName(project1.getProjectName());
			dailyReportDTO.setArrangement(showdailyreports.get(i)
					.getArrangement());
			dailyReportDTO.setFinishedContent(showdailyreports.get(i)
					.getFinishedContent());
			dailyReportDTO.setDate(showdailyreports.get(i).getDate());
			projectdailyreports.add(dailyReportDTO);
		}

		session.setAttribute("showdailyreports", projectdailyreports);
		List<Role> roles = userService.getRoleByUserId(user.getId());
		if (roles.get(0).getId() == 3) {
			// 组长查看周报
			Weeklyreport weeklyreport = new Weeklyreport();
			List<Weeklyreport> weeklyreports = new ArrayList<>();
			int pid = Integer.valueOf(request.getParameter("pid"));

			weeklyreports = reportService.getWeeklyReportByUserIDAndProjectID(
					user.getId(), pid);

			int num = weeklyreports.size() - 1;
			int n = 0;
			String temp = (String) request.getParameter("n");// -1为前一周 1为后一周
			n = Integer.parseInt(temp);

			if (n < 0 && num > 0) {
				num = num - 1;
			} else if (n > 0 && num < weeklyreports.size() - 1) {
				num = num + 1;
			} else {
				num = weeklyreports.size() - 1;
			}
			weeklyreport = weeklyreports.get(num);
			session.setAttribute("showYear", weeklyreport.getWeekDate()
					.substring(0, 4));
			session.setAttribute("showWeek", weeklyreport.getWeekDate()
					.substring(4, weeklyreport.getWeekDate().length()));
			session.setAttribute("showProblem", weeklyreport.getProblem());
			session.setAttribute("showSummary", weeklyreport.getSummary());
			session.setAttribute("showSolution", weeklyreport.getSolution());
			session.setAttribute("showNextPlan", weeklyreport.getNextPlan());
			session.setAttribute("showFutureSolution",
					weeklyreport.getFutureSolution());

			int year = Integer.valueOf(weeklyreport.getWeekDate().substring(0,
					4));
			int week = Integer.valueOf(weeklyreport.getWeekDate().substring(4,
					weeklyreport.getWeekDate().length()));
			String weekTime = DateTimeUtil.getTime1(year, week);
			session.setAttribute("weekTime", weekTime);
		}
		return page;
	}
}
