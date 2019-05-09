$(document).ready(function() {

	$(".textbox:eq(0)").attr("placeholder", "请输入项目编号");
	$(".textbox:eq(1)").attr("placeholder", "请输入任务名称");
	$(".textbox:eq(2)").attr("placeholder", "请输入任务位置信息");
	$(".textbox:eq(3)").attr("placeholder", "请输入委托单位名称");
	/** ***************************************************************** */
	/** 提交数据 */
	$("#mainInfo .combtn").click(function() {
		if ($(".textbox:eq(0)").val() == "") {
			$(".textbox:eq(0)").css("border-color", "#F00");
			showTips("请输入项目编号！");
			return false;
		}
		if ($(".textbox:eq(1)").val() == "") {
			$(".textbox:eq(1)").css("border-color", "#F00");
			showTips("请输入任务名称！");
			return false;
		}
		if ($(".textbox:eq(2)").val() == "") {
			$(".textbox:eq(2)").css("border-color", "#F00");
			showTips("请输入任务地点信息！");
			return false;
		}
		if ($(".textbox:eq(3)").val() == "") {
			$(".textbox:eq(3)").css("border-color", "#F00");
			showTips("请输入委托单位名称！");
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
