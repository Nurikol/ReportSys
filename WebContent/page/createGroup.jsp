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

<!-- Basic -->
<meta http-equiv="Content-Type" content="text/html; charset="UTF-8">
<base href="<%=basePath%>">

<title>CreateGroup</title>
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
<link rel="stylesheet"
	href="assets/vendor/jquery-ui/css/ui-lightness/jquery-ui-1.10.4.custom.css" />
<link rel="stylesheet" href="assets/vendor/select2/select2.css" />
<link rel="stylesheet"
	href="assets/vendor/bootstrap-multiselect/bootstrap-multiselect.css" />
<link rel="stylesheet"
	href="assets/vendor/bootstrap-tagsinput/bootstrap-tagsinput.css" />
<link rel="stylesheet"
	href="assets/vendor/bootstrap-colorpicker/css/bootstrap-colorpicker.css" />
<link rel="stylesheet"
	href="assets/vendor/bootstrap-timepicker/css/bootstrap-timepicker.css" />
<link rel="stylesheet" href="assets/vendor/dropzone/css/basic.css" />
<link rel="stylesheet" href="assets/vendor/dropzone/css/dropzone.css" />
<link rel="stylesheet"
	href="assets/vendor/bootstrap-markdown/css/bootstrap-markdown.min.css" />
<link rel="stylesheet" href="assets/vendor/summernote/summernote.css" />
<link rel="stylesheet"
	href="assets/vendor/summernote/summernote-bs3.css" />
<link rel="stylesheet"
	href="assets/vendor/codemirror/lib/codemirror.css" />
<link rel="stylesheet" href="assets/vendor/codemirror/theme/monokai.css" />

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
					<li class="nav-expanded"><a
						href="notice/findAllNotice/teacher"> <i class="fa fa-home"
							aria-hidden="true"></i> <span>系统公告</span>
					</a></li>
					<li class="nav-parent"><a> <i class="fa fa-copy"
							aria-hidden="true"></i> <span>报表管理</span>
					</a>
						<ul class="nav nav-children">
							<li><a href="report/findAllDailyReport"> 日报表 </a></li>
							<li><a href="report/findAllWeeklyReport"> 周报表 </a></li>
							<li><a href="report/getReportCount"> 统计分析 </a></li>
						</ul></li>
					<li class="nav-parent  nav-expanded"><a> <i
							class="fa fa-list-alt" aria-hidden="true"></i> <span>小组管理</span>
					</a>
						<ul class="nav nav-children">
							<li class=" nav-active"><a href="group/getUserListInGroup">
									创建小组 </a></li>
							<li><a href="group/getGroupList"> 查看小组 </a></li>
						</ul></li>
					<li class="nav-parent"><a> <i class="fa fa-list-alt"
							aria-hidden="true"></i> <span>项目管理</span>
					</a>
						<ul class="nav nav-children">
							<li><a href="project/createProject"> 创建项目 </a></li>
							<li><a href="project/showProject"> 查看项目 </a></li>
						</ul></li>
					<li><a href="meeting/findAllMeeting/meetingReport_teacher">
							<i class="fa fa-table" aria-hidden="true"></i> <span>会议记录</span>
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
			<div id="userbox" class="userbox" style="padding-top: 5%">

				<a href="#" data-toggle="dropdown"> <figure
						class="profile-picture" style=""> <img
						src="assets/images/user_image.jpg" alt="user_image" width=7%
						height=7% class="img-circle" /> </figure>
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
			<div class="col-xs-12">
				<section class="panel"> <header class="panel-heading">
				<div class="panel-actions">
					<a href="#" class="panel-action panel-action-toggle"
						data-panel-toggle></a> <a href="#"
						class="panel-action panel-action-dismiss" data-panel-dismiss></a>
				</div>

				<h2 class="panel-title">创建小组</h2>
				</header>
				<div class="panel-body">
					<script type="text/JavaScript">
						function submitForm() {
							//document.getElementById("userForm").action = "group/addGroup";
							document.getElementById("groupForm").submit();
							alert("创建成功");
						}

						function clearForm() {
							document.getElementById("groupForm").reset();
						}
					</script>
					<form id="groupForm" class="form-horizontal form-bordered"
						method="post" action="group/addGroup">
						<div class="form-group">
							<label class="col-md-3 control-label">小组名称</label>
							<div class="col-md-6">
								<input type="text" class="form-control" id="inputDefault"
									name="name">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">小组组长</label>
							<div class="col-md-6">
								<select multiple data-plugin-selectTwo
									class="form-control populate" name="leader">
									<optgroup label="老师">
										<c:forEach items="${users}" var="roleDTOs">
											<c:if test="${roleDTOs.roleid != 1}">
												<c:if test="${roleDTOs.roleid==2}">
													<option value="${roleDTOs.id}">${roleDTOs.name}</option>
												</c:if>
											</c:if>
										</c:forEach>
									</optgroup>
									<optgroup label="学生">
										<c:forEach items="${users}" var="roleDTOs">
											<c:if test="${roleDTOs.roleid != 1}">
												<c:if test="${roleDTOs.roleid==3}">
													<option value="${roleDTOs.id}">${roleDTOs.name}</option>
												</c:if>
											</c:if>
										</c:forEach>
									</optgroup>

								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">小组成员</label>
							<div class="col-md-6">
								<select multiple data-plugin-selectTwo
									class="form-control populate" name="member">

									<c:forEach items="${users}" var="roleDTOs">
										<c:if test="${roleDTOs.roleid != 1}">
											<c:if test="${roleDTOs.roleid==3}">
												<option value="${roleDTOs.id}">${roleDTOs.name}</option>
											</c:if>
										</c:if>
									</c:forEach>


								</select>
							</div>
						</div>
					</form>

					<footer class="panel-footer"
						style="margin-top:20px;background:white">
					<button type="submit" class="btn btn-primary"
						onclick="submitForm()" style="margin-left: 64%">提交</button>
					<button type="reset" class="btn btn-default" onclick="clearForm()">重写</button>
					</footer>
				</div>
				</section>
			</div>


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
	<script src="assets/vendor/jquery-ui/js/jquery-ui-1.10.4.custom.js"></script>
	<script
		src="assets/vendor/jquery-ui-touch-punch/jquery.ui.touch-punch.js"></script>
	<script src="assets/vendor/select2/select2.js"></script>
	<script
		src="assets/vendor/bootstrap-multiselect/bootstrap-multiselect.js"></script>
	<script src="assets/vendor/jquery-maskedinput/jquery.maskedinput.js"></script>
	<script src="assets/vendor/bootstrap-tagsinput/bootstrap-tagsinput.js"></script>
	<script
		src="assets/vendor/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
	<script
		src="assets/vendor/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
	<script src="assets/vendor/fuelux/js/spinner.js"></script>
	<script src="assets/vendor/dropzone/dropzone.js"></script>
	<script src="assets/vendor/bootstrap-markdown/js/markdown.js"></script>
	<script src="assets/vendor/bootstrap-markdown/js/to-markdown.js"></script>
	<script src="assets/vendor/bootstrap-markdown/js/bootstrap-markdown.js"></script>
	<script src="assets/vendor/codemirror/lib/codemirror.js"></script>
	<script src="assets/vendor/codemirror/addon/selection/active-line.js"></script>
	<script src="assets/vendor/codemirror/addon/edit/matchbrackets.js"></script>
	<script src="assets/vendor/codemirror/mode/javascript/javascript.js"></script>
	<script src="assets/vendor/codemirror/mode/xml/xml.js"></script>
	<script src="assets/vendor/codemirror/mode/htmlmixed/htmlmixed.js"></script>
	<script src="assets/vendor/codemirror/mode/css/css.js"></script>
	<script src="assets/vendor/summernote/summernote.js"></script>
	<script src="assets/vendor/bootstrap-maxlength/bootstrap-maxlength.js"></script>
	<script src="assets/vendor/ios7-switch/ios7-switch.js"></script>
	<script
		src="assets/vendor/bootstrap-confirmation/bootstrap-confirmation.js"></script>

	<!-- Theme Base, Components and Settings -->
	<script src="assets/javascripts/theme.js"></script>

	<!-- Theme Custom -->
	<script src="assets/javascripts/theme.custom.js"></script>

	<!-- Theme Initialization Files -->
	<script src="assets/javascripts/theme.init.js"></script>

	<!-- Examples -->
	<script src="assets/javascripts/forms/examples.advanced.form.js"></script>

</body>
</html>