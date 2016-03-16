<%--
  Created by IntelliJ IDEA.
  User: zhiyou
  Date: 15-3-14
  Time: 下午3:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息管理</title>

    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.6/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.6/themes/icon.css">
    <script type="text/javascript" src="jquery-easyui-1.3.6/jquery.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.6/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.6/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="vakata-jstree-2f630b4%20(2)/dist/jstree.js"></script>
    <script type="text/javascript">
        //todo 根据你输入的条件，来搜索出数据，查寻
        var url;
        $(document).ready(function(){
            $("#seacher").click(function(){
                $('#dg').datagrid('load',{
                stuNo:$("#s_stuNo").val(),
                stuName:$("#s_stuName").val(),
                sex:$("#s_sex").combobox("getValue"),
                bbirthday:$("#s_bbirthday").datebox("getValue"),
                ebirthday:$("#s_ebirthday").datebox("getValue"),
                gradeId:$("#s_gradeId").combobox("getValue")

            });
            })
        });

        //todo 根据你所选项删除相应数据，可以批量删除。
        $(document).ready(function(){
            $("#delete").click(function(){
                var selectedRows = $("#dg").datagrid('getSelections');//todo 创建了一个对象，对象的内容（选出来的结果是一个数组），把是你选的所有的内容
                if(selectedRows.length==0){//假如用户一个也没选，给用户一个友情提醒
                    $.messager.alert("系统提示","请选择要删除的选项");
                    return;
                }
                var strIds=[];//todo 因为支持批量删除，所以要定义一个数组
                for(var i=0;i<selectedRows.length;i++){//todo 遍历通过datagrid的getSelections方法查查询出来的数据
                    strIds.push(selectedRows[i].id);//todo 可以直接取出id，每遍历一条把它放到，注意连表的时候，要注意
                }
                var ids = strIds.join(',');//todo 遍历的每个strIds之间都添加一个','
                $.messager.confirm("系统提示","您确定要删除<font color='red'>"+selectedRows.length+"</font>条信息吗",function(r){
                    if(r){
                        $.post("studentDelete",{deiLds:ids},function(result){//todo post有四个参数 1.请求的url  2.要传递的参数（可以是键值对，也可以是序列化字符串），3.数据加载后要执行的函数 4,执行完后返回的数据类型（html，json）
                            if(result.success){//
                                $.messager.alert("系统提示","您已成功删除<font color='red'>"+result.delNums+"</font>条记录");
                                $("#dg").datagrid("reload");//如果删除成功给出提示并且刷新#dg表
                            }
                            else{
                                alert("删除失败");
                            }
                        },"json");
                    }
                });
            });
        });

        //弹出对话框
        $(document).ready(function(){
            $("#add").click(function(){
                $("#dlg").dialog("open").dialog("setTitle","添加学生信息");//todo 调用dialog方法,点击弹出一个对话框，
                url="studentAdd";
            })
        });

        //关闭对话框
        $(document).ready(function(){
            $("#close").click(function(){
                $("#dlg").dialog("close");//关闭对话框
                clear();
            })
        });
        //关闭后清空对话框的内容
        function clear(){
            $("#stuNo").val("");//清空对话框中所填内容
            $("#stuName").val("");
            $("#sex").combobox("setValue","");
            $("#birthday").datebox("setValue","");
            $("#gradeId").combobox("setValue","");
            $("#email").val("");
            $("#stuDesc").val("");
        }

        //todo 添加学生信息
        $(document).ready(function(){
            $("#save").click(function(){
                $("#fm").form("submit",{
                    url:url,
                    onSubmit:function(){

                        //todo 验证一下性别是否已选

                        if($("#sex").combobox("getValue")==""){
                            $.messager.alert("系统提示","请选择性别");
                            return false;
                        }
                        //this指的是提交的form表单
                        return $(this).form("validate");//当form表单提交是会调用validate方验证一下所填内容是否为空，
                    },
                    success:function(result){
                        if(result.errorMsg){
                            $.messager.alert("系统信息","保存失败");
                            return;
                        }else{
                            $.messager.alert("系统信息","保存成功");
                            clear();
                            $("#dlg").dialog("close");
                            $("#dg").datagrid("reload");//todo 刷新表中数据
                        }
                    }
                })
            })
        });
        //todo 修改学生信息
        $(document).ready(function(){
            //selectedRows获取的是一个二维的数组

            $("#modification").click(function(){
                var selectedRows = $("#dg").datagrid('getSelections');//创建了一个对象，对象的内容，把是你选的所有的内容
                if(selectedRows.length!=1){//假如用户一个也没选，给用户一个友情提醒
                    $.messager.alert("系统提示","请选择一条要修改的数据");
                    return;
                }
                var row = selectedRows[0];//取出selectedRows二维数组的第一条数据
                $("#dlg").dialog("open").dialog("setTitle","修改学生信息");
                $("#fm").form("load",row);//load方法会把row的值添加到form表单中
                url = "studentAdd?id="+row.id;
            })
        })

    </script>
</head>
    <body style="margin: 5px">
    <table id="dg" title="班级信息" class="easyui-datagrid" fitColumns="true"  pagination="true"
           rownumbers="true" url="studentlist"  toolbar="#tb">
        <thead>
        <tr>
            <th field="cb" checkbox="true"></th>
            <th field="id" width="10" align="center">编号</th>
            <th field="stuNo" width="10" align="center">学号</th>
            <th field="stuName" width="25" align="center">姓名</th>
            <th field="sex" width="10" align="center">性别</th>
            <th field="User" width="25" align="center">出生日期</th>
            <th field="gradeId" width="10" align="center" hidden="true">班级id</th><!--todo 隐藏不让显示的列-->
            <th field="gradeName" width="10" align="center">班级名称</th>
            <th field="email" width="25" align="center">邮箱</th>
            <th field="stuDesc" width="10" align="center">学生描述</th>
        </tr>
        </thead>
    </table>
    <div id="tb">
        <div>
            <a href="javascript:openGradeAdd()" id="add" class="easyui-linkbutton" iconCls="icon-add"plain="true">添加</a>
            <a href="javascript:modification()" id="modification" class="easyui-linkbutton" iconCls="icon-edit"plain="true">修改</a>
            <a href="javascript:gradDelete()" id="delete" class="easyui-linkbutton" iconCls="icon-remove"plain="true">删除</a>
        </div>
        <div>&nbsp;学号&nbsp;<input type="text" name="s_stuName" id="s_stuNo" size="10">
            &nbsp;姓名&nbsp;<input type="text" name="s_stuName" id="s_stuName" size="10">
            &nbsp;姓别&nbsp;<select class="easyui-combobox" id="s_sex" name="s_sex" editable="false" panelHeight="auto">
                <option value="">请选择</option>
                <option value="男">男</option>
                <option value="女">女</option>
            </select>
            &nbsp;出生日期&nbsp;<input  class="easyui-datebox" name="s_bbirthday" id="s_bbirthday" size="10" editable="false" panelHeight="auto">-><input  class="easyui-datebox" name="s_ebirthday" id="s_ebirthday" size="10" editable="false" panelHeight="auto">
            &nbsp;所属班级&nbsp;<input type="text" class="easyui-combobox" id="s_gradeId" name="s_gradeId" size="10" editable="false" panelHeight="auto" data-options="valueField:'id',textField:'gradeName',url:'gradeComboList'">
            <a href="javascript:searchgrade()" id="seacher" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
        </div>
        <div id="dlg" class="easyui-dialog" style="width:600px;height:400px;"
             closed="true" buttons="#dlg-button">
            <form id="fm" method="post">
                <table>
                    <tr>
                        <td>学生学号</td><td>
                        <input type="text" name="stuNo" id="stuNo" class="easyui-validatebox" size="10" required="true"></td>
                        <td>学生姓名</td><td>
                       <input type="text" name="stuName" id="stuName" class="easyui-validatebox" size="10" required="true"></td>
                    </tr>
                    <tr>
                        <td>学生性别</td><td>
                       <select class="easyui-combobox" id="sex" name="sex" panelHeight="auto" editable="fales" style="width:115px">
                            <option value="">请选择...</option>
                            <option value="男">男</option>
                            <option value="女">女</option>
                        </select></td>

                        <td>出生日期</td><td><input class="easyui-datebox" name="birthday" id="birthday"  editable="false" size="10" panelHeight="auto">
                        </td>
                    </tr>
                    <tr>
                        <td>所属班级</td><td>
                        <input type="text" class="easyui-combobox" id="gradeId" name="gradeId" size="10" editable="false" panelHeight="auto" data-options="valueField:'id',textField:'gradeName',url:'gradeComboList'"></td>
                        <td>学生邮箱</td><td>
                       <input type="text" name="email" id="email" class="easyui-validatebox" required="true" size="10" validType="email"></td><!--todo validType="email"验证邮箱为邮箱邮箱-->
                    </tr>
                    <tr>
                        <td valign="top">学生描述</td>
                      <td><textarea rows="8" cols="17" name="stuDesc" id="stuDesc"></textarea></td>
                    </tr>
                </table>
            </form>
        </div>
        <div id="dlg-button">
            <a href="javascript:gradeSave" class="easyui-linkbutton" id="save" iconCls="icon-ok">保存</a>
            <a href="javascript:gradeclose()" id="close" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
        </div>
    </div>
    </body>
</html>
