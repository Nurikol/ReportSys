package com.report.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.report.pojo.Notice;
import com.report.service.NoticeService;

/**
 * className:NoticeController
 * fuction:系统公告控制层
 * @author Vera Jiang
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/notice")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping(value="/addNotice")
	public String addNotice(Notice notice) throws Exception{
		noticeService.addNotice(notice);
		return "redirect:/notice/findAllNotice/admin";	
	}
	
	@RequestMapping(value="/findAllNotice/{page}")
	public String findAllNotice(@PathVariable("page")String page,Model model) throws Exception{
		List<Notice> notices= noticeService.findAllNotices();
		model.addAttribute("notices", notices);
		return page;
	}

	@RequestMapping("/deleteNotice")
	public String deleteNotice(HttpServletRequest request) throws NumberFormatException, Exception{
		String id=request.getParameter("id");
		noticeService.deleteNotice(Integer.parseInt(id));
		return "redirect:/notice/findAllNotice/admin";
	}
}
