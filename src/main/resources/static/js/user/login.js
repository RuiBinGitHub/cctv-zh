$(document).ready(function() {
	
	$("#pass").attr("type", "password");
	$("#name").attr("placeholder", "请输入登录账号");
	$("#pass").attr("placeholder", "请输入登录密码");
	/** ***************************************************************** */
	$("#btn").click(function() {
		var name = $("#name").val();
		var pass = $("#pass").val();
		if (name == "" || pass == "")
			$("#tips").text("*请输入账号或密码!");
		else {
			$(this).css("background-color", "#ccc");
			$(this).attr("disable", true);
			$(this).val("登录中...");
			$("#form1").submit();
		}
	});

	/** 输入框获取焦点事件 */
	$("#name,#pass").click(function() {
		$("#tips").text("");
	});
	/** 键盘按下事件 */
	$("body,#name,#pass").keydown(function() {
		if (event.keyCode == 13)
			$("#btn").click();
	});
});