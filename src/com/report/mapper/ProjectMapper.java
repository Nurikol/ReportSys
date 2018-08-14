package com.report.mapper;

import java.util.List;
import com.report.pojo.Project;

/**
 * ClassName: ProjectMapper Function: 对project类进行CRUD操作
 * 
 * @author Ironman
 */
public interface ProjectMapper {
	// 添加项目
	void addProject(Project project) throws Exception;

	// 查询全部并正确显示所有项目
	List<Project> findAllProject() throws Exception;

	// 删除项目
	void delProject(int projectID) throws Exception;

	// 查询小组负责的项目(一对多
	List<Project> findMyProjects(int GroupsID) throws Exception;

	// 根据项目id获取项目
	Project getProjectById(int projectID) throws Exception;
}
