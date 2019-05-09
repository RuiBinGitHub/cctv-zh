$(document).ready(function() {
    var code = null;
    $("#logo img").attr("title", "用户登录");
    /********************************************************************/
    //输入框显示提示信息
    $("#tab1 input[type=text]:eq(0)").attr("placeholder", "请输入登录账号");
    $("#tab1 input[type=text]:eq(1)").attr("placeholder", "请输入电子邮箱");
    $("#tab1 input[type=text]:eq(2)").attr("placeholder", "请输入验证码");
    $("#tab1 input[type=password]:eq(0)").attr("placeholder", "请输入您的新密码");
    $("#tab1 input[type=password]:eq(1)").attr("placeholder", "请确认您的新密码");
    /********************************************************************/
    $(".combtn").click(function() {
        if (!checkUserName() || !checkPassWord())
            return false;
        if (!checkMail() || !checkCode())
            return false;
        $("#form1").submit();
    });

    /** 检测账号 */
    function checkUserName() {
        var username = $("#tab1 input[type=text]:eq(0)").val();
        if (username.length < 6 || username.length > 16) {
            $("#tab1 input[type=text]:eq(0)").css("border-color", "#f00");
            $("#tips").text("*登录账号格式不正确!");
            return false;
        }
        return true;
    }

    /** 检测密码 */
    function checkPassWord() {
        var password1 = $("#tab1 input[type=password]:eq(0)").val();
        var password2 = $("#tab1 input[type=password]:eq(1)").val();
        if (password1.length < 6 || password1.length > 16) {
            $("#tab1 input[type=password]:eq(0)").css("border-color", "#f00");
            $("#tips").text("*登录密码格式不正确!");
            return false;
        }
        if (password1 != password2) {
            $("#tab1 input[type=password]:eq(0)").css("border-color", "#f00");
            $("#tab1 input[type=password]:eq(1)").css("border-color", "#f00");
            $("#tips").text("*两次密码不一致,请重新输入!");
            return false;
        }
        return true;
    }

    /** 检测邮箱 */
    function checkMail() {
    	if (!checkUserName())
    		return false
        var mail = $("#tab1 input[type=text]:eq(1)").val();
        if (mail == "" || !mail.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/)) {
            $("#tab1 input[type=text]:eq(1)").css("border-color", "#f00");
            $("#tips").text("*邮箱格式不正确!");
            return false;
        }
        var username = $("#tab1 input[type=text]:eq(0)").val();
        if (!Ajax("checknamemail", {username: username, mail: mail})) {
            $("#tab1 input[type=text]:eq(1)").css("border-color", "#f00");
            $("#tips").text("*登录账号与邮箱不匹配!");
            return false;
        }
        return true;
    }
    /** 检测验证码 */
    function checkCode() {
        var temp = $("#tab1 input[type=text]:eq(2)").val();
        if (temp == "" || temp != code) {
            $("#tips").text("*验证码输入不正确!");
            $("#tab1 input[type=text]:eq(2)").css("border-color", "#f00");
            return false;
        }
        return true;
    }

    var time = 0;
    $("#getBtn").click(function() {
		if (!checkMail())
			return false;
		 var mail = $("#tab1 input[type=text]:eq(1)").val();
		 var data = Ajax("sendmail", {"mail": mail});
		 if (data == ""){
			 $("#tab1 input[type=text]:eq(1)").css("border-color", "#f00");
			 $("#tips").text("*电子邮箱不正确!");
		     return false;
		}
		time = 60;
		code = data;
		$(this).attr("disabled", true);
		$("#getBtn").css("color", "#CCCCCC");
		changeTime();
    });

    function changeTime() {
        if (time-- == 0) {
            $("#getBtn").attr("value", "获取验证码");
            $("#getBtn").attr("disabled", false);
            $("#getBtn").css("color", "#28B779");
        } else {
            $("#getBtn").attr("value", time + " S后重新获取");
            setTimeout(changeTime, 1000);
        }
    }
    $("#tab1 input[type=text],#tab1 input[type=password]").focus(function() {
        $(this).css("border-color", "#999");
        $("#tips").text("");
    });
    /** 输入框输入改变事件 */
    $("#tab1 input[type=text],#tab1 input[type=password]").bind("input", function() {
        $(this).css("border-color", "#999");
        $("#tips").text("");
    });
    /** 执行AJAX操作 */
    function Ajax(url, data) {
        var result = null;
        $.ajax({
            url:url,
            data:data,
            type:"post",
            async:false,
            dataType:"json",
            success:function(data) {
                result = data;
            }
        });
        return result;
    }
});
