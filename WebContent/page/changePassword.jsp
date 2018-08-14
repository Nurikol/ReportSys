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
		<!-- Basic -->
		<meta charset="UTF-8">

		<meta name="keywords" content="HTML5 Admin Template" />
		<meta name="description" content="Porto Admin - Responsive HTML5 Template">
		<meta name="author" content="okler.net">

		<!-- Mobile Metas -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />

		<!-- Web Fonts  -->
		<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light" rel="stylesheet" type="text/css">

		<!-- Vendor CSS -->
		<link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />

		<link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.css" />
		<link rel="stylesheet" href="assets/vendor/magnific-popup/magnific-popup.css" />
		<link rel="stylesheet" href="assets/vendor/bootstrap-datepicker/css/datepicker3.css" />

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
		<!-- start: page -->
		<section class="body-sign body-locked">
			<div class="center-sign">
				<div class="panel panel-sign">
					<div class="panel-body">
						<form id="editPass_form" name="ff" enctype="multipart/form-data" method="post">
							<div class="current-user text-center">
								<img src="assets/images/!logged-user.jpg" alt="John Doe" class="img-circle user-image" />
								<h2 class="user-name text-dark m-none">${user.username}</h2>
								<p class="user-email m-none">student</p>
							</div>
							<div class="form-group mb-lg">
							    <!-- <div class="input-group input-group-icon">
									<input id="nowPass" type="password" name="nowpass" class="form-control input-lg" placeholder="Password" />
									<span class="input-group-addon">
										<span class="icon icon-lg">
											<i class="fa fa-lock"></i>
										</span>
									</span>
								</div> -->
								<div class="input-group input-group-icon" style="margin-top: 10px">
									<input id="newPass" type="password" class="form-control input-lg" placeholder="New Password" />
									<span class="input-group-addon">
										<span class="icon icon-lg">
											<i class="fa fa-lock"></i>
										</span>
									</span>
								</div>
								<div class="input-group input-group-icon" style="margin-top: 10px">
									<input id="rePass" type="password" class="form-control input-lg" placeholder="Repeat The Password" />
									<span class="input-group-addon">
										<span class="icon icon-lg">
											<i class="fa fa-lock"></i>
										</span>
									</span>
								</div>
							</div>

							<div class="row">
								<div class="col-xs-12 text-right">
									<input type="button" onclick="changePass_submit()" class="btn btn-primary"
						value="提交">
									</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			
			<script type="text/javascript">
		function editPass() {
			$('#editPass').dialog('open');
			return true;
		}
		function editPass_cancel() {
			$(".panel-tool-close").click();
			$('#editPass_form').form('clear');
		}

		
		//修改密码
		
		
		
		function changePass_submit() {
			var $nowPass= $('#nowPass');
			var $newPass = $('#newPass');
			var $rePass = $('#rePass');
			 var $pass=$('#${user.password}');
			 
			
		/* 	
			if ($nowPass.val() !=$pass.val()) {
				//msgShow('系统提示', '请输入密码！', 'warning');${user.username}
				alert('密码错误');
				return false; 
			} 
		*/	
			
			if ($newPass.val() == '') {
				//msgShow('系统提示', '请输入密码！', 'warning');${user.username}
				alert('请输入密码');
				return false;
			}
			if ($rePass.val() == '') {
				//msgShow('系统提示', '请在一次输入密码！', 'warning');
				alert('请再一次输入密码');
				return false;
			}

			if ($newPass.val() != $rePass.val()) {
				//msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
				alert('两次密码不一至！请重新输入');
				return false;
			}

			$.post('user/changePass?pass=' + $newPass.val(), function(data) {

				if (data == true || data == 'true') {
					//msgShow('消息', '密码修改成功！', 'info');
				}
				if (data == false || data == 'false') {
					msgShow('消息', '操作失败!', 'error');
				}

				$newPass.val('');
				$rePass.val('');
				$nowPass.val('');
				alert('修改成功，请重新登录');
				window.location.href='page/pages-signin.jsp';
				
			});

		}
		
		
	</script>
		</section>
		<!-- end: page -->

		<!-- Vendor -->
		<script src="assets/vendor/jquery/jquery.js"></script>
		<script src="assets/vendor/jquery-browser-mobile/jquery.browser.mobile.js"></script>
		<script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
		<script src="assets/vendor/nanoscroller/nanoscroller.js"></script>
		<script src="assets/vendor/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
		<script src="assets/vendor/magnific-popup/magnific-popup.js"></script>
		<script src="assets/vendor/jquery-placeholder/jquery.placeholder.js"></script>
		
		<!-- Theme Base, Components and Settings -->
		<script src="assets/javascripts/theme.js"></script>
		
		<!-- Theme Custom -->
		<script src="assets/javascripts/theme.custom.js"></script>
		
		<!-- Theme Initialization Files -->
		<script src="assets/javascripts/theme.init.js"></script>
		
		
		

	</body>
</html>