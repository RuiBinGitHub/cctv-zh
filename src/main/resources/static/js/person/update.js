$(document).ready(function() {

	$(".textbox:eq(0)").attr("placeholder", "请输入人员的姓名");
    $(".textbox:eq(1)").attr("placeholder", "请输入人员的职称");
    $(".textbox:eq(2)").attr("placeholder", "操作人员证件名称");
    $(".textbox:eq(3)").attr("placeholder", "操作人员证书编号");
	/** ***************************************************************** */
	/** 提交数据 */
    $("#tab1 .combtn").click(function() {
        if ($(".textbox:eq(0)").val() == "") {
            $(".textbox:eq(0)").css("border-color", "#F00");
            showTips("请输入人员姓名！");
            return false;
        }
        if ($(".textbox:eq(1)").val() == "") {
            $(".textbox:eq(1)").css("border-color", "#F00");
            showTips("请输入人员职称！");
            return false;
        }
        if ($(".textbox:eq(2)").val() == "") {
            $(".textbox:eq(2)").css("border-color", "#F00");
            showTips("请输入证件名称！");
            return false;
        }
        if ($(".textbox:eq(3)").val() == "") {
            $(".textbox:eq(3)").css("border-color", "#F00");
            showTips("请输入证书编号！");
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
		$("#Tip").show().delay(1800).hide(200);
		$("#Tip").text(text);
	}
});
