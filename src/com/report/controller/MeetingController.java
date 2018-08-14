package com.report.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.report.dto.MeetingDTO;
import com.report.dto.RoleDTO;
import com.report.pojo.Groups;
import com.report.pojo.Meeting;
import com.report.pojo.Project;
import com.report.pojo.Role;
import com.report.pojo.User;
import com.report.service.GroupService;
import com.report.service.MeetingService;
import com.report.service.ProjectService;
import com.report.service.UserService;

/**
 * className:MeetingController
 * fuction:会议记录控制层
 * @author Vera Jiang
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/meeting")
public class MeetingController {
	@Autowired
	private MeetingService meetingService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	UserService userService;

	// 添加会议记录至数据库
	@RequestMapping(value = "/addMeeting/{page}", method = RequestMethod.POST)
	public String addMeeting(Meeting meeting, HttpServletResponse response,@PathVariable("page")String page)
			throws Exception {
		meetingService.addMeeting(meeting);
		return "redirect:/meeting/findAllMeeting/"+page;
	}

	// 获取所有用户
	@RequestMapping(value = "/getUserListInMeeting/{page}")
	public String getUserListInMeeting(Model model, HttpSession session,@PathVariable("page")String page)
			throws Exception {
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
		List<RoleDTO> roleDTOs = new ArrayList<RoleDTO>();
		List<User> users = userService.findAllUser();
		for (int i = 0; i < users.size(); i++) {
			User user1 = users.get(i);
			RoleDTO roleDTO = new RoleDTO();
			roleDTO.setId(user1.getId());
			roleDTO.setName(user1.getUsername());
			// 获取用户角色
			ArrayList<Role> roles = userService.getRoleByUserId(user1.getId());
			// 将角色传给前端页面
			Role role = roles.get(0);
			roleDTO.setRoleid(role.getId());
			roleDTOs.add(roleDTO);

		}
		model.addAttribute("users", roleDTOs);
		// List<User> user=userService.findAllUser();
		// model.addAttribute("users", user);
		return page;
	}

	// 显示所有会议记录
	@RequestMapping(value = "/findAllMeeting/{page}")
	public String findAllMeeting(Model model, HttpSession session,@PathVariable("page")String page)
			throws Exception {
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
		// 从数据库中查找会议记录
		List<Meeting> meetings = meetingService.findAllMeetings();
		List<MeetingDTO> meetingDTOs = new ArrayList<MeetingDTO>();
		List<MeetingDTO> meetingDTOs1 = new ArrayList<MeetingDTO>();
		for (Meeting m : meetings) {

			MeetingDTO meetingDTO = new MeetingDTO();
			MeetingDTO meetingDTO1 = new MeetingDTO();
			// 会议开始时间
			Date md = (Date) m.getMeetingDate();
			// 会议记录填写时间
			Date ct = (Date) m.getCreateTime();
			// 按XX月XX日显示时间
			SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
			// 会议开始时间 月、日
			String md1 = sdf.format(md);
			// 会议记录时间 月、日
			String ct1 = sdf.format(ct);
			// 会议开始的周数
			SimpleDateFormat sdf_week = new SimpleDateFormat("第w周");
			String weekNumber = sdf_week.format(md);
			// 按XXXX年显示时间
			SimpleDateFormat sdf_year = new SimpleDateFormat("YYYY年");
			// 会议开始时间 年
			String md1_year = sdf_year.format(md);
			// 按XX月显示时间
			SimpleDateFormat sdf_month = new SimpleDateFormat("MM月");
			// 会议开始时间 月
			String md1_month = sdf_month.format(md);

			// 计算参加会议所有人数
			String members = m.getMeetingMember();
			String otherMembers = m.getOtherMember();
			String[] member = members.split(",");
			String[] otherMember = otherMembers.split(",");
			int number = member.length + otherMember.length;

			// 将数据加入meetingDTOs
			meetingDTO.setId(m.getId());
			meetingDTO.setTheme(m.getTheme());
			meetingDTO.setMeetingContent(m.getMeetingContent());
			meetingDTO.setMeetingMember(members);
			meetingDTO.setMeetingDate(md1);
			meetingDTO.setCreateDate(ct1);
			meetingDTO.setRecorder(m.getRecorder());
			meetingDTO.setNumbers(number);
			meetingDTO.setOtherMember(otherMembers);
			meetingDTO.setWeekNumber(weekNumber);
			meetingDTO.setMonth(md1_month);
			meetingDTO.setYear(md1_year);
			meetingDTOs.add(meetingDTO);

			// 将数据加入meetingDTOs1
			meetingDTO1.setMonth(md1_month);
			meetingDTO1.setYear(md1_year);
			meetingDTOs1.add(meetingDTO1);
		}
		List<MeetingDTO> ym = clearRepitition(meetingDTOs1);
		model.addAttribute("meetings", meetingDTOs);
		model.addAttribute("date", ym);

		return page;
	}

	// 根据角色获取用户列表，获取当前会议记录信息（在更新会议记录页面中）
	@RequestMapping(value = "/getUserListInUpdateMeeting/{page}")
	public String getUserListInUpdateMeeting(Model model, HttpSession session,HttpServletRequest request,@PathVariable("page")String page)
			throws Exception {
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
		List<RoleDTO> roleDTOs = new ArrayList<RoleDTO>();
		List<User> users = userService.findAllUser();
		for (int i = 0; i < users.size(); i++) {
			User user1 = users.get(i);
			RoleDTO roleDTO = new RoleDTO();
			roleDTO.setId(user1.getId());
			roleDTO.setName(user1.getUsername());
			// 获取用户角色
			ArrayList<Role> roles = userService.getRoleByUserId(user1.getId());
			// 将角色传给前端页面
			Role role = roles.get(0);
			roleDTO.setRoleid(role.getId());
			roleDTOs.add(roleDTO);

		}
		model.addAttribute("users", roleDTOs);
		
		//获取当前会议记录id
		int id=Integer.parseInt(request.getParameter("meetingID"));
		Meeting meeting=meetingService.findMeetingByid(id);
		
		//将当前会议记录信息添加到会话
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String meetingDate=sdf.format(meeting.getMeetingDate());
		String createDate=sdf.format(meeting.getCreateTime());
		String meetingMembers=meeting.getMeetingMember();
		String[] meetingMember=meetingMembers.split(",");
		session.setAttribute("id", id);
		session.setAttribute("theme", meeting.getTheme());
		session.setAttribute("meetingContent", meeting.getMeetingContent());
		session.setAttribute("otherMember", meeting.getOtherMember());
		session.setAttribute("meetingDate", meetingDate);
		session.setAttribute("createDate", createDate);
		session.setAttribute("meetingMember",meetingMember);
		session.setAttribute("recorder", meeting.getRecorder());
		
		return page;
	}

	// 修改选中会议记录
	@RequestMapping(value = "/updateMeeting/{page}", method = RequestMethod.POST)
	public String updateMeeting(Meeting meeting,HttpServletResponse response,@PathVariable("page")String page) throws Exception {
		meetingService.updateMeeting(meeting);
		return "redirect:/meeting/findAllMeeting/"+page;
	}

	//删除选中会议记录
	@RequestMapping("/deleteMeeting/{page}")
	public String deleteMeeting(HttpServletRequest request,@PathVariable("page")String page) throws Exception{
		String MeetingID=request.getParameter("id");
		
		meetingService.deleteMeeting(Integer.parseInt(MeetingID));
		
		return "redirect:/meeting/findAllMeeting/"+page;
	}
	
	// 清除数组中重复元素
	public List<MeetingDTO> clearRepitition(List<MeetingDTO> md) {

		for (int i = 0; i < md.size(); i++) {
			MeetingDTO mdi = md.get(i);
			for (int j = i + 1; j < md.size(); j++) {
				MeetingDTO mdj = md.get(j);
				if (mdi.getYear().equals(mdj.getYear())
						&& mdi.getMonth().equals(mdj.getMonth())) {
					md.remove(j);
					j--;
				}
			}
		}
		return md;
	}
}
