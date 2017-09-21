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
		<link rel='stylesheet' type='text/css' href='<%=basePath%>datepicker/skin/WdatePicker.css' />
		<link rel='stylesheet' type='text/css' href='<%=basePath%>datepicker/skin/default/datepicker.css' />
		<script type="text/javascript" src="<%=basePath%>datepicker/WdatePicker.js"></script>
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/driverSchoolIndex.js"></script>
		<title>春申驾校</title>
	</head>

	<body>
		<input id="identityId" type="hidden" name="identityId" value="${user.identityId}"/>
		<input id="isLogin" type="hidden" name="isLogin" value="${isLogin}"/>
		<div class="firstdiv">
			<div align="right" style="float:right;margin-right:40px">
				<div id="userlogin" style="display: none;float:left;">
					<span id="welcome">欢迎您：<font style="color:blue;">${user.username}</font></span>&nbsp;|
					<span id="logout" style="cursor: pointer;">注销</span>
				</div>
			</div>
		</div>
		<div class="seconddiv" id="seconddiv">
			<img title="春申驾校预约系统" src="image/banner2.jpg" >
			<div class="seconddivright1" id="seconddivright1">
				<span id="showSubscribe" >
					<h3>学员学车时间安排</h3>
					<h4>学员姓名:</h4><font style='color:blue'>${user.username}</font><br>
					<h4>下次学车时间:</h4>
				</span>
				<br><br>
				<div class="attention" id="attention">
					<font style="color:red;">注意:</font>
					<br><br>
					<font>能准时参加,请点击<font id="confirmArrange">确认</font></font>
					<br><br>
					<font>如果时间安排不妥,请联系教练修改</font>
				</div>
			</div>
			<div id="seconddivright2" class="seconddivright2">
				<font id="subscribeId" style="font-weight: bold;font-size: 40;cursor: pointer;">报名学车</font>
			</div>
		</div>
		
		<div class="thirddiv" id="thirddiv">
			<div>
				<h3>驾校信息</h3>
				<h4>驾校教练：袁静</h4>
				<h4>教练描述：驾校一把手</h4>
				<h4>教练电话：13671633237</h4>
			</div>
		</div>
		
		<!--------------------教练登陆--------------------->
		<div id="showIsNotSubscribe" class="showIsNotSubscribe">
			<caption><h3>春申驾校报名单</h3></caption>
			<table id="isNotSubscribetable" border="1" align="center">
			<tr>
				<td align='center' width="200px">报名人</td>
				<td align='center' width="200px">报名时间</td>
				<td align='center' width="200px">操作</td>
			</tr>
			</table>
		</div>
		<div id="showIsSubscribe" class="showIsSubscribe">
			<caption><h3>春申驾校预约单</h3></caption>
			<table id="isSubscribetable" border="1" align="center">
			<tr>
				<td align='center' width="200px">预约人</td>
				<td align='center' width="200px">预约时间</td>
				<td align='center' width="200px">是否确认时间</td>
				<td align='center' width="200px">操作</td>
			</tr>
			</table>
		</div>
		<!------------------------------------------------------->
		
		<div class="fourthdiv">
			<div align="right">
				<font style="font-weight: bold">Copyright &copy; 王俊文和袁静 1234567890 保留所有权利。</font>
			</div>
		</div>
		
		<div id="firstSubscribe" class="firstSubscribe">
			<div style="float:right;"><font id="hiddenDIV" style="cursor:pointer;" title="关闭">X&nbsp;&nbsp;</font></div>
			<input id="userIdSubscribe" type="hidden"/>
			<h4>安排学车</h4>
			学员姓名:&nbsp;&nbsp;<input id="usernameSubscribe" type="text" disabled="disabled"/>
			<br><br>
			学车时间:&nbsp;&nbsp;<input id="datetimepicker" type="text" onclick="dateShow();"/>
			<br><br>
			<input type="button" id="buttonSubscribe" value="确定安排"/>
		</div>
		
	</body>

</html>