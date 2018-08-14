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
<base href="<%=basePath%>">
<meta charset="UTF-8">

<title>report</title>
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
<link rel="stylesheet" href="assets/vendor/select2/select2.css" />
<link rel="stylesheet"
	href="assets/vendor/jquery-datatables-bs3/assets/css/datatables.css" />

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
					<li class="nav-expanded"><a href="user/student"> <i
							class="fa fa-home" aria-hidden="true"></i> <span>系统公告</span>
					</a></li>
					<li><a href="report/dailyReport"> <i class="fa fa-copy"
							aria-hidden="true"></i> <span>我的日报</span>
					</a></li>
					<li class="nav-parent nav-expanded"><a class=""> <i
							class="fa fa-copy" aria-hidden="true"></i> <span>我的周报</span>
					</a>
						<ul class="nav nav-children">
							<c:forEach items="${myprojects}" var="ProjectDTO">
								<c:if test="${ProjectDTO.projectID!=1}">
									<li><a
										href="project/findProject/weeklyReport?clickPName=${ProjectDTO.projectName}&cID=${ProjectDTO.projectID}">${ProjectDTO.projectName}</a>
									</li>
								</c:if>

							</c:forEach>
							<c:forEach items="${myprojects}" var="ProjectDTO">
								<c:if test="${ProjectDTO.projectID==1}">
									<li><a
										href="project/findProject/weeklyReport?clickPName=${ProjectDTO.projectName}&cID=${ProjectDTO.projectID}">${ProjectDTO.projectName}</a>
									</li>
								</c:if>

							</c:forEach>
						</ul></li>
					<li class="nav-active"><a href="group/findMyGroups"> <i
							class="fa fa-list-alt" aria-hidden="true"></i> <span>我的小组</span>
					</a></li>
					<li><a href="meeting/findAllMeeting/meetingReport"> <i
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
			<div class="col-md-12">
				<%
					String dailyuName = request.getParameter("dailyuName");
					//乱码
					dailyuName = new String(dailyuName.getBytes("ISO-8859-1"), "UTF-8");

					String dailypName = request.getParameter("dailypName");
					dailypName = new String(dailypName.getBytes("ISO-8859-1"), "UTF-8");

					pageContext.setAttribute("dpName", dailypName);
					pageContext.setAttribute("duName", dailyuName);
				%>
				<div class="tabs tabs-primary">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#popular1" data-toggle="tab"><%=dailyuName%>的日报列表</a></li>
						<li><a href="#recent1" data-toggle="tab"><%=dailyuName%>的周报列表</a></li>
					</ul>
					<div class="tab-content">
						<div id="popular1" class="tab-pane active">
							<c:forEach items="${showdailyreports}" var="DailyReportDTO">
								<%-- <c:if test="${dpName eq DailyReportDTO.projectName}"> --%>
								<c:if test="${duName eq DailyReportDTO.userName}">
									<div class="panel-body">
										<section class="panel">
										<form id="showform" class="form-horizontal">
											<section class="panel"> <header
												class="panel-heading1">
											<h5>${DailyReportDTO.date}&nbsp&nbsp&nbsp&nbsp&nbsp${DailyReportDTO.projectName}</h5>
											</header>
											<div class="panel-body">
												<div class="form-group">
													<label class="col-sm-2 control-label">完成任务： </label>
													<div class="col-sm-9">
														<label class="control-label" style="text-align: left">${DailyReportDTO.finishedContent}</label>
													</div>
												</div>
												<hr>
												<div class="form-group">
													<label class="col-sm-2 control-label">后续安排：</label>
													<div class="col-sm-9">
														<label class="control-label" style="text-align: left">${DailyReportDTO.arrangement}</label>
													</div>
												</div>
											</div>
											</section>
										</form>
										</section>
									</div>
								</c:if>
								<%-- 		</c:if> --%>
							</c:forEach>
						</div>
						<div id="recent1" class="tab-pane">
							<form id="form1" class="form-horizontal">
								<section class="panel">
								<form id="form1" class="form-horizontal">
									<section class="panel"> <header
										class="panel-heading1"
										style="padding-top:0px;position:relative">
									<h3 style="text-align: center">
										<a id="beforeWeek" href="report/weeklyReportViewSingle?n=-1"
											style="position: absolute; left: 25%; top: 10px; width: 40px; height: 40px; background-color: rgb(204, 204, 204); border-radius: 25px;"
											onmouseover="this.style.backgroundColor='#0088cc'"
											onmouseout="this.style.backgroundColor='rgb(204, 204, 204)'">
											<span
											style="color: white; position: absolute; top: -5px; font-size: 40px; left: 12px"
											class="fc-icon fc-icon-left-single-arrow"></span>
										</a> <a id="nextWeek" href="report/weeklyReportViewSingle?n=1"
											style="position: absolute; left: 68%; top: 10px; width: 40px; height: 40px; background-color: rgb(204, 204, 204); border-radius: 25px;"
											onmouseover="this.style.backgroundColor='#0088cc'"
											onmouseout="this.style.backgroundColor='rgb(204, 204, 204)'">
											<span
											style="color: white; position: absolute; top: -5px; font-size: 40px; left: 12px"
											class="fc-icon fc-icon-right-single-arrow"></span>
										</a> ${showYear}年第${showWeek}周
									</h3>
									<h5 style="text-align: center">${weekTime}</h5>
									</header>
									<div class="panel-body">
										<div class="form-group">
											<label class="col-sm-4 control-label">历史遗留问题： </label>
											<div class="col-sm-8">
												<p>${showProblem}</p>
											</div>
										</div>
										<hr>
										<div class="form-group">
											<label class="col-sm-4 control-label">本周进展：</label>
											<div class="col-sm-8">
												<p>${showSummary}</p>
											</div>
										</div>
										<hr>
										<div class="form-group">
											<label class="col-sm-4 control-label">存在问题及解决方案：</label>
											<div class="col-sm-8">
												<p>${showSolution}</p>
											</div>
										</div>
										<hr>
										<div class="form-group">
											<label class="col-sm-4 control-label">下周计划：</label>
											<div class="col-sm-8">
												<p>${showNextPlan}</p>
											</div>
										</div>
										<hr>
										<div class="form-group">
											<label class="col-sm-4 control-label">可能问题及解决方案：</label>
											<div class="col-sm-8">
												<p>${showFutureSolution}</p>
											</div>
										</div>
									</div>
									</section>
								</form>
								</section>
							</form>
						</div>
					</div>
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
	<script src="assets/vendor/select2/select2.js"></script>
	<script
		src="assets/vendor/jquery-datatables/media/js/jquery.dataTables.js"></script>
	<script
		src="assets/vendor/jquery-datatables/extras/TableTools/js/dataTables.tableTools.min.js"></script>
	<script
		src="assets/vendor/jquery-datatables-bs3/assets/js/datatables.js"></script>

	<!-- Theme Base, Components and Settings -->
	<script src="assets/javascripts/theme.js"></script>

	<!-- Theme Custom -->
	<script src="assets/javascripts/theme.custom.js"></script>

	<!-- Theme Initialization Files -->
	<script src="assets/javascripts/theme.init.js"></script>


	<!-- Examples -->
	<script src="assets/javascripts/tables/examples.datatables.default.js"></script>
	<script
		src="assets/javascripts/tables/examples.datatables.row.with.details.js"></script>
	<script
		src="assets/javascripts/tables/examples.datatables.tabletools.js"></script>

</body>
</html>