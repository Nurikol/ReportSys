package com.report.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.report.dto.UserDTO;
import com.report.pojo.Role;
import com.report.pojo.User;
import com.report.service.UserService;

/**
 * className:UserController function:用户控制层
 * 
 * @author Vera Jiang
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	// 各个页面的跳转
	@RequestMapping("/login")
	public String login() {
		return "pages-signin";
	}

	@RequestMapping("/student")
	public String student() {
		return "redirect:/project/findProject/student";
	}

	@RequestMapping("/main")
	public String main(HttpSession session, Model model) throws Exception {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			// 获取用户的角色
			ArrayList<Role> roles = userService.getRoleByUserId(user.getId());
			// 获取用户的主页

			Role role = roles.get(0);
			
			session.setAttribute("name",user.getUsername());
			session.setAttribute("role", role.getName());
			if (role.getName().equals("管理员")) {
				return "redirect:/notice/findAllNotice/admin";
			}
			else if (role.getName().equals("老师")) {
				return "redirect:/notice/findAllNotice/teacher";
			} 
			else{
				return "redirect:/user/student";
			}


		}
		return "redirect:/user/login";
	}

	@RequestMapping("/checkUser")
	public String checkUser(@RequestParam("username") String account,
			@RequestParam("password") String password, HttpSession session)
			throws Exception {
		User user = new User();
		user.setAccount(account);
		user.setPassword(password);
		// 从数据库中获取用户
		user = userService.getUserByAccountAndPass(user);
		//向前端传输密码是否正确
		int flag;
		if (user != null) {
			flag=1;
			session.setAttribute("flag", flag);
			session.setAttribute("user", user);
			return "redirect:/user/main";
		}else{
			flag=0;
			session.setAttribute("flag", flag);
		return "redirect:/user/login";
		}
	}

	// 添加用户
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(@RequestParam("roleId") String roleId, User user,
			HttpServletResponse response) throws Exception {
		userService.addUser(user, roleId);
		return "createUser";
	}

	// 更改用户密码
	@RequestMapping(value = "/changePass", method = RequestMethod.POST)
	public void changePass(@RequestParam("pass") String pass,
			HttpServletResponse response, HttpSession session) throws Exception {
		PrintWriter pw = response.getWriter();

		User user = (User) session.getAttribute("user");
		if (user != null) {
			userService.updatePassword(pass, user.getId());
			pw.write("true");
		} else {
			pw.write("false");
		}

	}

	// 删除账户
	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public String deleteUser(HttpServletRequest request) throws Exception {
		// 从前端获得要删除的项目编号
		String ID = request.getParameter("ID");
		// 从数据库删除
		int userID = Integer.parseInt(ID);

		userService.delUser(userID);
		return "redirect:/user/showUser";
	}

	@RequestMapping(value = "/md5", method = RequestMethod.POST)
	public void md5(@RequestParam("nowpass") String nowpass,
			HttpServletResponse response, HttpSession session) throws Exception {

		String nowPass = DigestUtils.md5Hex(nowpass);

	}

	@RequestMapping("/showUser")
	public String showUser(Model model) throws Exception {
		List<User> users = userService.findAllUser();
		List<UserDTO> userDTOs = new ArrayList<>();
		for (User user : users) {
			UserDTO userDTO = new UserDTO();

			userDTO.setAccount(user.getAccount());
			userDTO.setUsername(user.getUsername());
			userDTO.setId(user.getId());

			userDTOs.add(userDTO);
		}
		model.addAttribute("users", userDTOs);
		return "showUser";
	}

	// 更新用户信息
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public String updateUser(HttpServletResponse response, User user,
			ServletRequest request) throws Exception {
		// 从前端获得用户编号
		/*
		 * String ID=request.getParameter("id"); String
		 * account=request.getParameter("account"); String
		 * userName=request.getParameter("username");
		 */
		userService.updateUser(user);

		return "redirect:/user/showUser";

	}

}
