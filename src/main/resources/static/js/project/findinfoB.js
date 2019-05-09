$(document).ready(function() {
	
    var list1 = $("#devicelist").val().split(",");
    var list2 = $("#personlist").val().split(",");
    $("#tab1 tbody input").attr("name", "devices");
    $("#tab2 tbody input").attr("name", "persons");
    
    $("#tab1 tbody input").each(function() {
        if (list1.indexOf($(this).val()) != -1)
            $(this).attr("checked", true);
    });
    $("#tab2 tbody input").each(function() {
        if (list2.indexOf($(this).val()) != -1)
            $(this).attr("checked", true);
    });

    $(".combtn").click(function() {
        var len1 = $("#tab1 input[type=checkbox]:checked").length;
        var len2 = $("#tab2 input[type=checkbox]:checked").length;
        if (len1 == 0 || len2 == 0)
            showTips("请选择投入设备及人员！");
        else
            $("#form1").submit();
    });
    /** 显示提示信息 */
    function showTips(text) {
        $("#Tip").show().delay(1800).hide(200);
        $("#Tip").text(text);
    }
});
