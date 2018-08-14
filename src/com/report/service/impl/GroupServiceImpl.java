package com.report.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.report.mapper.GroupMapper;
import com.report.mapper.UserMapper;
import com.report.pojo.Groups;
import com.report.pojo.Role;
import com.report.pojo.User;
import com.report.service.GroupService;
import com.report.service.UserService;

/**
 * className:GroupSeriviceImpl fuction：实现GroupService接口
 * 
 * @author Vera Jiang
 *
 */
@Service("groupService")
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupMapper groupMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserService userService;

	@Override
	public void addGroup(Groups group) throws Exception {
		String ids = "";

		// 将id 变成 用户名
		String leader = group.getLeader();
		String[] leaders = leader.split(",");
		StringBuffer sb1 = new StringBuffer();
		for (String l : leaders) {
			ids += l + ",";
			int _userId = Integer.parseInt(l);
			User _user = userMapper.getUserById(_userId);
			String _username = _user.getUsername();
			sb1.append(_username + ",");
			ArrayList<Role> roles = userService.getRoleByUserId(_userId);
			int i = 0;
			Role role = roles.get(i);
			while (i < roles.size()) {
				role = roles.get(i);
				if (role.getId() == 4) {
					break;
				} else
					i++;
			}
			if (role.getId() != 4) {
				userService.addUserRole(_user.getId(), 4);
			}
		}
		String s1 = sb1.toString();
		leader = s1.substring(0, s1.length() - 1);
		group.setLeader(leader);

		// 将id 变成 用户名
		String member = group.getMember();
		String[] members = member.split(",");
		StringBuffer sb2 = new StringBuffer();
		for (String m : members) {
			ids += m + ",";
			int _userId = Integer.parseInt(m);
			User _user = userMapper.getUserById(_userId);
			String _username = _user.getUsername();
			sb2.append(_username + ",");
		}

		String s2 = sb2.toString();
		member = s2.substring(0, s2.length() - 1);
		group.setMember(member);

		group.setIds(ids.substring(0, ids.length() - 1));

		groupMapper.addGroup(group);

	}

	// 从数据库中获取分组信息
	@Override
	public List<Groups> findAllGroup() throws Exception {
		return groupMapper.findAllGroup();
	}

	// 获取分组数量
	public int getGroupCount() throws Exception {

		return groupMapper.getGroupCount();
	}

	// 查询用户所在的小组
	public List<Groups> findMyGroups(String id) throws Exception {
		return groupMapper.findMyGroups(id);
	}

	@Override
	public void deleteGroup(int id) throws Exception {
		groupMapper.deleteGroup(id);
	}

	// 更新小组信息
	@Override
	public void updateGroups(Groups group) throws Exception {
		String ids = "";

		// 将小组组长id 变成 用户名
		String leader = group.getLeader();
		String[] leaders = leader.split(",");
		// 判断前端传来数据是否有重复值
		for (int i = 0; i < leaders.length; i++) {
			for (int j = i + 1; j < leaders.length; j++) {
				if (leaders[j].equals(leaders[i])) {
					leaders = deleteElement(leaders, leaders[j], j);
					leaders = Arrays.copyOf(leaders, leaders.length - 1);
				} else {
					continue;
				}
			}
		}
		StringBuffer sb1 = new StringBuffer();
		for (String l : leaders) {
			ids += l + ",";
			int _userId = Integer.parseInt(l);
			User _user = userMapper.getUserById(_userId);
			String _username = _user.getUsername();
			sb1.append(_username + ",");
			ArrayList<Role> roles = userService.getRoleByUserId(_userId);
			int i = 0;
			Role role = roles.get(i);
			while (i < roles.size()) {
				role = roles.get(i);
				if (role.getId() == 4) {
					break;
				} else
					i++;
			}
			if (role.getId() != 4) {
				userService.addUserRole(_user.getId(), 4);
			}
		}
		String s1 = sb1.toString();
		leader = s1.substring(0, s1.length() - 1);
		group.setLeader(leader);

		// 将小组成员id 变成 用户名
		String member = group.getMember();
		String[] members = member.split(",");
		// 判断前端传来数据是否有重复值
		for (int i = 0; i < members.length; i++) {
			for (int j = i + 1; j < members.length; j++) {
				if (members[j].equals(members[i])) {
					members = deleteElement(members, members[j], j);
					members = Arrays.copyOf(members, members.length - 1);
				} else {
					continue;
				}
			}
		}
		StringBuffer sb2 = new StringBuffer();
		for (String m : members) {
			ids += m + ",";
			int _userId = Integer.parseInt(m);
			User _user = userMapper.getUserById(_userId);
			String _username = _user.getUsername();
			sb2.append(_username + ",");
		}

		String s2 = sb2.toString();
		member = s2.substring(0, s2.length() - 1);
		group.setMember(member);

		group.setIds(ids.substring(0, ids.length() - 1));

		groupMapper.updateGroups(group);
	}

	// 向小组中插入成员
	public void insertUser(int id, int GroupId) throws Exception {
		

		User user = userService.getUserById(id);
		Groups groups = groupMapper.findThisGroup(GroupId);
		
		String ids=groups.getIds();

		String member = groups.getMember();
		StringBuffer sb2 = new StringBuffer();
		sb2.append(member);
		sb2.append(",");
		sb2.append(user.getUsername());
		String s2 = sb2.toString();
		member = s2.substring(0, s2.length() );
		groups.setMember(member);
		
		StringBuffer sb3 = new StringBuffer();
		sb3.append(ids);
		sb3.append(",");
		sb3.append(String.valueOf(user.getId()));
		String s3 = sb3.toString();
		ids=s3.substring(0,s3.length());
		groups.setIds(ids);

		groupMapper.updateGroups(groups);
	}

	// 删除数组中重复元素
	public String[] deleteElement(String[] array, String a, int j) {
		String[] newArray = new String[array.length];
		int i = 0;
		for (; i < j; i++) {
			newArray[i] = array[i];
		}
		for (; j < array.length; j++) {
			if (!array[j].equals(a)) {
				newArray[i] = array[j];
				i++;
			}
		}
		newArray[array.length - 1] = "";
		return newArray;
	}

	// 查找当前小组信息
	@Override
	public Groups findThisGroup(int id) throws Exception {
		return groupMapper.findThisGroup(id);
	}

	// 查询用户所在的小组
	public Groups getGroupByID(int id) throws Exception {
		return groupMapper.getGroupByID(id);
	}
}