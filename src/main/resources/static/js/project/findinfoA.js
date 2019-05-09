$(document).ready(function() {

    $("#tab2 .textbox:eq(1)").attr("list", "list");
    $("#tab2 input[class=datebox]").attr("readonly", true);
    $("#tab2 input[class=datebox]").focus(function() {
        laydate();
    });
    $(".combtn").click(function() {
        /** tab1数据 */
        if ($("#tab1 .textbox:eq(0)").val() == "") {
            $("#tab1 .textbox:eq(0)").css("border-color", "#f00");
            showTips("请输入管理单位名称！");
            return false;
        }
        if ($("#tab1 .textbox:eq(1)").val() == "") {
            $("#tab1 .textbox:eq(1)").css("border-color", "#f00");
            showTips("请输入施工单位名称！");
            return false;
        }
        if ($("#tab1 .textbox:eq(2)").val() == "") {
            $("#tab1 .textbox:eq(2)").css("border-color", "#f00");
            showTips("请输入设计单位名称！");
            return false;
        }
        if ($("#tab1 .textbox:eq(3)").val() == "") {
            $("#tab1 .textbox:eq(3)").css("border-color", "#f00");
            showTips("请输入监理单位名称！");
            return false;
        }
        if ($("#tab1 .textbox:eq(4)").val() == "") {
            $("#tab1 .textbox:eq(4)").css("border-color", "#f00");
            showTips("请输入质量监督站名称！");
            return false;
        }
        /** tab2数据 */
        if ($("#tab2 .textbox:eq(0)").val() == "") {
            $("#tab2 .textbox:eq(0)").css("border-color", "#f00");
            showTips("请输入项目检测范围！");
            return false;
        }
        if ($("#tab2 .textbox:eq(1)").val() == "") {
            $("#tab2 .textbox:eq(1)").css("border-color", "#f00");
            showTips("请输入项目检测目的！");
            return false;
        }
        var date1 = $("#tab2 .datebox:eq(0)").val();
        var date2 = $("#tab2 .datebox:eq(1)").val();
        if (date1 == "") {
            $("#tab2 .datebox:eq(0)").css("border-color", "#f00");
            showTips("请选择开始检测日期！");
            return false;
        }
        if (date2 == "") {
            $("#tab2 .datebox:eq(1)").css("border-color", "#f00");
            showTips("请选择结束检测日期！");
            return false;
        }
        if (date1 > date2) {
            $("#tab2 .datebox:eq(0)").css("border-color", "#f00");
            $("#tab2 .datebox:eq(1)").css("border-color", "#f00");
            showTips("开始时间大于结束时间！");
            return false;
        }
        $("#form1").submit();
    });
    $(".textbox").on("input", function() {
        $(this).css("border-color", "#999")
    });
    $(".datebox").on("click", function() {
    	$(".datebox").css("border-color", "#999")
    });
    /** 显示提示信息 */
    function showTips(text) {
        $("#Tip").show().delay(1800).hide(200);
        $("#Tip").text(text);
    }
});
