$(document).ready(function() {
	
	$("#tab1 input[class=datebox]").attr("readonly", true);
    $("#tab1 input[class=datebox]").focus(function() {
        laydate();
    });

    $(".combtn").click(function(){
    	if ($("#tab1 .datebox:eq(0)").val() == "") {
    		$("#tab1 .datebox:eq(0)").css("border-color", "#f00");
    		showTips("请输入进场踏勘日期！");
    		return false;
    	}
    	if ($("#tab1 .datebox:eq(1)").val() == "") {
    		$("#tab1 .datebox:eq(1)").css("border-color", "#f00");
    		showTips("请输入进场工作日期！");
    		return false;
    	}
    	if ($("#tab1 .datebox:eq(2)").val() == "") {
    		$("#tab1 .datebox:eq(2)").css("border-color", "#f00");
    		showTips("请输入离场工作日期！");
    		return false;
    	}
    	if ($("#tab1 .datebox:eq(3)").val() == "") {
    		$("#tab1 .datebox:eq(3)").css("border-color", "#f00");
    		showTips("请输入资料整理日期！");
    		return false;
    	}
    	if ($("#tab1 .datebox:eq(4)").val() == "") {
    		$("#tab1 .datebox:eq(4)").css("border-color", "#f00");
    		showTips("请输入报告编写日期！");
    		return false;
    	}
    	if ($("#tab1 .datebox:eq(0)").val() > $("#tab1 .datebox:eq(1)").val()){
    		$("#tab1 .datebox:eq(0)").css("border-color", "#f00");
    		$("#tab1 .datebox:eq(1)").css("border-color", "#f00");
    		showTips("进场踏勘日期大于工作进场日期！");
    		return false;
    	}
    	if ($("#tab1 .datebox:eq(1)").val() > $("#tab1 .datebox:eq(2)").val()){
    		$("#tab1 .datebox:eq(1)").css("border-color", "#f00");
    		$("#tab1 .datebox:eq(2)").css("border-color", "#f00");
    		showTips("工作进场日期大于工作离场日期！");
    		return false;
    	}
    	if ($("#tab1 .datebox:eq(3)").val() > $("#tab1 .datebox:eq(4)").val()){
    		$("#tab1 .datebox:eq(3)").css("border-color", "#f00");
    		$("#tab1 .datebox:eq(4)").css("border-color", "#f00");
    		showTips("资料整理日期大于报告编写日期！");
    		return false;
    	}
    	$("#form1").submit();
    });
    $(".textbox,.datebox").on("click", function() {
    	$(".textbox,.datebox").css("border-color", "#999");
	});
    /** 显示提示信息 */
	function showTips(text) {
        $("#Tip").text(text);
        $("#Tip").show().delay(1800).hide(200);
    }
	
});