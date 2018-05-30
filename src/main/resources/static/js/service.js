$("document").ready(function () {
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
                url: "/list",
                type: "post",
                traditional: true,  //传递数组值时必须含有traditional
                data: {chk_value: chk_value},
                success: function (data) {
                    var ddl = $("#content");
                    //删除节点
                    document.getElementById("biao").style.display = "block";
                    document.getElementById("bq").style.display = "block";
                    $("#content td").remove();
                    $("#tishi").text(" 加载完成~~");
                    $.each(data, function (i, result) {
                        var item = "<tr onmouseover=\"this.style.backgroundColor='#BFEFFF';\"\n" +
                            "            onmouseout=\"this.style.backgroundColor='#d4e3e5';\" align='center'><td>" + '<input type="checkbox" name="box" id="box" value=' + result['sid'] + ' />' + "</td>" +
                            "<td name=\"unInfo\">" + result['sid'] + "</td>" +"<td>" + result['service_name'] + "</td><td>" + result['depict'] + "</td>" +
                            "<td name=\"unInfo\">" + result['process_name'] + "</td><td name=\"unInfo\">" + result['path'] + "</td>" +
                            "<td name=\"unInfo\">" + result['cmad_start'] + "</td><td name=\"unInfo\">" + result['cmad_restart'] + "</td>" +
                            "<td name=\"unInfo\">" + result['cmad_stop'] + "</td><td name=\"unInfo\">" + result['cmad_status'] + "</td>" +
                            "<td>"+ '<input checked type="checkbox" class="blx hidden" id=uncheckbox' + i +' /><label for=uncheckbox'+i+' class="back"></label>' +"</td></tr>";
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

    $("#btn7").click(function () {
        $("[name='box']").attr("checked", 'true');//全选
    });
    $("#btn9").click(function () {
        $("[name='box']").removeAttr("checked");//取消全选
    });
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
    $("#sub1").click(function () {         //提交
        var chk_value = [];
        $('input[name="box"]:checked').each(function () {
            chk_value.push($(this).val());
        });
        if (chk_value == null || chk_value == 0) {
            alert("请选择一个选项!")
        } else {
            $.ajax({
                url: "/serverdata",
                type: "post",
                traditional: true,  //传递数组值时必须含有traditional
                data: {chk_value: chk_value},
                success: function (data) {
                    $("#tishi").text("sid服务传输成功~~");
                    alert("传输成功");
                    $.each(data, function (i, result) {

                    });
                },
                error: function () {
                    alert("Error");
                }
            });
        }
    });

    $("#hidde").click(function () {
        $("[name='unInfo']").hide();
        $("#content").width(720);
    });

    $("#show").click(function () {
        $("[name='unInfo']").show();
        $("#content").width(1300);
    });
});

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
            $("#tishi").text("MID: " + tid + " 加载完成~~");
            $.each(data, function (i, result) {
                var item = "<tr onmouseover=\"this.style.backgroundColor='#BFEFFF';\"\n" +
                    "            onmouseout=\"this.style.backgroundColor='#d4e3e5';\" align='center'><td>" + '<input type="checkbox" name="box" id="box" value=' + result['sid'] + ' />' + "</td>" +
                    "<td name=\"unInfo\">" + result['sid'] + "</td>" +"<td>" + result['service_name'] + "</td><td>" + result['depict'] + "</td>" +
                    "<td name=\"unInfo\">" + result['process_name'] + "</td><td name=\"unInfo\">" + result['path'] + "</td>" +
                    "<td name=\"unInfo\">" + result['cmad_start'] + "</td><td name=\"unInfo\">" + result['cmad_restart'] + "</td>" +
                    "<td name=\"unInfo\">" + result['cmad_stop'] + "</td><td name=\"unInfo\">" + result['cmad_status'] + "</td></tr>";
                ddl.append(item);
            });
            $("[name='unInfo']").hide();
            $("#content").width(720);
        },
        error: function () {
            alert("Error");
        }
    });
}
