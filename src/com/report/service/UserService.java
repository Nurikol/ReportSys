package com.report.service;

import java.util.ArrayList;
import java.util.List;

import com.report.dto.UserDTO;
import com.report.pojo.Role;
import com.report.pojo.User;

public interface UserService {
	// 添加用户
	void addUser(User user, String roleId) throws Exception;

	// 添加用户角色
	void addUserRole(int userId, int roleId) throws Exception;

	// 获取全部用户
	List<User> findAllUser() throws Exception;

	// 获取用户数量
	int getUserCount() throws Exception;

	// 根据用户id获取用户
	User getUserById(int id) throws Exception;

	// 根据用户account获取用户
	User getUserByAccount(String account) throws Exception;

	// 根据用户id获取所有角色
	ArrayList<Role> getRoleByUserId(int id) throws Exception;

	// 根据帐号和密码获取用户
	User getUserByAccountAndPass(User user) throws Exception;

	// 获取指定分页的全部用户
	List<User> getUserList(int intPage, int number) throws Exception;

	// 获取全部用户
	List<User> getUserList() throws Exception;

	// 更改用户密码
	void updatePassword(String pass, int id) throws Exception;

	// 删除用户
	void delUser(int id);

	// 更新用户
	void updateUser(User user) throws Exception;

	// 需要提交报表的成员
	List<User> findMemberUser();

	// 获取成员提交报表
	public List<UserDTO> findMemberUserReport() throws Exception;
}
