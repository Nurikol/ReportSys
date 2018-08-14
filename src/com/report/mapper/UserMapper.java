package com.report.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.report.pojo.Role;
import com.report.pojo.User;

public interface UserMapper {

	// 添加用户
	void addUser(User user) throws Exception;

	// 添加用户
	void addUserRole(@Param("userId") int userId, @Param("roleId") int roleId)
			throws Exception;

	// 获取全部用户
	List<User> findAllUser() throws Exception;

	// 获取用户数量
	int getUserCount() throws Exception;

	// 根据用户id获取用户
	User getUserById(int id) throws Exception;

	// 根据用户帐号获取用户
	User getUserByAccount(String account) throws Exception;

	// 根据用户id获取所有角色
	ArrayList<Role> getRoleByUserId(int id) throws Exception;

	// 根据帐号和密码获取用户
	User getUserByAccountAndPass(User user) throws Exception;

	// 更改用户密码
	void updatePassword(@Param("pass") String pass, @Param("id") int id)
			throws Exception;

	// 删除用户
	void delUser(int id);

	// 需要提交报表的成员
	List<User> findMemberUser();

	// 更新用户
	void updateUser(User user) throws Exception;
}
