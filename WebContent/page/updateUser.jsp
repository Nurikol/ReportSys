<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="fixed">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<base href="<%=basePath%>">
<title>Insert title here</title>
<!-- Mobile Metas -->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

<!-- Web Fonts  -->
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light"
	rel="stylesheet" type="text/css">

<!-- Vendor CSS -->
<link rel="stylesheet"
	href="<%=basePath%>assets/vendor/bootstrap/css/bootstrap.css" />

<link rel="stylesheet"
	href="<%=basePath%>assets/vendor/font-awesome/css/font-awesome.css" />
<link rel="stylesheet"
	href="<%=basePath%>assets/vendor/magnific-popup/magnific-popup.css" />
<link rel="stylesheet"
	href="<%=basePath%>assets/vendor/bootstrap-datepicker/css/datepicker3.css" />

<!-- Specific Page Vendor CSS -->
<link rel="stylesheet"
	href="<%=basePath%>assets/vendor/bootstrap-fileupload/bootstrap-fileupload.min.css" />
<link rel="stylesheet"
	href="<%=basePath%>assets/vendor/jquery-ui/css/ui-lightness/jquery-ui-1.10.4.custom.css" />
<link rel="stylesheet"
	href="<%=basePath%>assets/vendor/select2/select2.css" />
<link rel="stylesheet"
	href="<%=basePath%>assets/vendor/bootstrap-multiselect/bootstrap-multiselect.css" />
<link rel="stylesheet"
	href="<%=basePath%>assets/vendor/bootstrap-tagsinput/bootstrap-tagsinput.css" />
<link rel="stylesheet"
	href="<%=basePath%>assets/vendor/bootstrap-colorpicker/css/bootstrap-colorpicker.css" />
<link rel="stylesheet"
	href="<%=basePath%>assets/vendor/bootstrap-timepicker/css/bootstrap-timepicker.css" />
<link rel="stylesheet"
	href="<%=basePath%>assets/vendor/dropzone/css/basic.css" />
<link rel="stylesheet"
	href="<%=basePath%>assets/vendor/dropzone/css/dropzone.css" />
<link rel="stylesheet"
	href="<%=basePath%>assets/vendor/bootstrap-markdown/css/bootstrap-markdown.min.css" />
<link rel="stylesheet"
	href="<%=basePath%>assets/vendor/summernote/summernote.css" />
<link rel="stylesheet"
	href="<%=basePath%>assets/vendor/summernote/summernote-bs3.css" />
<link rel="stylesheet"
	href="<%=basePath%>assets/vendor/codemirror/lib/codemirror.css" />
<link rel="stylesheet"
	href="<%=basePath%>assets/vendor/codemirror/theme/monokai.css" />

<!-- Theme CSS -->
<link rel="stylesheet" href="<%=basePath%>assets/stylesheets/theme.css" />

<!-- Skin CSS -->
<link rel="stylesheet"
	href="<%=basePath%>assets/stylesheets/skins/default.css" />

<!-- Theme Custom CSS -->
<link rel="stylesheet"
	href="<%=basePath%>assets/stylesheets/theme-custom.css">

<!-- Head Libs -->
<script src="<%=basePath%>assets/vendor/modernizr/modernizr.js"></script>
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
					<li class="nav-expanded "><a href="notice/findAllNotice/admin">
							<i class="fa fa-home" aria-hidden="true"></i> <span>系统公告</span>
					</a></li>
					<li class="nav-parent nav-expanded"><a> <i
							class="fa fa-user" aria-hidden="true"></i> <span>用户管理</span>
					</a>
						<ul class="nav nav-children">
							<li><a href="<%=basePath%>page/createUser.jsp"> 创建用户 </a></li>
							<li class="nav-active"><a href="<%=basePath%>user/showUser">
									查看用户 </a></li>
						</ul></li>

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
				<h2 class="panel-title">更新用户信息</h2>
				</header>
				<div class="panel-body">
					<form class="form-horizontal form-bordered" id="ff"
						action="user/updateUser" method="post">
						<%
							String account = request.getParameter("account");
							String username = request.getParameter("username");
							int id = Integer.parseInt(request.getParameter("id"));
							//将字符类型转换为utf-8
							username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
						%>
						<div class="form-group">
							<label class="col-md-3 control-label">用户编号</label>
							<div class="col-md-6">
								<input type="text" class="form-control" id="inputDefault"
									name="id" value="<%=id%>">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">账号名</label>
							<div class="col-md-6">
								<input type="text" class="form-control" id="inputDefault"
									name="account" value="<%=account%>">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">用户名</label>
							<div class="col-md-6">
								<input type="text" class="form-control" id="inputDefault"
									name="username" value="<%=username%>">
							</div>
						</div>



						<!-- <div class="form-group">
										<label class="col-md-3 control-label">用户角色</label>
										<div class="col-md-6">
											<select data-plugin-selecttwo="" class="form-control populate select2-offscreen" tabindex="-1" title="" name="roleId">
												<option value="1">管理员</option>
												<option value="2">老师</option>
												<option value="3">学生</option>
											</select>
										</div>
									</div> -->

					</form>
					<footer class="panel-footer"
						style="margin-top:20px;background-color:white">
					<button type="submit" class="btn btn-primary"
						onclick="submitForm()" style="margin-left: 64%">提交</button>
					<button type="reset" class="btn btn-default" onclick="clearForm()">重写</button>
					</footer>
				</div>
				</section>
			</div>
		</div>
		<!-- end: page --> </section>
	</div>

	<script type="text/javascript">
		function submitForm() {
			document.getElementById("ff").submit();
			alert("更新成功！")
		}
		function clearForm() {
			document.getElementById("ff").reset();
		}
	</script> </section>

	<!-- Vendor -->
	<script src="<%=basePath%>assets/vendor/jquery/jquery.js"></script>
	<script
		src="<%=basePath%>assets/vendor/jquery-browser-mobile/jquery.browser.mobile.js"></script>
	<script src="<%=basePath%>assets/vendor/bootstrap/js/bootstrap.js"></script>
	<script src="<%=basePath%>assets/vendor/nanoscroller/nanoscroller.js"></script>
	<script
		src="<%=basePath%>assets/vendor/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script
		src="<%=basePath%>assets/vendor/magnific-popup/magnific-popup.js"></script>
	<script
		src="<%=basePath%>assets/vendor/jquery-placeholder/jquery.placeholder.js"></script>

	<!-- Specific Page Vendor -->
	<script
		src="<%=basePath%>assets/vendor/jquery-autosize/jquery.autosize.js"></script>
	<script
		src="<%=basePath%>assets/vendor/bootstrap-fileupload/bootstrap-fileupload.min.js"></script>
	<script
		src="<%=basePath%>assets/vendor/jquery-ui/js/jquery-ui-1.10.4.custom.js"></script>
	<script
		src="<%=basePath%>assets/vendor/jquery-ui-touch-punch/jquery.ui.touch-punch.js"></script>
	<script src="<%=basePath%>assets/vendor/select2/select2.js"></script>
	<script
		src="<%=basePath%>assets/vendor/bootstrap-multiselect/bootstrap-multiselect.js"></script>
	<script
		src="<%=basePath%>assets/vendor/jquery-maskedinput/jquery.maskedinput.js"></script>
	<script
		src="<%=basePath%>assets/vendor/bootstrap-tagsinput/bootstrap-tagsinput.js"></script>
	<script
		src="<%=basePath%>assets/vendor/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
	<script
		src="<%=basePath%>assets/vendor/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
	<script src="<%=basePath%>assets/vendor/fuelux/js/spinner.js"></script>
	<script src="<%=basePath%>assets/vendor/dropzone/dropzone.js"></script>
	<script
		src="<%=basePath%>assets/vendor/bootstrap-markdown/js/markdown.js"></script>
	<script
		src="<%=basePath%>assets/vendor/bootstrap-markdown/js/to-markdown.js"></script>
	<script
		src="<%=basePath%>assets/vendor/bootstrap-markdown/js/bootstrap-markdown.js"></script>
	<script src="<%=basePath%>assets/vendor/codemirror/lib/codemirror.js"></script>
	<script
		src="<%=basePath%>assets/vendor/codemirror/addon/selection/active-line.js"></script>
	<script
		src="<%=basePath%>assets/vendor/codemirror/addon/edit/matchbrackets.js"></script>
	<script
		src="<%=basePath%>assets/vendor/codemirror/mode/javascript/javascript.js"></script>
	<script src="<%=basePath%>assets/vendor/codemirror/mode/xml/xml.js"></script>
	<script
		src="<%=basePath%>assets/vendor/codemirror/mode/htmlmixed/htmlmixed.js"></script>
	<script src="<%=basePath%>assets/vendor/codemirror/mode/css/css.js"></script>
	<script src="<%=basePath%>assets/vendor/summernote/summernote.js"></script>
	<script
		src="<%=basePath%>assets/vendor/bootstrap-maxlength/bootstrap-maxlength.js"></script>
	<script src="<%=basePath%>assets/vendor/ios7-switch/ios7-switch.js"></script>
	<script
		src="<%=basePath%>assets/vendor/bootstrap-confirmation/bootstrap-confirmation.js"></script>

	<!-- Theme Base, Components and Settings -->
	<script src="<%=basePath%>assets/javascripts/theme.js"></script>

	<!-- Theme Custom -->
	<script src="<%=basePath%>assets/javascripts/theme.custom.js"></script>

	<!-- Theme Initialization Files -->
	<script src="<%=basePath%>assets/javascripts/theme.init.js"></script>

</body>
</html>