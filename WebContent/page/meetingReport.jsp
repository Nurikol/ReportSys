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

<title>Meeting</title>
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
					<li><a href="group/findMyGroups"> <i
							class="fa fa-list-alt" aria-hidden="true"></i> <span>我的小组</span>
					</a></li>
					<li class="nav-active"><a
						href="meeting/findAllMeeting/meetingReport"> <i
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
		</div>
		</header> <!-- start: page -->
		<div class="row">
			<div class="col-lg-12">
				<div class="timeline">
					<div class="tm-body">
						<a class="mb-xs mt-xs mr-xs btn btn-danger"
							style="position: absolute; right: 0px"
							href="meeting/getUserListInMeeting/createMeeting"> <i
							class="glyphicon glyphicon-plus"></i>
						</a>
					</div>
				</div>
			</div>
			<c:forEach items="${date}" var="ym">
				<div class="col-lg-12">
					<div class="timeline">
						<div class="tm-body">
							<div class="tm-title">
								<h3 class="h5 text-uppercase">${ym.year}${ym.month}</h3>
							</div>
							<c:forEach items="${meetings}" var="meetingDTOs">
								<c:if test="${meetingDTOs.year==ym.year}">
									<c:if test="${meetingDTOs.month==ym.month}">
										<ol class="tm-items">
											<li>
												<div class="tm-info">
													<div class="tm-icon">
														<i class="fa fa-star"></i>
													</div>
													<time class="tm-datetime"
														datetime="${meetingDTOs.meetingDate}">
													<div class="tm-datetime-date">${meetingDTOs.weekNumber }</div>
													<div class="tm-datetime-time">${meetingDTOs.meetingDate}</div>
													</time>
												</div>
												<div class="tm-box appear-animation"
													data-appear-animation="fadeInRight"
													data-appear-animation-delay="100">
													<blockquote class="primary">
														<p>会议主题：${meetingDTOs.theme}</p>
														<a
															href="meeting/getUserListInUpdateMeeting/updateMeeting?meetingID=${meetingDTOs.id}"><i
															class="fa fa-pencil" style="float: right"></i></a>
													</blockquote>
													<p>${meetingDTOs.meetingContent}</p>


													<div class="tm-meta">
														<span> <i class="fa fa-user"></i> By ${ meetingDTOs.recorder}
														</span> <span> <i class="fa fa-comments"></i>
															<button type="button" class="btn btn-default btn-xs"
																data-toggle="tooltip" data-placement="bottom"
																title="${meetingDTOs.meetingMember}"
																style="border: none;">${meetingDTOs.numbers}人</button>
														</span> <span><i class="fa fa-user"></i>
															其他与会人员：${meetingDTOs.otherMember}</span> <span
															style="float: right"><i class="fa fa-calendar"></i>
															创建时间： ${meetingDTOs.createDate}</span>
													</div>
												</div>
											</li>
										</ol>
									</c:if>
								</c:if>
							</c:forEach>

						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

	<!-- end: page --> </section>



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
	<script src="assets/vendor/jquery-appear/jquery.appear.js"></script>
	<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
	<script src="assets/vendor/gmaps/gmaps.js"></script>

	<!-- Theme Base, Components and Settings -->
	<script src="assets/javascripts/theme.js"></script>

	<!-- Theme Custom -->
	<script src="assets/javascripts/theme.custom.js"></script>

	<!-- Theme Initialization Files -->
	<script src="assets/javascripts/theme.init.js"></script>


	<!-- Examples -->
	<script src="assets/javascripts/pages/examples.timeline.js"></script>

</body>
</html>