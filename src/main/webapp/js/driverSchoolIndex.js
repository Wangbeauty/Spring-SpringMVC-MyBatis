$(function() {
	isLoginFn();
	//点击注销
	$("#logout").click(
			function() {
				location.href = "user/index";
			}
	);
	//点击报名
	$("#subscribeId").click(
			function() {
				afterEnrol();
			}
	);
	//安排学车
	$("#buttonSubscribe").click(
			function() {
				var userId = $("#userIdSubscribe").val();
				var datetimepicker = $("#datetimepicker").val();
				if(datetimepicker == '' || datetimepicker == null){
					alert("学车时间不能为空");
					return;
				}
				var subscribeStr = datetimepicker.substring(0,datetimepicker.length);
				$.ajax({
					url : "user/addSubscribe",
					data : {"userId":userId,"subscribeStr":subscribeStr},
					type : "post",
					success : function(data) {
						if(data == "success") {
							alert("安排成功");
							location.reload();
//						} else if (data == "conflict") {
//							alert("学车时间与他人发生冲突，请重新选择");
						} else {
							alert("安排失败");
						}
					}
				});
			}
	);
	//安排练车div的关闭功能
	$("#hiddenDIV").click(
			function() {
				$("#firstSubscribe").hide();
			}
	);
	
	//确认学车时间
	$("#confirmArrange").click(
			function() {
				$.ajax({
					url:"user/confirmArrange",
					data:{"isConfirm" : 1},
					success:function(data){
						if(data == "failure") {
							alert("学车时间确认失败");
							return;
						}
						alert("成功确认学车时间");
						$("#subscribeStrId").css({"color":"blue"});
						location.reload();
					}
				});
			}
	);

});

function isLoginFn() {
	var isLogin = $("#isLogin").val();
	if(isLogin == "true") {
		showIdentity();
		$("#userlogin").show();
	} else {
		alert("您未登录，请先登陆！");
		location.href = "user/index";
	}
}

function showIdentity() {
	var identityId = $("#identityId").val();
	if(identityId == 1) {//教练登陆
		showSubscribeList();
		$("#welcome").append(" 教练");
		return;
	}
	if(identityId == 2) {//学员登陆
		showSubscribeFn();
		$("#seconddivright1").show();
		$("#welcome").append(" 学员");
		return;
		}
	if(identityId == 3) {//用户登陆
		$("#seconddivright2").show();
		$("#welcome").append(" 用户");
		return;
	}
}

//学员登陆
function showSubscribeFn() {
	$.ajax({
		url: "user/subscribeDate",
		dataType:"json",
		success:function(data) {
			if(data != null && data != '') {
				if(data.isConfirm == 0) {
					$("#showSubscribe").append("<font id='subscribeStrId' style='color:red'>"+data.subscribeStr+"</font>");
					$("#confirmArrange").css({'color':'blue','cursor': 'pointer'});
				} else {
					$("#showSubscribe").append("<font id='subscribeStrId' style='color:blue'>"+data.subscribeStr+"</font>");
					$("#confirmArrange").unbind('click');
				}
				$("#attention").show();
			}
		}
	});
}

//报名成功后，自动重新登录一次，以学员身份登录
function afterEnrol() {
	$.ajax({
		url : "user/afterEnrol",
		success : function (data) {
			if(data == "failure") {
				alert("报名失败");
				return;
			}
			location.reload();
		}
	});
}

//教练登陆
function showSubscribeList() {
	$("#seconddiv").hide();
	$("#thirddiv").hide();
	$.ajax({
		url : "user/subscribeList",
		success : function(data) {
			$(data.isNotSubscribeList).each(function(i) {
				$("#isNotSubscribetable").append("<tr id='"+data.isNotSubscribeList[i].userId+"'><td align='center'>"+data.isNotSubscribeList[i].username+"</td><td align='center'>"+data.isNotSubscribeList[i].enrolStr+"</td><td id='isNotSubscribetable"+i+"' align='center'><font style='color:blue;cursor: pointer;'>安排学车</font></td></tr>");
			});
			$(data.isSubscribeList).each(function(i) {
				var isConfirm = data.isSubscribeList[i].isConfirm;
				if(isConfirm == 0) {
					$("#isSubscribetable").append("<tr id='"+data.isSubscribeList[i].userId+"'><td align='center'>"+data.isSubscribeList[i].username+"</td><td align='center'>"+data.isSubscribeList[i].subscribeStr+"</td><td align='center'><img src='image/no.png' style='width:20px;height:20px;'/></td><td id='isSubscribetable"+i+"' align='center'><font style='color:blue;cursor: pointer;'>修改时间</font></td></tr>");
				}
				if(isConfirm == 1) {
					$("#isSubscribetable").append("<tr id='"+data.isSubscribeList[i].userId+"'><td align='center'>"+data.isSubscribeList[i].username+"</td><td align='center'>"+data.isSubscribeList[i].subscribeStr+"</td><td align='center'><img src='image/yes.png' style='width:20px;height:20px;'/></td><td id='isSubscribetable"+i+"' align='center'><font style='color:blue;cursor: pointer;'>修改时间</font></td></tr>");
				}
			});
			isNotSubscribetableClickFn();
			isSubscribetableClickFn();
		}
	});
	$("#showIsNotSubscribe").show();
	$("#showIsSubscribe").show();
}

function dateShow() {
	WdatePicker({
		lang:'zh-cn',
		dateFmt:'yyyy-MM-dd HH:00:00',
		minDate:'%y-%M-%d',
		isShowOK:true,
		isShowToday:false,
		isShowClear:false,
		readOnly:true
	});
}
//点击修改时间，获取tr里的id
function isSubscribetableClickFn() {
	$('#isSubscribetable td').click(
			function() {
				var tdId = $(this).attr("id");
				if(tdId !='' && tdId != null) {
					var userId = $(this).parent().attr("id");
					var username = $($("#"+tdId+"").prevAll()[2]).html();
					var subscribeStr = $($("#"+tdId+"").prevAll()[1]).html();
					var subscribeDate = subscribeStr.substring(0,subscribeStr.length-1)+":00:00";
					$("#userIdSubscribe").val(userId);
					$("#usernameSubscribe").val(username);
					$("#datetimepicker").val(subscribeDate);
					$("#firstSubscribe").show();
				}
	});
}
//点击安排练车，获取tr里的id
function isNotSubscribetableClickFn() {
	$('#isNotSubscribetable td').click(
			function(){
				var tdId = $(this).attr("id");
				if(tdId !='' && tdId != null) {
					var userId = $(this).parent().attr("id");
					var username = $($("#"+tdId+"").prevAll()[1]).html();
					$("#userIdSubscribe").val(userId);
					$("#usernameSubscribe").val(username);
					$("#firstSubscribe").show();
				}
			});
}