package com.report.controller;

import java.util.ArrayList;
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

import com.report.dto.ProjectDTO;
import com.report.pojo.Groups;
import com.report.pojo.Notice;
import com.report.pojo.Project;
import com.report.pojo.User;
import com.report.pojo.Weeklyreport;
import com.report.service.GroupService;
import com.report.service.NoticeService;
import com.report.service.ProjectService;
import com.report.service.ReportService;
import com.report.service.UserService;
import com.report.util.DateTimeUtil;

/**
 * ClassName: ProjectController Function: 项目控制层
 * 
 * @author Ironman date: 2017年7月19日
 */
@Controller
@Scope("prototype")
@RequestMapping("/project")
public class ProjectController {
	@Autowired
	private GroupService groupService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private UserService userService;

	// 各个页面的跳转
	@RequestMapping("/createProject")
	public String createProject(Model model) throws Exception {
		// 把小组列表传给前台生成下拉框
		List<Groups> list = groupService.findAllGroup();
		model.addAttribute("list", list);
		return "createProject";
	}

	@RequestMapping("/showProject")
	public String showProject(Model model) throws Exception {
		List<Project> projects = projectService.findAllProject();
		List<ProjectDTO> projectDTOs = new ArrayList<>();
		for (Project project : projects) {
			ProjectDTO projectDTO = new ProjectDTO();
			int groupid = project.getGroupID();
			Groups groups = groupService.getGroupByID(groupid);
			projectDTO.setProjectID(project.getProjectID());
			projectDTO.setProjectName(project.getProjectName());
			projectDTO.setName(groups.getName());
			projectDTO.setLeader(groups.getLeader());
			projectDTO.setMember(groups.getMember());
			projectDTOs.add(projectDTO);
		}
		model.addAttribute("projects", projectDTOs);
		return "showProject";
	}

	// 添加项目
	@RequestMapping(value = "/addProject", method = RequestMethod.POST)
	public String addProject(Project project, Model model) throws Exception {
		/*
		 * //同前，在加入一个项目后仍然刷新下拉列表 List<Groups> list =
		 * groupService.findAllGroup(); model.addAttribute("list", list);
		 */
		projectService.addProject(project);

		return "redirect:/project/createProject";

	}

	// 删除项目
	@RequestMapping(value = "/deleteProject", method = RequestMethod.GET)
	public String deleteProject(HttpServletRequest request) throws Exception {
		// 从前端获得要删除的项目编号
		String pID = request.getParameter("pID");
		// 从数据库删除
		int projectID = Integer.parseInt(pID);

		projectService.delProject(projectID);
		return "redirect:/project/showProject";
	}

	// 周报页面显示项目
	@RequestMapping(value = "/weeklyProject", method = RequestMethod.GET)
	public String weeklyProject(HttpServletRequest request, Model model) {

		String clickPName = request.getParameter("clickPName");
		Project clickProject = new Project();
		clickProject.setProjectName(clickPName);
		model.addAttribute("clickProject", clickProject);
		return "redirect:/report/weeklyReport";
	}

	// 查找用户所在项目
	@RequestMapping(value = "/findProject/{page}")
	public String findProject(Model model, HttpSession session,
			@PathVariable("page") String page, HttpServletRequest request)
			throws Exception {
		User user = (User) session.getAttribute("user");
		// 得到当前用户全部小组
		String userID = String.valueOf(user.getId());
		List<Groups> groups = groupService.findMyGroups(userID);
		// 显示集
		List<Project> myAllProject = new ArrayList<>();
		// 查找每个小组对应的所有项目
		for (Groups group : groups) {
			List<Project> myprojects = projectService.findMyProjects(group
					.getId()); // group.getId()
			for (Project project : myprojects) {
				myAllProject.add(project);
			}
		}
		model.addAttribute("myprojects", myAllProject);
		// 获取公告列表
		List<Notice> notices = noticeService.findAllNotices();
		model.addAttribute("notices", notices);

		// 获得当前是第几周
		String weekNumber = String.valueOf(DateTimeUtil.getWeekNumber());
		request.setAttribute("weekNumber", weekNumber);
		// 获得今年的年数
		String yearNumber = String.valueOf(DateTimeUtil.getYearNumber());
		request.setAttribute("yearNumber", yearNumber);

		String pName = request.getParameter("clickPName");
		String projectID = request.getParameter("cID");
		if (pName != "" && pName != null) {
			session.setAttribute("pName", pName);
			session.setAttribute("pID", projectID);

			List<Weeklyreport> weeklyreports = new ArrayList<>();
			weeklyreports = reportService.getWeeklyReportByUserIDAndProjectID(
					user.getId(), Integer.parseInt(projectID));
			session.setAttribute("weeklyreports", weeklyreports);
			Weeklyreport weeklyreport = new Weeklyreport();
			if (weeklyreports.size() > 0) {
				weeklyreport = weeklyreports.get(weeklyreports.size() - 1);
				request.setAttribute("wrYear", weeklyreport.getWeekDate()
						.substring(0, 4));
				request.setAttribute("wrWeek", weeklyreport.getWeekDate()
						.substring(4, weeklyreport.getWeekDate().length()));
				request.setAttribute("wrProblem", weeklyreport.getProblem());
				request.setAttribute("wrSummary", weeklyreport.getSummary());
				request.setAttribute("wrSolution", weeklyreport.getSolution());
				request.setAttribute("wrNextPlan", weeklyreport.getNextPlan());
				request.setAttribute("wrFutureSolution",
						weeklyreport.getFutureSolution());
			}
			// 获得本周的日期范围
			String weekTime = DateTimeUtil.getWeekTime();
			request.setAttribute("weekTime", weekTime);

			String wrDate = weeklyreport.getWeekDate();
			String today = yearNumber + weekNumber;

			if (weeklyreports.size() > 0) {
				if (wrDate.equals(today)) {
					Weeklyreport weeklyreport2 = new Weeklyreport();
					weeklyreport2 = weeklyreports.get(weeklyreports.size() - 1);
					/*
					 * session.setAttribute("wrYear",
					 * weeklyreport2.getWeekDate().substring(0,4));
					 * session.setAttribute("wrWeek",
					 * weeklyreport2.getWeekDate()
					 * .substring(4,weeklyreport.getWeekDate().length()));
					 */
					request.setAttribute("wrProblemToday",
							weeklyreport2.getProblem());
					request.setAttribute("wrSummaryToday",
							weeklyreport2.getSummary());
					request.setAttribute("wrSolutionToday",
							weeklyreport2.getSolution());
					request.setAttribute("wrNextPlanToday",
							weeklyreport2.getNextPlan());
					request.setAttribute("wrFutureSolutionToday",
							weeklyreport2.getFutureSolution());
				} else {
					request.setAttribute("wrProblemToday", null);
					request.setAttribute("wrSummaryToday", null);
					request.setAttribute("wrSolutionToday", null);
					request.setAttribute("wrNextPlanToday", null);
					request.setAttribute("wrFutureSolutionToday", null);
				}
			}
		}

		// 显示周数和日期范围
		// 还没写（判断当前显示周数是否是最新的 如果是，隐藏右边箭头

		return page;
	}
}
