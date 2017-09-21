$(function() {
	$("#login").click(
			function loginFn() {
				$("#formlogin")[0].reset();
				$("#loginData").show();
			}		
	);

	$("#loginbtn").click(
			function loginbtn() {
				var param = $("#formlogin").serialize();
				$.ajax({
					url : "user/login",
					data : param,
					type : "post",
					success : function (data) {
						if(data == "failure") {
							alert("登陆失败");
							return;
						}
						location.href="user/driverSchool";
					}
				});
			}
	);

	$("#usernamelogin").blur(
			function usernameloginFn() {
				if($("#usernamelogin").val() == "") {
					$("#usernameloginspan").html("<font style='color: red'>用户名不能为空</font>");
				} else {
					$("#usernameloginspan").html("");
				}
			}
	);

	$("#passwordlogin").blur(
			function usernameloginFn() {
				if($("#passwordlogin").val() == "") {
					$("#passwordloginspan").html("<font style='color: red'>密码不能为空</font>");
				} else {
					$("#passwordloginspan").html("");
				}
			}
	);

	$("#register").click(
			function registerFn() {
				$("#loginData").hide();
				$("#formregister")[0].reset();

				//显示地区下拉框
				$.ajax({
					url : "user/select",
					dataType : "json",
					success : function(data) {
						$(data).each(function(i) {
							$("#selectregister").append("<option value="+data[i].id+">"+data[i].name+"</option>");
						});
						$("#registerData").show();
					}
				});


			}
	);


	$("#registerbtn").click(
			function registerbtn() {
				var user = {
						username : $("#usernameregister").val(),
						password : $("#passwordregister").val(),
						addressId: $("#selectregister").get(0).value
				}
				$.ajax({
					url : "user/register",
					data : user,
					type : "post",
					success : function (data) {console.log(data);
					if(data == "success") {
						$("#registerData").hide();
						var flag = confirm("注册成功,是否登录");
						if(flag == true) {
							$("#loginData").show();
						}
					} else {
						alert("注册失败");
					}
					$("#selectregister").empty();
					}
				});

			}
	);

	$("#usernameregister").blur(
			function usernameblur() {
				var usernameregister = $("#usernameregister").val();
				if(usernameregister=="") {
					$("#usernamespan").html("<font style='color: red'>用户名不能为空</font>");
//					$("#usernameregister").focus();
					return;
				} else {
					$("#usernamespan").html("");
				}
			}
	);

	$("#passwordregister").blur(
			function passwordblur() {
				var passwordregister = $("#passwordregister").val();
				if(passwordregister=="") {
					$("#passwordspan").html("<font style='color: red'>密码不能为空</font>");
//					$("#passwordregister").focus();
					return;
				} else {
					$("#passwordspan").html("");
				}
				var password = passwordregister.match("^[0-9]{6,8}$");
				if(password==null) {
					$("#passwordspan").html("<font style='color: red'>密码必须是6-8数字</font>");
//					$("#passwordregister").focus();
					return;
				} else {
					$("#passwordspan").html("");
				}
			}
	);

	$("#passwordregisteragain").blur(
			function passwordagainblur() {
				var passwordregister = $("#passwordregister").val();
				var passwordregisteragain = $("#passwordregisteragain").val();
				if(passwordregister!=passwordregisteragain) {
					$("#passwordspanagain").html("<font style='color: red'>重复密码与密码不一致</font>");
//					$("#passwordregisteragain").focus();
					return;
				} else {
					$("#passwordspanagain").html("");
				}
			}
	);

});