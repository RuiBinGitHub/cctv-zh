$(document).ready(function() {

    $(".textbox:eq(0)").attr("placeholder", "请输入设备的名称");
    $(".textbox:eq(1)").attr("placeholder", "请输入设备的型号");
    $(".textbox:eq(2)").attr("placeholder", "请输入设备生产厂家");
    $(".textbox:eq(3)").attr("placeholder", "请输入设备出厂证书编号");
    $(".textbox:eq(4)").attr("placeholder", "请输入设备校准证书编号");
    $("#tab1 input[name=term]").attr("readonly", true);
    $("#tab1 input[name=term]").focus(function() {
        laydate();
    });
    /********************************************************************/
    var myDate = new Date();
    var y = myDate.getFullYear();
    var m = myDate.getMonth() + 1;
    var d = myDate.getDate();
    m = m < 10 ? "0" + m : m;
    d = d < 10 ? "0" + d : d;
    var text = y + "-" + m + "-" + d;
    $("#tab1 input[name=term]").val(text);
    /********************************************************************/
    /** 提交数据 */
    $("#tab1 .combtn").click(function() {
        if ($(".textbox:eq(0)").val() == "") {
            $(".textbox:eq(0)").css("border-color", "#F00");
            showTips("请输入设备的名称！");
            return false;
        }
        if ($(".textbox:eq(1)").val() == "") {
            $(".textbox:eq(1)").css("border-color", "#F00");
            showTips("请输入设备的型号！");
            return false;
        }
        if ($(".textbox:eq(2)").val() == "") {
            $(".textbox:eq(2)").css("border-color", "#F00");
            showTips("请输入设备生产厂家！");
            return false;
        }
        if ($(".textbox:eq(3)").val() == "") {
            $(".textbox:eq(3)").css("border-color", "#F00");
            showTips("请输入设备出厂编号！");
            return false;
        }
        if ($(".textbox:eq(4)").val() == "") {
            $(".textbox:eq(4)").css("border-color", "#F00");
            showTips("请输入设备证书编号！");
            return false;
        }
        $("#form1").submit();
    });
    /** 输入框获取焦点事件 */
    $("#tab1 input[type=text]").focus(function() {
        $(this).css("border-color", "#999");
    });
    /** 显示提示信息 */
    function showTips(text) {
        $("#Tip").text(text);
        $("#Tip").show().delay(1800).hide(200);
    }
});
