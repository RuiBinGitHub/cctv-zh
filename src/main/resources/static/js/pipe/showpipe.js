$(document).ready(function() {
	
	var itemindex = -1;
    var path = "http://127.0.0.1:8080/cctvImage/";
	/** ******************************************************************** */
    var id = $("#id").val();
    var no = $("#no").val();
    var size = $("#size").val();
    /** ******************************************************************** */
    $("#video").click(function() {
        if ($(this).attr("src") != undefined && $(this).attr("src") != "")
            this.paused ? this.play() : this.pause();
    });
    $("#video").dblclick(function() {
        $("#file1").click();
    });
    $("#file1").change(function() {
        if (!this.files || !this.files[0])
            return false;
        var url = getURL(this.files[0]);
        $("#video").attr("src", url);
        $("#video").attr("poster", "");
        this.value = "";
    });
    /** ******************************************************************** */
    // 上下管道翻页
    if (no == 1)
        $("#control").hide();
    if (Number(no) >= Number(size))
        $("#contro2").hide();
    $("#control").click(function() {
        var value = Number(no) - 1;
        window.location.href = "showpipe?id=" + id + "&no=" + value;
    });
    $("#contro2").click(function() {
        var value = Number(no) + 1;
        window.location.href = "showpipe?id=" + id + "&no=" + value;
    });
    /** ******************************************************************** */
    var reg = /^[1-9]?\d|100*$/;
    $("#pagebtn").click(function() {
        var index = $("#pagebox").val();
        if (index == "" || !reg.test(index))
            showTips("请输入正确的页码！");
        else {
            var param = "id=" + id + "&no=" + index;
            window.location.href = "showpipe?" + param;
        }
    });
    $("#pagebox").keydown(function() {
        if (event.keyCode == "13")
            $("#pagebtn").click();
    });
    /** ******************************************************************** */
    /** 根据文件获取路径 */
    function getURL(file) {
        var url = null;
        if (window.createObjectURL != undefined)
            url = window.createObjectURL(file);
        else if (window.URL != undefined)
            url = window.URL.createObjectURL(file);
        else if (window.webkitURL != undefined)
            url = window.webkitURL.createObjectURL(file);
        return url;
    }
    /** ******************************************************************** */
    $("#tab3 tbody tr").each(function(i) {
        $(this).unbind("click");
        $(this).click(function() {
            itemindex = i;
            $("#tab3 tbody tr a").text("");
            $(this).find("td:eq(0) a").text("▶");
            var value = $(this).find("td:last").text();
            if (value != "" && value.length < 40)
                $("#image").attr("src", path + value + ".png");
            else
                $("#image").attr("src", "/cctv/img/00001.png");
        });
    });
    $("#tab3 tbody tr").eq(0).click();
    /** 显示提示信息 */
    function showTips(text) {
        $("#Tip").show().delay(1800).hide(200);
        $("#Tip").text(text);
    }
});
