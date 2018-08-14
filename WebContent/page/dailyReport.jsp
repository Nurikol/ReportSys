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

<title>周报日报管理系统</title>
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
<link rel="stylesheet"
	href="assets/vendor/fullcalendar/fullcalendar.css" />
<link rel="stylesheet"
	href="assets/vendor/fullcalendar/fullcalendar.print.css" media="print" />

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
					<li class=" nav-active"><a href="report/dailyReport"> <i
							class="fa fa-copy" aria-hidden="true"></i> <span>我的日报</span>
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
					<li><a href="group/findMyGroups"> <i
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
			<div class="col-lg-12" style="width: 70%; float: left;">
				<section class="panel panel-primary">
				<div class="col-md-6" style="width: 100%">
					<div class="tabs tabs-primary">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#sumbit-dailypaper"
								data-toggle="tab">提交我的日报</a></li>
							<c:forEach items="${myprojects}" var="ProjectDTO">
								<li><a href="#${ProjectDTO.projectName}" data-toggle="tab">${ProjectDTO.projectName}</a>
								</li>
							</c:forEach>
						</ul>
						<div class="tab-content">
							<div id="sumbit-dailypaper" class="tab-pane active">
								<!-- <form id="form1" class="form-horizontal"> -->
								<section class="panel"> <header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle"
										data-panel-toggle></a> <a href="#"
										class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>

								<h2 class="panel-title">${dailyDate}</h2>
								</header> <c:forEach items="${myprojects}" var="ProjectDTO">
									<form id="form1" class="form-horizontal" method="post"
										action="report/addDailyReport?dailypID=${ProjectDTO.projectID}">
										<section class="panel"> <header
											class="panel-heading1">
										<h5>${ProjectDTO.projectName}</h5>
										</header>
										<div class="panel-body">
											<div class="form-group">
												<label class="col-sm-3 control-label">完成任务： </label>
												<div class="col-sm-9">
													<textarea class="form-control" rows="3" name="finishedContent"><c:forEach items="${todayDailyReportDTOs}" var="DailyReportDTO"><c:if test="${ProjectDTO.projectName==DailyReportDTO.projectName}">${DailyReportDTO.finishedContent}</c:if></c:forEach></textarea>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label">后续安排：</label>
												<div class="col-sm-9">
													<textarea class="form-control" rows="3" name="arrangement"><c:forEach items="${todayDailyReportDTOs}" var="DailyReportDTO"><c:if test="${ProjectDTO.projectName==DailyReportDTO.projectName}">${DailyReportDTO.arrangement}</c:if></c:forEach></textarea>
												</div>
											</div>
										</div>
										<footer class="panel-footer"
											style="margin-top:20px;background-color:white">
										<button type="submit" class="btn btn-primary"
											onclick="submitDailyReport()" style="margin-left: 78%">提交</button>
										<button type="reset" class="btn btn-default"
											onclick="clearForm()">重写</button>
										</footer> </section>
									</form>
								</c:forEach> </section>
								<!-- </form> -->
								<!-- 查看开始  -->
							</div>
							<c:forEach items="${myprojects}" var="ProjectDTO">
								<div id="${ProjectDTO.projectName}" class="tab-pane">
									<c:forEach items="${Projectdailyreports}" var="DailyReportDTO">
										<c:if
											test="${ProjectDTO.projectName==DailyReportDTO.projectName}">
											<section class="panel">
											<form id="showform" class="form-horizontal">
												<section class="panel"> <header
													class="panel-heading1">
												<h5>${DailyReportDTO.date}</h5>
												</header>
												<div class="panel-body">
													<div class="form-group">
														<label class="col-sm-3 control-label">完成任务： </label>
														<div class="col-sm-8">
															<label class="control-label" style="text-align: left">${DailyReportDTO.finishedContent}</label>
														</div>
													</div>
													<hr>
													<div class="form-group">
														<label class="col-sm-3 control-label">后续安排：</label>
														<div class="col-sm-8">
															<label class="control-label" style="text-align: left">${DailyReportDTO.arrangement}</label>
														</div>
													</div>
												</div>
												</section>
											</form>
											</section>
										</c:if>
									</c:forEach>
								</div>
							</c:forEach>

						</div>
					</div>
				</div>
				</section>
			</div>
			<div class="panel-body " style="float: right; width: 30%;">
				<div class="row">
					<div class="col-md-9" style="width: 100%;">
						<div id="studentDailyCalendar"></div>
					</div>
				</div>
				<p style="padding-top: 15px">
					<span style="color: red">*</span>打<span style="color: red">√</span>代表当天所有项目报表已提交
				</p>
			</div>
		</div>
		<!-- end: page --> </section>
	</div>


	</section>

	<script type="text/javascript">
		function submitDailyReport() {
			document.getElementById("form1").submit();
			alert("提交成功");
		}
	</script>
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
	<script src="assets/vendor/fullcalendar/lib/moment.min.js"></script>
	<script src="assets/vendor/fullcalendar/fullcalendar.js"></script>

	<!-- Theme Base, Components and Settings -->
	<script src="assets/javascripts/theme.js"></script>

	<!-- Theme Custom -->
	<script src="assets/javascripts/theme.custom.js"></script>

	<!-- Theme Initialization Files -->
	<script src="assets/javascripts/theme.init.js"></script>

	<!-- Examples -->
	<script src="assets/javascripts/pages/examples2.calendar.js"></script>

</body>
</html>