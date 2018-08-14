package com.report.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.report.dto.UserDTO;
import com.report.mapper.GroupMapper;
import com.report.mapper.UserMapper;
import com.report.pojo.Role;
import com.report.pojo.User;
import com.report.service.GroupService;
import com.report.service.UserService;

/**
 * ClassName: UserServiceImpl <br/>
 * Function: 处理与用户信息相关的业务逻辑. <br/>
 * 
 * @author Vera Jiang
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private GroupService groupService;

	// 添加用户
	public void addUser(User user, String roleId) throws Exception {
		String md5pass = DigestUtils.md5Hex(user.getPassword());
		user.setPassword(md5pass);
		userMapper.addUser(user);// 添加用户信息到数据库
		user = getUserByAccount(user.getAccount());// 从数据库获取用户id

		String[] roleIds = roleId.split(",");
		for (String rid : roleIds) {
			int _rid = Integer.parseInt(rid);
			addUserRole(user.getId(), _rid); // 给用户添加角色
		}
		insertUser(user.getId());
	}

	// 添加用户角色
	public void addUserRole(int userId, int roleId) throws Exception {
		userMapper.addUserRole(userId, roleId);
	}
	
	//添加用户至其他小组
	public void insertUser(int userId) throws Exception {
		groupService.insertUser(userId, 1);
	}

	// 获取全部用户
	public List<User> findAllUser() throws Exception {
		return userMapper.findAllUser();
	}

	// 获取用户数量
	public int getUserCount() throws Exception {

		return userMapper.getUserCount();
	}

	// 根据用户id获取用户
	public User getUserById(int id) throws Exception {

		return userMapper.getUserById(id);
	}

	// 根据用户account获取用户
	public User getUserByAccount(String account) throws Exception {
		return userMapper.getUserByAccount(account);
	}

	// 根据用户id获取所有角色
	public ArrayList<Role> getRoleByUserId(int id) throws Exception {

		ArrayList<Role> roles = userMapper.getRoleByUserId(id);
		return roles;
	}

	// 根据帐号和密码获取用户
	public User getUserByAccountAndPass(User user) throws Exception {
		String md5pass = DigestUtils.md5Hex(user.getPassword());
		user.setPassword(md5pass);

		return userMapper.getUserByAccountAndPass(user);
	}

	// 获取全部用户
	public List<User> getUserList(int intPage, int number) throws Exception {

		PageHelper.startPage(intPage, number);
		return userMapper.findAllUser();
	}

	// 获取全部用户
	public List<User> getUserList() throws Exception {
		return userMapper.findAllUser();
	}

	// 更改用户密码
	public void updatePassword(String pass, int id) throws Exception {
		String md5pass = DigestUtils.md5Hex(pass);
		userMapper.updatePassword(md5pass, id);
	}

	// 删除用户
	public void delUser(int id) {
		userMapper.delUser(id);
	}

	// 需要提交报表的成员
	public List<User> findMemberUser() {

		return userMapper.findMemberUser();
	}

	//获取成员提交报表
	public List<UserDTO> findMemberUserReport(){
		List<User> lUsers = userMapper.findMemberUser();
		List<UserDTO> uDtos = new ArrayList<>();
		for (int i = 0; i < lUsers.size(); ++i) {
			User user = lUsers.get(i);
			UserDTO userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setAccount(user.getAccount());
			userDTO.setUsername(user.getUsername());
			
//			userDTO.setDaily("<a href=\"statistics/memberStatistics/daily"  + "?id=" + user.getId() + "\">日报表分析</a>");
//			userDTO.setWeekly("<a href=\"statistics/memberStatistics/weekly"  + "?id=" + user.getId() + "\">周报表分析</a>");
			uDtos.add(userDTO);
		}
		
		
		return uDtos;
	}
	// 修改用户
	public void updateUser(User user) throws Exception {
		userMapper.updateUser(user);// 更新用户信息到数据库

	}

}
