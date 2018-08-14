package com.report.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.report.pojo.Groups;

public interface GroupMapper {
	// 添加分组
	void addGroup(Groups group) throws Exception;

	// 查询全部分组
	List<Groups> findAllGroup() throws Exception;

	// 获取分组数量
	int getGroupCount() throws Exception;

	// 查询用户所在的小组
	List<Groups> findMyGroups(String id) throws Exception;

	// 删除当前显示小组
	void deleteGroup(int id) throws Exception;

	// 更新小组信息
	void updateGroups(Groups group) throws Exception;

	// 查询当前小组
	Groups findThisGroup(int id) throws Exception;

	// 根据项目负责小组ID 查询小组信息
	Groups getGroupByID(@Param("id") int id) throws Exception;
}
