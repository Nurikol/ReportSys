package com.report.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.report.dto.MeetingDTO;
import com.report.dto.RoleDTO;
import com.report.dto.GroupDTO;
import com.report.dto.memberDTO;
import com.report.pojo.Groups;
import com.report.pojo.Project;
import com.report.pojo.Role;
import com.report.pojo.User;
import com.report.service.GroupService;
import com.report.service.ProjectService;
import com.report.service.UserService;

/**
 * 分组控制层
 * 
 * @author Vera Jiang
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/group")
public class groupController {
	@Autowired
	private GroupService groupService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProjectService projectService;

	// 添加分组至数据库
	@RequestMapping(value = "/addGroup", method = RequestMethod.POST)
	public String addGroup(Groups group, HttpServletResponse response,
			Model model) throws Exception {
		groupService.addGroup(group);
		return "redirect:/group/getUserListInGroup";
	}

	// 获取分组列表
	@RequestMapping("/getGroupList")
	public String getGroupList(Model model, HttpServletResponse response)
			throws Exception {
		List<Groups> group = groupService.findAllGroup();
		model.addAttribute("groups", group);
		return "showGroup";
	}

	// 根据角色获取用户列表
	@RequestMapping(value = "/getUserListInGroup")
	public String getUserListInGroups(Model model) throws Exception {
		List<RoleDTO> roleDTOs = new ArrayList<RoleDTO>();
		List<User> users = userService.findAllUser();
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			RoleDTO roleDTO = new RoleDTO();
			roleDTO.setId(user.getId());
			roleDTO.setName(user.getUsername());
			// 获取用户角色
			ArrayList<Role> roles = userService.getRoleByUserId(user.getId());
			// 将角色传给前端页面
			Role role = roles.get(0);
			roleDTO.setRoleid(role.getId());
			roleDTOs.add(roleDTO);

		}
		model.addAttribute("users", roleDTOs);
		// List<User> user=userService.findAllUser();
		// model.addAttribute("users", user);
		return "createGroup";
	}

	// 删除小组
	@RequestMapping(value = "/deleteGroup")
	public String deleteGroup(HttpServletRequest request)
			throws NumberFormatException, Exception {
		String groupId = request.getParameter("groupId");
		groupService.deleteGroup(Integer.parseInt(groupId));
		return "redirect:/group/getGroupList";
	}

	// 获取当前用户所在小组
	@RequestMapping(value = "/findMyGroups")
	public String findMyGroups(HttpSession session, Model model)
			throws Exception {

		User user = (User) session.getAttribute("user");
		List<Role> roles = userService.getRoleByUserId(user.getId());
		List<Role> role2 = new ArrayList<Role>();

		if (roles.size()>1&&roles.get(1).getId() == 4) {
			Role role = roles.get(1);
			role2.add(role);
		}
		else role2=roles;

		model.addAttribute("roles", role2);
		String userId = Integer.toString(user.getId());
		List<Groups> groups = groupService.findMyGroups(userId);
		List<Project> myAllProject1 = new ArrayList<>();

		// 当学生是小组组长时

		List<memberDTO> memberDTOs=new ArrayList<memberDTO>();
		for (int i = 0; i < roles.size(); i++) {
			if (roles.get(i).getId() == 4) {
				for (Groups g : groups) {
					String leader = g.getLeader();
					String[] leaders = leader.split(",");
					String id = g.getIds();
					String[] ids = id.split(",");
					int l = leaders.length;
					List<User> users = new ArrayList<User>();
					for (int j = l; j <= ids.length-l; j++) {
						users.add(userService.getUserById(Integer
								.parseInt(ids[j])));
					}
					memberDTO MemberDTO=new memberDTO();
					MemberDTO.setGroupid(g.getId());
					MemberDTO.setUsers(users);
					memberDTOs.add(MemberDTO);
				}
			}
		}
		
		/*for (int k = 0; k < users.size(); k++) {
			User mdi = users.get(i);
			for (int j = k + 1; j < users.size(); j++) {
				User mdj = users.get(j);
				if (mdi.getId() == (mdj.getId())) {
					users.remove(j);
					j--;
				}
			}
		}*/
		model.addAttribute("member", memberDTOs);
		for (Groups group : groups) {
			List<Project> myprojects = projectService.findMyProjects(group
					.getId());
			for (Project project : myprojects) {
				myAllProject1.add(project);
			}
		}

		model.addAttribute("groups", groups);
		model.addAttribute("project", myAllProject1);

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

		return "group";
	}

	// 根据角色获取用户列表(在更新小组界面中)
	@RequestMapping(value = "/getUserListInUpdateGroup")
	public String getUserListInUpdateGroups(Model model,
			HttpServletRequest request) throws Exception {
		List<RoleDTO> roleDTOs = new ArrayList<RoleDTO>();
		List<GroupDTO> groupDTOsLeader = new ArrayList<GroupDTO>();
		List<GroupDTO> groupDTOsMember = new ArrayList<GroupDTO>();
		List<User> users = userService.findAllUser();
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			RoleDTO roleDTO = new RoleDTO();
			roleDTO.setId(user.getId());
			roleDTO.setName(user.getUsername());
			// 获取用户角色
			ArrayList<Role> roles = userService.getRoleByUserId(user.getId());
			// 将角色传给前端页面
			Role role = roles.get(0);
			roleDTO.setRoleid(role.getId());
			roleDTOs.add(roleDTO);

		}
		model.addAttribute("users", roleDTOs);

		// 获取已选择的用户的id
		String groupId = request.getParameter("groupId");
		Groups group = groupService.findThisGroup(Integer.parseInt(groupId));

		String leader = group.getLeader();
		String[] leaders = leader.split(",");
		String ids = group.getIds();
		String[] idStrings = ids.split(",");
		String member = group.getMember();
		String[] members = member.split(",");
		String[] leaderIds = new String[leaders.length];
		String[] memberIds = new String[idStrings.length - leaders.length];

		int i = 0, j = 0;
		for (; i < leaders.length; i++) {
			GroupDTO groupDto = new GroupDTO();
			leaderIds[i] = idStrings[i];
			groupDto.setLeaderId(leaderIds[i]);
			groupDto.setLeaderName(leaders[i]);
			groupDTOsLeader.add(groupDto);
		}
		for (; j < idStrings.length - leaders.length; j++) {
			GroupDTO groupDto = new GroupDTO();
			memberIds[j] = idStrings[i];
			i++;
			groupDto.setMemberId(memberIds[j]);
			groupDto.setMemberName(members[j]);
			groupDTOsMember.add(groupDto);
		}

		model.addAttribute("leaders", groupDTOsLeader);
		model.addAttribute("members", groupDTOsMember);

		return "updateGroup";
	}

	// 更新小组信息
	@RequestMapping(value = "/updateGroup", method = RequestMethod.POST)
	public String updateGroup(Groups group, HttpServletRequest request)
			throws Exception {
		groupService.updateGroups(group);
		return "redirect:/group/getGroupList";
	}
}
