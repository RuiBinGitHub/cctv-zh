$(document).ready(function() {
	
	var itemindex = -1;
    var path = "http://127.0.0.1:8080/cctvImage/";
	/** ******************************************************************** */
	$("#tab1 input[name=date]").attr("readonly", true);
    $("#tab1 input[name=date]").focus(function() {
        laydate();
    });
	/** ******************************************************************** */
    var size = $("#size").val();
    var paraID = $("#id").val();
    var paraNO = $("#no").val() =="" ? 1 : $("#no").val();
    var pipeID = $("#form1 input[name = id]").val();
    $("#mainTop input[type=button]:eq(0)").click(function() {
        window.location.href = "insert?id=" + paraID + "&no=" + size;
    });
    $("#mainTop input[type=button]:eq(1)").click(function() {
        if (!confirm("确定要删除该数据吗?"))
        	return false;
    	$(this).css("background-color", "#CCC");
    	$(this).attr("disabled", true);
        if (Ajax("delete", {id: pipeID}))
        	showTips("数据删除成功！");
        setTimeout("location.reload()", 2000);
    });
    /** ******************************************************************** */
    $("#video").click(function() {
        if ($(this).attr("src") != undefined && $(this).attr("src") != "")
            this.paused ? this.play() : this.pause();
    });
    $("#video").dblclick(function() {
        $("#file1").click();
    });
    $("#image").dblclick(function() {
        $("#file2").click();
    });
    $("#file1").change(function() {
        if (!this.files || !this.files[0])
            return false;
        var url = getURL(this.files[0]);
        $("#video").attr("src", url);
        $("#video").attr("poster", "");
        this.value = "";
    });
    $("#file2").change(function() {
        if (!this.files && this.files[0])
            return false;
        var url = getURL(this.files[0]);
        $("#image").attr("src", url);
        var img = new Image();
        img.src = $("#image").attr("src");
        img.onload = function() {
            var canvas1 = $("#canvas1")[0];
            var context1 = canvas1.getContext("2d");
            $(canvas1).attr("width", img.width);
            $(canvas1).attr("height", img.height);
            context1.drawImage($("#image")[0], 0, 0);
            this.value = "";
        };
    });
    /** ******************************************************************** */
    // 上下管道翻页
    if (paraNO == 1)
        $("#control").hide();
    if (Number(paraNO) >= Number(size))
        $("#contro2").hide();
    $("#control").click(function() {
        var value = Number(paraNO) - 1;
        window.location.href = "makepipe?id=" + paraID + "&no=" + value;
    });
    $("#contro2").click(function() {
        var value = Number(paraNO) + 1;
        window.location.href = "makepipe?id=" + paraID + "&no=" + value;
    });
    /** ******************************************************************** */
    var reg = /^[1-9]?\d|100*$/;
    $("#pagebtn").click(function() {
        var index = $("#pagebox").val();
        if (index == "" || !reg.test(index))
            showTips("请输入正确的页码！");
        else {
            var param = "id=" + paraID + "&no=" + index;
            window.location.href = "makepipe?" + param;
        }
    });
    $("#pagebox").keydown(function() {
        if (event.keyCode == "13")
            $("#pagebtn").click();
    });
    /** ******************************************************************** */
    $(".combtn").click(function() {
        if (checkPipe() && chcheItem()) {
            Ajax("update", $("#form1").serialize());
            showTips("数据修改成功！");
            setTimeout("location.reload()", 1500);
            $(this).css("background-color", "#CCC");
            $(this).attr("disabled", true);
        } else 
        	showTips("请检查输入数据");
    });
    /** ******************************************************************** */
    var reg = /^[1-9]?\d|100*$/;
    /** 验证管道数据 */
    function checkPipe() {
        var result = true;
        $("#tab1 tr input").trigger("input");
        $("#tab1 tr input").each(function() {
        	var color = $(this).css("background-color");
            if (color == "rgb(255, 0, 0)")
                result = false;
        });
        return result;
    }
    /** 验证记录数据 */
    function chcheItem() {
    	var result = true;
    	$("#tab3 tbody tr input").trigger("input");
        $("#tab3 tbody tr input").each(function() {
        	var color = $(this).css("background-color");
            if (color == "rgb(255, 0, 0)")
                result = false;
        });
        return result;
    }
    /** ******************************************************************** */
    // 管道编号
    $("#tab1 tr:eq(0) input:eq(1),#tab1 tr:eq(0) input:eq(2)").on("input", function() {
        if ($(this).val() == "")
            $(this).css("background-color", "#f00");
        else
            $(this).css("background-color", "#fff");
    });
    // 敷设年代
    $("#tab1 tr:eq(1) input:eq(0)").on("input", function() {
        if ($(this).val() == "" || !reg.test($(this).val()))
            $(this).css("background-color", "#f00");
        else
            $(this).css("background-color", "#fff");
    });
    // 起点埋深和终点埋深
    $("#tab1 tr:eq(1) input:eq(1),#tab1 tr:eq(1) input:eq(2)").on("input", function() {
        if (isNaN($(this).val()))
            $(this).css("background-color", "#f00");
        else
            $(this).css("background-color", "#fff");
    });
    // 管道直径
    $("#tab1 tr:eq(2) input:eq(2)").on("input", function() {
        if ($(this).val() == "" || isNaN($(this).val()))
            $(this).css("background-color", "#f00");
        else
            $(this).css("background-color", "#fff");
    });
    // 管道长度和检测长度
    $("#tab1 tr:eq(3) input:eq(0),#tab1 tr:eq(3) input:eq(1)").on("input", function() {
        if ($(this).val() == "" || isNaN($(this).val()))
            $(this).css("background-color", "#f00");
        else
            $(this).css("background-color", "#fff");
    });
    // 检测地点和检测日期
    $("#tab1 tr:eq(4) input:eq(0),#tab1 tr:eq(4) input:eq(1)").on("input", function() {
        if ($(this).val() == "")
            $(this).css("background-color", "#f00");
        else
            $(this).css("background-color", "#fff");
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
    $("#itemMemu input[type=button]:eq(0)").click(function() {
        $("#tab3 tbody").append(getContext());
        var length = $("#tab3 tbody tr").length - 1;
        initItemList(length);
    });
    $("#itemMemu input[type=button]:eq(1)").click(function() {
        $("#tab3 tbody tr").eq(itemindex).before(getContext());
        initItemList(itemindex);
    });
    $("#itemMemu input[type=button]:eq(2)").click(function() {
    	if (itemindex == -1)
    		showTips("记录列表为空！");
    	else if (confirm("确定删除该数据吗?")) {
	        var line = $("#tab3 tbody tr").eq(itemindex);
	        var itemID = line.find("[type=hidden]").val();
	        if (itemID != "" && itemID != 0)
	           Ajax("/cctv/item/delete", {id: itemID});
	        $("#tab3 tbody tr").eq(itemindex).remove();
	        initItemList(itemindex);
    	}
    });
    /** 获取Context */
    function getContext() {
        var context = "<tr>";
        context += "  <td><input type='hidden' value='0'/><a></a></td>";
        context += "  <td><input type='text'/></td>";
        context += "  <td><input type='text'/></td>";
        context += "  <td><input type='text'/></td>";
        context += "  <td><input type='text'/></td>";
        context += "  <td><input type='text'/></td>";
        context += "  <td><input type='text'/></td>";
        context += "  <td><input type='text'/></td>";
        context += "</tr>";
        return context;
    }
    /** ******************************************************************** */
    // 视频截图
    $("#itemMemu input[type=button]:eq(3)").click(function() {
        if ($("#video").attr("src") == undefined)
            return false;
        var video = $("#video")[0];
        var canvas = $("#canvas1")[0];
        var context1 = canvas.getContext("2d");
        $(canvas).attr("width", video.videoWidth);
        $(canvas).attr("height", video.videoHeight);
        context1.drawImage(video, 0, 0);
        $("#image").attr("src", canvas.toDataURL("image/png"));
    });
    // 保存图片
    $("#itemMemu input[type=button]:eq(4)").click(function() {
    	if (itemindex == -1)
    		return false;
    	var canvas1 = $("#canvas1")[0];
        var context1 = canvas1.getContext("2d");
        var ImageData = canvas1.toDataURL("image/png");
        ImageData = ImageData.substring(22, ImageData.length);
        $("#tab3 tbody tr").eq(itemindex).find("td:eq(5) input").val("#待保存#");
        $("#tab3 tbody tr").eq(itemindex).find("td:eq(7) input").val(ImageData);
    });
    // 删除图片
    $("#itemMemu input[type=button]:eq(5)").click(function() {
    	var path = $("#tab3 tbody tr").eq(itemindex).find("td:eq(7) input").val();
    	if (path == "" || !confirm("确定删除图片吗?"))
    		return false;
    	$("#tab3 tbody tr").eq(itemindex).find("td:eq(5) input").val("");
        $("#tab3 tbody tr").eq(itemindex).find("td:eq(7) input").val("");
        $("#image").attr("src", "/cctv/img/00001.png");
    });
    $("#itemMemu input[type=button]:eq(6)").click(function() {
    	showTips("功能维护中...");
    });
    /** ******************************************************************** */
    initItemList(0);
    var codelist = new Array();
    $("#codes option").each(function(){
    	codelist.push($(this).val());
    });
    function initItemList(index) {
        itemindex = -1;
        $("#tab3 tbody tr").each(function(i) {
        	$(this).find("td:eq(1) input[type=text]").css("width", "75px");
        	$(this).find("td:eq(1) input[type=text]").css("text-align", "right");
        	$(this).find("td:eq(1) input[type=text]").css("padding-right", "6px");
            $(this).find("td:eq(2) input[type=text]").attr("list", "codes");
            $(this).find("td:eq(6) input[type=text]").attr("list", "conts");
            $(this).find("td:last").css("display", "none");
            /***************************************************************************/
            $(this).find("td:eq(0) input").attr("name", "items[" + i + "].id");
            $(this).find("td:eq(1) input").attr("name", "items[" + i + "].dist");
            $(this).find("td:eq(2) input").attr("name", "items[" + i + "].code");
            $(this).find("td:eq(3) input").attr("name", "items[" + i + "].grade");
            $(this).find("td:eq(4) input").attr("name", "items[" + i + "].location");
            $(this).find("td:eq(5) input").attr("name", "items[" + i + "].picture");
            $(this).find("td:eq(6) input").attr("name", "items[" + i + "].remarks");
            $(this).find("td:eq(7) input").attr("name", "items[" + i + "].path");
            /***************************************************************************/
            $(this).unbind("click");
            $(this).click(function() {
                itemindex = i;
                $("#tab3 tbody tr a").text("");
                $(this).find("td:eq(0) a").text("▶");
                var value = $(this).find("input:last").val();
                if (value != "" && value.length < 40)
                    $("#image").attr("src", path + value + ".png");
                else
                    $("#image").attr("src", "/cctv/img/00001.png");
            });
            $(this).find("td").each(function(j) {
            	$(this).attr("tabindex", i * 8 + j + 1);
            });
            $(this).find("td").unbind();
            // TD获取焦点事件
            $(this).find("td").focus(function(){
            	$(this).parents("tr").click();
            	$(this).css("background-color", "#0078D8");
            	$(this).children().css("background-color", "#0078D8");
            });
            // TD失去焦点事件
            $(this).find("td").blur(function(){
            	$(this).css("background-color", "#ffffff");
            	$(this).children().css("background-color", "#ffffff");
            });
            $(this).find("td").keydown(function(event) {
            	var index = $(this).parents("tr").find("td").index($(this));
            	if (event.keyCode == 9) {
            		$(this).focus();
            	} else if (event.keyCode == 37) {
            		$(this).prev().focus();
            	} else if (event.keyCode == 39) {
            		$(this).next().focus();
            	} else if (event.keyCode == 38 && i > 0) {
            		$("#tab3 tbody tr").eq(i - 1).find("td").eq(index).focus();
            		$("#tab3 tbody tr").eq(i - 1).click();
            	} else if (event.keyCode == 40) {
            		if (i == $("#tab3 tbody tr").length - 1)
                    	$("#itemMemu input[type=button]:eq(0)").click();
            		$("#tab3 tbody tr").eq(i + 1).find("td").eq(index).focus();
            	} else 
                	$(this).find("input").focus();
            });
            $(this).find("td input").unbind();
            $(this).find("td input").focus(function(){
            	$(this).css("background-color", "#fff");
            	$(this).find("input").css("background-color", "#fff");
            	$(this).select();
            });
            $(this).find("td input").keydown(function(event) {
            	event.stopPropagation();
            	if (event.keyCode == 9){
            		$(this).parent().focus();
            	} else if(event.keyCode == 38 && i > 0){
            		var index = $(this).parents("tr").find("td").index($(this).parent());
            		$("#tab3 tbody tr").eq(i - 1).find("td").eq(index).focus();
            	} else if(event.keyCode == 40){
            		if (i == $("#tab3 tbody tr").length - 1)
                    	$("#itemMemu input[type=button]:eq(0)").click();
            		var index = $(this).parents("tr").find("td").index($(this).parent());
            		$("#tab3 tbody tr").eq(i + 1).find("td").eq(index).focus();
            	}
            });
            /** 距离输入数字 */
            $(this).find("td:eq(1) input").unbind("input");
            $(this).find("td:eq(1) input").bind("input", function() {
                if ($(this).val() == "" || isNaN($(this).val()))
                    $(this).css("background-color", "#FF0000");
                else
                    $(this).css("background-color", "#FFFFFF");
            });
            /** 输入缺陷代码 */
            $(this).find("td:eq(2) input").unbind("input");
            $(this).find("td:eq(2) input").bind("input", function() {
            	$(this).val($(this).val().toUpperCase());
            	if ($(this).val() == "") {
            		$(this).parents("tr").find("td:eq(3) input").val("");
                    $(this).css("background-color", "#FFFFFF");
                } else if (codelist.indexOf($(this).val()) != -1) {
                	$(this).parents("tr").find("td:eq(3) input").val("1");
                    $(this).css("background-color", "#FFFFFF");
                } else if ($(this).val().indexOf("KS") != -1){
                	$(this).parents("tr").find("td:eq(3) input").val("1");
                    $(this).css("background-color", "#FFFFFF");
                } else if ($(this).val().indexOf("JS") != -1){
                	$(this).parents("tr").find("td:eq(3) input").val("1");
		            $(this).css("background-color", "#FFFFFF");
		        } else {
		        	$(this).parents("tr").find("td:eq(3) input").val("");
                	$(this).css("background-color", "#FF0000");
                }
            });
            /** 等级只能输入数字 */
            $(this).find("td:eq(3) input").unbind("input");
            $(this).find("td:eq(3) input").bind("input", function() {
            	if (isNaN($(this).val()))
                    $(this).css("background-color", "#FF0000");
                else if (Number($(this).val()) > 4)
                	$(this).css("background-color", "#FF0000");
                else
                    $(this).css("background-color", "#FFFFFF");
            });
            /** 等级只能输入数字 */
            /*
            $(this).find("td:eq(5) input").unbind("input");
            $(this).find("td:eq(5) input").bind("input", function() {
            	if (isNaN($(this).val()))
                    $(this).css("background-color", "#FF0000");
                else
                    $(this).css("background-color", "#FFFFFF");
            });
            */
        });
        /** 执行行点击事件 */
        if (index > $("#tab3 tbody tr").length - 1)
            index = $("#tab3 tbody tr").length - 1;
        $("#tab3 tbody tr").eq(index).click();
    }
    /** 显示提示信息 */
    function showTips(text) {
        $("#Tip").show().delay(1800).hide(200);
        $("#Tip").text(text);
    }
    /** 执行Ajax请求 */
    function Ajax(url, data) {
        var result = null;
        $.ajax({
            url:url,
            data:data,
            type:"post",
            async:false,
            datatype:"json",
            success:function(data) {
                result = data;
            }
        });
        return result;
    }
});
