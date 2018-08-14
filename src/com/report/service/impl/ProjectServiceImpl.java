package com.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.report.mapper.ProjectMapper;
import com.report.pojo.Project;
import com.report.service.ProjectService;

/**
 * ClassName: ProjectServiceImpl
 * Function: 处理与项目相关的应用逻辑
 * @author Ironman
 * date: 2017年7月19日
 */
@Service("ProjectrService")
public class ProjectServiceImpl implements ProjectService{
	
	@Autowired
	private ProjectMapper projectMapper;
	
	@Override
	public void addProject(Project project) throws Exception {
		projectMapper.addProject(project);
	}

	@Override
	public List<Project> findAllProject() throws Exception {
		return projectMapper.findAllProject();
	}

	@Override
	public void delProject(int projectID) throws Exception {
	     projectMapper.delProject(projectID);
	}

	@Override
	public List<Project> findMyProjects(int GroupsID) throws Exception {
		
		return projectMapper.findMyProjects(GroupsID);
	}
	
	@Override
	public Project getProjectById(int projectID) throws Exception {
		return projectMapper.getProjectById(projectID);
	}
}
	
