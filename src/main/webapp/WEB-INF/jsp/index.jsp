<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="shortcut icon" href="image/car1.png" type="image/x-icon" />
		<link rel="stylesheet" href="css/minecss.css" type="text/css">
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/index.js"></script>
		<title>春申驾校</title>
	</head>

	<body>
		<div class="rightdiv">
			<!-- 登陆弹窗 -->
			<div id="loginData" class="loginData">
				<caption><h3>用户登录</h3></caption>
				<form id="formlogin">
					<font style="font-weight: bold">用户名:</font>&nbsp;&nbsp;&nbsp;<input id="usernamelogin" type="text" name="usernamelogin"/><span id="usernameloginspan"></span><br><br><br>
					<font style="font-weight: bold">密&nbsp;&nbsp;&nbsp;码:</font>&nbsp;&nbsp;&nbsp;<input id="passwordlogin" type="password" name="passwordlogin"/><span id="passwordloginspan"></span><br><br><br>
					<input id="loginbtn" type="button" value="登陆" style="cursor:pointer;"/><br><br>
					<font>还没有账号?</font><font id="register" style="text-decoration:underline;color:blue;cursor:pointer;">立即注册</font>
				</form>
			</div>
		
			<!-- 注册弹窗 -->
			<div id="registerData" class="registerData">
				<caption><h3>用户注册</h3></caption>
				<form id="formregister">
					<font style="font-weight: bold">用&nbsp;户&nbsp;名:</font>&nbsp;&nbsp;&nbsp;&nbsp;<input id="usernameregister" type="text" name="usernameregister"/><span id="usernamespan"></span><br><br><br>
					<font style="font-weight: bold">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</font>&nbsp;&nbsp;&nbsp;&nbsp;<input id="passwordregister" type="text" name="passwordregister"/><span id="passwordspan"></span><br><br><br>
					<font style="font-weight: bold">重复密码:</font>&nbsp;&nbsp;&nbsp;<input id="passwordregisteragain" type="text" name="passwordregisteragain"/><span id="passwordspanagain"></span><br><br><br>
					<font style="font-weight: bold">地&nbsp;&nbsp;&nbsp;区:</font>&nbsp;&nbsp;&nbsp;<select id="selectregister" name="selectregister"></select><br><br><br>
					<input id="registerbtn" type="button" value="注册"/>
				</form>
			</div>
		</div>
		
		<div class="leftdiv">
			<img src="image/banner1.jpg" width="100%" height="100%">
		</div>
	</body>

</html>