$(document).ready(function () {

    /**
     * 此为整个service页面的js包括,按钮,表格等
     */
    /* /!**
      * 下拉框从数据库中进行选择*!/
     var downSelect = $("#typeID");
     downSelect.append("<option value=\"\" disabled=\"disabled\">步骤:</option>");
     $.ajax({
         url: "/getSelectValue",
         type: "get",
         success: function (data) {
             $.each(data, function (i, result) {
                 var item ='<option value='+result['order_num'] +' >'+result['instructions']+"</option>";
                 downSelect.append(item);
             });
         },
         error: function () {
             alert("出错!")
         }
     });*/

    $("#btn1").click(function () {
        $("[name='check']").attr("checked", 'true');//全选
    });
    $("#btn2").click(function () {
        $("[name='check']").removeAttr("checked");//取消全选
    });
    $("#btn4").click(function () {
        $("[name='check']").each(function () {  //反选
            if ($(this).attr("checked")) {
                $(this).removeAttr("checked");
            }
            else {
                $(this).attr("checked", 'true');
            }
        })
    });
    $("#sub").click(function () {         //提交
        var chk_value = [];
        $('input[name="check"]:checked').each(function () {
            chk_value.push($(this).val());
        });
        if (chk_value == null || chk_value == 0) {
            alert("请选择一个选项!")
        } else {
            $.ajax({
                url: "/ServiceDetailList",
                type: "post",
                traditional: true,  //传递数组值时必须含有traditional
                data: {chk_value: chk_value},
                success: function (data) {
                    var ddl = $("#content");
                    //删除节点
                    document.getElementById("biao").style.display = "block";
                    document.getElementById("bq").style.display = "block";
                    $("#content td").remove();
                    $("#tishi").text(" 加载完成");
                    $("[name='area']").show();
                    $("#miaoshu").text("服务名称:");
                    $.each(data, function (i, result) {
                        var item = "<tr onmouseover=\"this.style.backgroundColor='#BFEFFF';\"\n" +
                            "            onmouseout=\"this.style.backgroundColor='#d4e3e5';\" align='center'><td>" + '<input type="checkbox" name="box" id="box" value=' + result['sid'] + ' />' + "</td>" +
                            "<td name=\"unInfo\">" + result['sid'] + "</td>" + "<td>" + result['host'] + "</td>" + "<td>" + result['area'] + "</td>" + "<td>" + result['depict'] + "</td><td name=\"unInfo\">" + result['service_name'] + "</td>" +
                            "<td name=\"unInfo\">" + result['process_name'] + "</td><td name=\"unInfo\">" + result['path'] + "</td>" +
                            "<td name=\"unInfo\">" + result['cmad_start'] + "</td><td name=\"unInfo\">" + result['cmad_restart'] + "</td>" +
                            "<td name=\"unInfo\">" + result['cmad_stop'] + "</td><td name=\"unInfo\">" + result['cmad_status'] + "</td>" +
                            "<td>" + '<input type="checkbox" name="status" onclick=getStatus(this) class="blx hidden" id=uncheckbox' + result['sid'] + ' /><label for=uncheckbox' + result['sid'] + '  class="back" id=label' + result['sid'] + '></label>' + "</td></tr>";
                        ddl.append(item);
                    });
                    $("[name='unInfo']").hide();
                    $("#content").width(700);
                },
                error: function () {
                    alert("Error");
                }
            });
        }
    });


    //服务栏机器的开启
    $("#openMachine").click(function () {
        var arr_value = [];
        $('input[name="box"]:checked').each(function () {
            arr_value.push($(this).val());
        });
        if (arr_value == null || arr_value == 0) {
            alert("请选择一个选项!")
        } else {
            $("#jiqi").text(arr_value + "---机器正在启动中!");
            $.ajax({
                url: "http://localhost:8806/batchStart",
                type: "post",
                traditional: true,
                data: {ids: arr_value}, //传数组至后台
                success: function (data) {
                    $.each(data, function (i, result) {     //对回传的json数据进行循环遍历
                        for (var k in arr_value) {          //使用for循环对数组值进行循环遍历 for in 组合, k 为系数, in后的为值 相当于for(int i=0;i<arr_value.length;i++){}
                            var str = "uncheckbox" + arr_value[k];      //拼接id值
                            var leb = "label" + arr_value[k];
                            if (arr_value[k] == result['sid']) {
                                if (result['status'] == "NORMAL") {
                                    $("#" + str).prop("checked", true);     //"#"程序辨别这个为id值
                                } else if (result['status'] == "OPER_ERR") {
                                    $("#" + leb).css("background-color", "#dd281b");
                                    $("#" + str).prop("checked", false);
                                } else {
                                    $("#" + str).prop("checked", false);
                                }
                            }
                        }
                        $("#jiqi").text("机器全部启动完成");
                    });
                },
                error: function () {
                    alert("与后台连接失败");
                }
            });
        }
    });


    //服务栏的状态获取提交
    $("#status").click(function () {         //获取tid时勾选框值的提交
        var chk_value = [];
        $('input[name="box"]:checked').each(function () {
            chk_value.push($(this).val());
        });
        if (chk_value == null || chk_value == 0) {
            alert("请选择一个选项!")
        } else {
            $("#tishi").text("正在查询中......");
            $.ajax({
                url: "http://localhost:8806/queryList",
                type: "get",
                /*traditional: true,  //传递数组值时必须含有traditional
                data: {chk_value: chk_value},*/
                success: function (data) {
                    $("#tishi").hide();
                    $("#jiqi").text("机器状态已获取");
                    $.each(data, function (i, result) {
                        for (var k in chk_value) {
                            var str = "uncheckbox" + chk_value[k];
                            var leb = "label" + chk_value[k];
                            if (chk_value[k] == result['sid']) {
                                if (result['status'] == "NORMAL") {
                                    $("#" + str).prop("checked", true);
                                    $("#" + leb).css("color", "#42d842");
                                } else if (result['status'] == "OPER_ERR") {
                                    $("#" + str).prop("checked", false);
                                    $("#" + leb).css("color", "#dd281b");
                                } else {
                                    $("#" + str).prop("checked", false);
                                }
                            }
                        }
                    });
                },
                error: function () {
                    alert("服务尚未开启");
                }
            });
        }
    });
    //服务栏的全选
    $("#btn7").click(function () {
        $("[name='box']").attr("checked", 'true');//全选
    });
    //服务栏详细页的取消全选
    $("#btn9").click(function () {
        $("[name='box']").removeAttr("checked");//取消全选
    });
    //服务栏的反选
    $("#btn8").click(function () {
        $("[name='box']").each(function () {  //反选
            if ($(this).attr("checked")) {
                $(this).removeAttr("checked");
            }
            else {
                $(this).attr("checked", 'true');
            }
        })
    });

    //隐藏按钮
    $("#hidde").click(function () {
        $("[name='unInfo']").hide();
        $("#content").width(700);
    });
    //显示按钮
    $("#show").click(function () {
        $("[name='unInfo']").show();
        $("#content").width(1300);
    });

    //选择步骤的方法
    /*$('#typeID').click(function () {
        var options = $("#typeID option:selected");
        var typeValue = options.val();
        $.ajax({
            url: "/typeValue/" + typeValue,
            type: "get",
            success: function (data) {
                var ddl = $("#content");
                //删除节点
                document.getElementById("biao").style.display = "block";
                document.getElementById("bq").style.display = "block";
                $("#content td").remove();
                $("#jiqi").text("TYPE: " + typeValue + " 加载完成");
                $("#tishi").hide();
                $("[name='area']").hide();
                $("#miaoshu").text("描述:");
                $.each(data, function (i, result) {
                    var item = "<tr onmouseover=\"this.style.backgroundColor='#BFEFFF';\"\n" +
                        "            onmouseout=\"this.style.backgroundColor='#d4e3e5';\" align='center'><td>" + '<input type="checkbox" name="box" id="box" value=' + result['sid'] + ' />' + "</td>" +
                        "<td name=\"unInfo\">" + result['sid'] + "</td>" + "<td>" + result['instructions'] + "</td>" + "<td>" + result['depict'] + "</td><td name=\"unInfo\">" + result['service_name'] + "</td>" +
                        "<td name=\"unInfo\">" + result['process_name'] + "</td><td name=\"unInfo\">" + result['path'] + "</td>" +
                        "<td name=\"unInfo\">" + result['cmad_start'] + "</td><td name=\"unInfo\">" + result['cmad_restart'] + "</td>" +
                        "<td name=\"unInfo\">" + result['cmad_stop'] + "</td><td name=\"unInfo\">" + result['cmad_status'] + "</td>" +
                        "<td>" + '<input type="checkbox" name="status" onclick=getStatus(this) class="blx hidden" id=uncheckbox' + result['sid'] + ' /><label for=uncheckbox' + result['sid'] + '  class="back" id=label' + result['sid'] + '></label>' + "</td></tr>";
                    ddl.append(item);
                });
                $("[name='unInfo']").hide();
                $("#content").width(700);
            },
            error: function () {
                alert("Error");
            }
        });
    });*/

//繁星
    var w = document.documentElement.clientWidth * 1.2;
    var h = document.documentElement.clientHeight * 1.2;
    var star = document.getElementsByClassName("stars")[0];
    var n = 2500;   //页面星星数量

//随机函数
    function randomNum(m, n) {
        return Math.floor(Math.random() * (n - m + 1) + m);
    }

    var str = '';
    for (var i = 0; i < n; i++) {
        var numX = randomNum(-w, w);
        var numY = randomNum(-h, h);
        var color = 'rgb(' + randomNum(0, 255) + ',' + randomNum(0, 255) + ',' + randomNum(0, 255) + ')';
        str += numX + 'px' + ' ' + numY + 'px' + ' ' + color + ',';
    }
    str = str.slice(0, -1);
    star.style.boxShadow = str;
//繁星
})
;

//点击状态栏时的状态
function getStatus(obj) {
    if ($("#" + obj.id).prop("checked")) {  //是否是开启状态
        if (confirm("确定要开启吗?")) {
            alert("请使用勾选框操作");
            $("#" + obj.id).prop("checked", false);
        }
        else {
            $("#" + obj.id).prop("checked", false);
        }
    } else {
        if (confirm("确定要关闭吗?")) {
            alert("暂未开通此业务!")
            $("#" + obj.id).prop("checked", true);
        } else {
            $("#" + obj.id).prop("checked", true);
        }
    }
}

function content(obj) {
    var tid = obj.id;
    $.ajax({
        url: "/content/" + tid,
        type: "post",
        contentType: "application/json",
        dataType: "json",
        success: function (data) {
            var ddl = $("#content");
            //删除节点
            document.getElementById("biao").style.display = "block";
            document.getElementById("bq").style.display = "block";
            $("#content td").remove();
            $("#tishi").text("MID: " + tid + " 加载完成");
            $("[name='area']").show();
            $("#miaoshu").text("服务名称:");
            $.each(data, function (i, result) {
                var item = "<tr onmouseover=\"this.style.backgroundColor='#BFEFFF';\"\n" +
                    "            onmouseout=\"this.style.backgroundColor='#d4e3e5';\" align='center'><td>" + '<input type="checkbox" name="box" id="box" value=' + result['sid'] + ' />' + "</td>" +
                    "<td name=\"unInfo\">" + result['sid'] + "</td>" + "<td>" + result['host'] + "</td>" + "<td>" + result['area'] + "</td>" + "<td>" + result['depict'] + "</td><td name=\"unInfo\">" + result['service_name'] + "</td>" +
                    "<td name=\"unInfo\">" + result['process_name'] + "</td><td name=\"unInfo\">" + result['path'] + "</td>" +
                    "<td name=\"unInfo\">" + result['cmad_start'] + "</td><td name=\"unInfo\">" + result['cmad_restart'] + "</td>" +
                    "<td name=\"unInfo\">" + result['cmad_stop'] + "</td><td name=\"unInfo\">" + result['cmad_status'] + "</td>" +
                    "<td>" + '<input type="checkbox" name="status" onclick=getStatus(this) class="blx hidden" id=uncheckbox' + result['sid'] + ' /><label for=uncheckbox' + result['sid'] + '  class="back" id=label' + result['sid'] + '></label>' + "</td></tr>";
                ddl.append(item);
            });
            $("[name='unInfo']").hide();
            $("#content").width(700);
        },
        error: function () {
            alert("Error");
        }
    });
}

