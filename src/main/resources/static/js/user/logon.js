$(document).ready(function() {
    $("#logo img").attr("title", "用户登录");
    /********************************************************************/
    $("#tab1 input[type=text]:eq(0)").attr("placeholder", "用户昵称，2-10位");
    $("#tab1 input[type=text]:eq(1)").attr("placeholder", "6-16位，由数字和字母组成");
    $("#tab1 input[type=password]:eq(0)").attr("placeholder", "6-16位，由数字和字母组成");
    $("#tab1 input[type=password]:eq(1)").attr("placeholder", "6-16位，由数字和字母组成");
    $("#tab1 input[type=text]:eq(2)").attr("placeholder", "请输入公司名称");
    /********************************************************************/
    $(".combtn").click(function() {
        if (!checkNickName())
            return false;
        if (!checkUserName() || !checkPassWord())
            return false;
        if (!checkCompany())
            return false;
        $("#form1").submit();
    });

    /** 检测昵称 */
    function checkNickName() {
        var nickname = $("#tab1 input[type=text]:eq(0)").val();
        if (nickname.length < 2 || nickname.length > 10) {
            $("#tab1 input[type=text]:eq(0)").css("border-color", "#f00");
            $("#tips").text("*用户名称格式不正确！");
            return false;
        }
        return true;
    }
    /** 检测账号 */
    function checkUserName() {
        var name = $("#tab1 input[type=text]:eq(1)").val();
        if (name.length < 6 || name.length > 16) {
            $("#tab1 input[type=text]:eq(1)").css("border-color", "#f00");
            $("#tips").text("*登录账号格式不正确！");
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
            $("#tips").text("*登录密码格式不正确！");
            return false;
        }
        if (password1 != password2) {
            $("#tab1 input[type=password]:eq(0)").css("border-color", "#f00");
            $("#tab1 input[type=password]:eq(1)").css("border-color", "#f00");
            $("#tips").text("*两次密码不一致,请重新输入！");
            return false;
        }
        return true;
    }

    /** 检查公司 */
    function checkCompany() {
        if ($("#tab1 input[type=text]:eq(2)").val() == "") {
            $("#tab1 input[type=text]:eq(2)").css("border-color", "#f00");
            $("#tips").text("*请输入公司名称！");
            return false;
        }
        return true;
    }
    /** 输入框输入改变事件 */
    $("#tab1 input[type=text]").bind("click", function() {
        $(this).css("border-color", "#999");
        $("#tips").text("");
    });
    $("#tab1 input[type=password]").bind("click", function() {
        $("input[type=password]").css("border-color", "#999");
        $("#tips").text("");
    });
    
});
