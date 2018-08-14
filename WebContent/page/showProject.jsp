<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="fixed">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<base href="<%=basePath%>">
<title>Insert title here</title>
<!-- Basic -->
<meta charset="UTF-8">

<title>SystemNotice</title>
<meta name="keywords" content="HTML5 Admin Template" />
<meta name="description"
	content="Porto Admin - Responsive HTML5 Template">
<meta name="author" content="okler.net">

<!-- Mobile Metas -->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

<!-- Web Fonts  -->
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light"
	rel="stylesheet" type="text/css">

<!-- Vendor CSS -->
<link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />

<link rel="stylesheet"
	href="assets/vendor/font-awesome/css/font-awesome.css" />
<link rel="stylesheet"
	href="assets/vendor/magnific-popup/magnific-popup.css" />
<link rel="stylesheet"
	href="assets/vendor/bootstrap-datepicker/css/datepicker3.css" />

<!-- Specific Page Vendor CSS -->
<link rel="stylesheet"
	href="assets/vendor/bootstrap-fileupload/bootstrap-fileupload.min.css" />

<!-- Theme CSS -->
<link rel="stylesheet" href="assets/stylesheets/theme.css" />

<!-- Skin CSS -->
<link rel="stylesheet" href="assets/stylesheets/skins/default.css" />

<!-- Theme Custom CSS -->
<link rel="stylesheet" href="assets/stylesheets/theme-custom.css">

<!-- Head Libs -->
<script src="assets/vendor/modernizr/modernizr.js"></script>
</head>
<body>
	<section class="body"> <!-- start: header --> <!-- end: header -->

	<div class="inner-wrapper">
		<!-- start: sidebar -->
		<aside id="sidebar-left" class="sidebar-left">

		<div class="sidebar-header">
			<div class="sidebar-title">导航栏</div>
			<div class="sidebar-toggle hidden-xs"
				data-toggle-class="sidebar-left-collapsed" data-target="html"
				data-fire-event="sidebar-left-toggle">
				<i class="fa fa-bars" aria-label="Toggle sidebar"></i>
			</div>
		</div>

		<div class="nano">
			<div class="nano-content">
				<nav id="menu" class="nav-main" role="navigation">
				<ul class="nav nav-main">
					<li class="nav-expanded"><a href="notice/findAllNotice/teacher">
							<i class="fa fa-home" aria-hidden="true"></i> <span>系统公告</span>
					</a></li>
					<li class="nav-parent"><a> <i
							class="fa fa-copy" aria-hidden="true"></i> <span>报表管理</span>
					</a>
						<ul class="nav nav-children">
							<li><a href="report/findAllDailyReport"> 日报表 </a></li>
							<li><a href="report/findAllWeeklyReport"> 周报表 </a></li>
							<li><a href="report/getReportCount"> 统计分析 </a></li>
						</ul></li>
					<li class="nav-parent"><a> <i class="fa fa-list-alt"
							aria-hidden="true"></i> <span>小组管理</span>
					</a>
						<ul class="nav nav-children">
							<li><a href="group/getUserListInGroup"> 创建小组 </a></li>
							<li><a href="group/getGroupList"> 查看小组 </a></li>
						</ul></li>
					<li class="nav-parent nav-expanded"><a> <i class="fa fa-list-alt"
							aria-hidden="true"></i> <span>项目管理</span>
					</a>
						<ul class="nav nav-children">
							<li><a href="project/createProject"> 创建项目 </a></li>
							<li class="nav-active"><a href="project/showProject"> 查看项目 </a></li>
						</ul></li>
					<li><a href="meeting/findAllMeeting/meetingReport_teacher"> <i
							class="fa fa-table" aria-hidden="true"></i> <span>会议记录</span>
					</a></li>

				</ul>
				</nav>

				<hr class="separator" />

				<hr class="separator" />

			</div>

		</div>

		</aside>
		<!-- end: sidebar -->

		<section role="main" class="content-body"> <header
			class="page-header">
		<h2>大数据实验室</h2>

		<div class="right-wrapper pull-right">
			<div id="userbox" class="userbox"  style="padding-top:5%">

				<a href="#" data-toggle="dropdown"> 
				<figure
						class="profile-picture" style=""> <img
						src="assets/images/user_image.jpg" alt="user_image" width=7% height=7%
						class="img-circle" /> </figure>
					<div class="profile-info">
						<span class="name">${name}</span> <span class="role">${role}</span>
					</div> <i class="fa custom-caret"></i>
				</a>

				<div class="dropdown-menu">
					<ul class="list-unstyled">
						<li class="divider"></li>
						<li><a role="menuitem" tabindex="-1" href="#"><i
								class="fa fa-whatsapp"></i>绑定微信</a></li>

						<li><a role="menuitem" tabindex="-1"
							href="changePassword.jsp"><i class="fa fa-lock"></i> 修改密码</a></li>
						<li><a role="menuitem" tabindex="-1" href="user/login"><i
								class="fa fa-power-off"></i> 安全登出</a></li>
					</ul>
				</div>
			</div>
			<!-- 	<a class="sidebar-right-toggle" data-open="sidebar-right"><i class=""></i></a> -->
		</div>
		</header> <!-- start: page -->
		<div class="row">
			<div class="col-md-6" style="width: 100%">
				<section class="panel"> <header class="panel-heading">
				<div class="panel-actions">
					<a href="#" class="panel-action panel-action-toggle"
						data-panel-toggle></a> <a href="#"
						class="panel-action panel-action-dismiss" data-panel-dismiss></a>
				</div>

				<h2 class="panel-title">查看项目</h2>
				</header>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-hover mb-none">
							<thead>
								<tr>
									<th class="center">项目编号</th>
									<th class="center">项目名称</th>
									<th class="center">负责小组名称</th>
									<th class="center">小组人员</th>
									<th class="center">操作</th>
								</tr>
							</thead>
							<tbody>
								<%
									int i = 1;
								%>
								<c:forEach items="${projects}" var="ProjectDTO">
									<tr>
										<td class="center"><%=i++%></td>
										<td class="center">${ProjectDTO.projectName}</td>
										<td class="center">${ProjectDTO.name}</td>
										<td class="center"><mark>${ProjectDTO.leader}</mark>&nbsp${ProjectDTO.member}</td>
										<td class="center actions-hover actions-fade">
											<!-- 	<a href="" onclick="pencilProject()"><i class="fa fa-pencil"></i></a> -->
											<a href="project/deleteProject?pID=${ProjectDTO.projectID}"
											onclick="return confirm('确定要删除此项目么');" class="delete-row"><i
												class="fa fa-trash-o"></i></a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				</section>
			</div>
		</div>

		<!-- end: page --> </section>
	</div>

	</section>

	<!-- Vendor -->
	<script src="assets/vendor/jquery/jquery.js"></script>
	<script
		src="assets/vendor/jquery-browser-mobile/jquery.browser.mobile.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
	<script src="assets/vendor/nanoscroller/nanoscroller.js"></script>
	<script
		src="assets/vendor/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script src="assets/vendor/magnific-popup/magnific-popup.js"></script>
	<script src="assets/vendor/jquery-placeholder/jquery.placeholder.js"></script>

	<!-- Specific Page Vendor -->
	<script src="assets/vendor/jquery-autosize/jquery.autosize.js"></script>
	<script
		src="assets/vendor/bootstrap-fileupload/bootstrap-fileupload.min.js"></script>

	<!-- Theme Base, Components and Settings -->
	<script src="assets/javascripts/theme.js"></script>

	<!-- Theme Custom -->
	<script src="assets/javascripts/theme.custom.js"></script>

	<!-- Theme Initialization Files -->
	<script src="assets/javascripts/theme.init.js"></script>

</body>
</html>