<%--
  Created by IntelliJ IDEA.
  User: zhiyou
  Date: 15-3-14
  Time: 下午3:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>班级信息管理</title>
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.6/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.6/themes/icon.css">
    <script type="text/javascript" src="jquery-easyui-1.3.6/jquery.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.6/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.6/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="vakata-jstree-2f630b4%20(2)/dist/jstree.js"></script>
    <script type="text/javascript">
        var url;

        //todo 根据输入的班级名，搜索数据，（模糊查询）
        $(document).ready(function(){
           $("#seacher").click(function(){
            $('#dg').datagrid('load',{//todo 调用了load方法当点击这个这个#seacher就会触发该函数
                gradeName:$('#s_gradeName').val()//todo 调用datagrid中的load方法，传一个参数，根据参数来搜索数据
            });
           });
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
                strIds.push(selectedRows[i].id);//todo 每遍历一条把它放到
            }
            var ids = strIds.join(',');//todo 遍历的每个strIds之间都添加一个','
               $.messager.confirm("系统提示","您确定要删除<font color='red'>"+selectedRows.length+"</font>条信息吗",function(r){
                   if(r){
                       $.post("grateDelete",{deiLds:ids},function(result){//todo post有四个参数 1.请求的url  2.要传递的参数（可以是键值对，也可以是序列化字符串），3.数据加载后要执行的函数 4,执行完后返回的数据类型（html，json）
                           if(result.success){
                               $.messager.alert("系统提示","您已成功删除<font color='red'>"+result.delNums+"</font>条记录");
                               $("#dg").datagrid("reload");//如果删除成功给出提示并且刷新#dg表
                           }
                           else{

                               $.messager.alert("系统提示","<font color='red'>"+selectedRows[result.errorindex].gradeName+"</font>"+result.errorMas);
                              // $.messager.alert("系统提示","您删除的班级下有学生");
                           }
                       },"json");
                   }
               });
           });
        });

        //弹出对话框
        $(document).ready(function(){
            $("#add").click(function(){
                $("#dlg").dialog("open").dialog("setTitle","添加班级信息");//todo 调用dialog方法,点击弹出一个对话框，
                url="grateSave";
            })
        });
        //关闭对话框
        $(document).ready(function(){
            $("#close").click(function(){
                $("#dlg").dialog("close");//关闭对话框
                clear();
            })
        });
        function clear(){
            $("#gradeName").val("");//清空对话框中所填内容
            $("#gradeDesc").val("");
        }

        //保存对话框里的内容
        $(document).ready(function(){
            $("#save").click(function(){
                $("#fm").form("submit",{
                    url:url,
                    onSubmit:function(){
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
                            $("#dg").datagrid("reload");
                        }
                    }
                })
            })
        });

        //修改数据
        $(document).ready(function(){
            //selectedRows获取的是一个二维的数组

            $("#modification").click(function(){
                var selectedRows = $("#dg").datagrid('getSelections');//创建了一个对象，对象的内容，把是你选的所有的内容
                if(selectedRows.length!=1){//假如用户一个也没选，给用户一个友情提醒
                    $.messager.alert("系统提示","请选择一条要修改的数据");
                    return;
                }
                var row = selectedRows[0];//取出selectedRows二维数组的第一条数据
                $("#dlg").dialog("open").dialog("setTitle","修改班级信息");
                $("#fm").form("load",row);//load方法会把row的值添加到form表单中
                url = "grateSave?id="+row.id;
            })
        })
    </script>


</head>
<body style="margin: 5px">

  <table id="dg" title="班级信息" class="easyui-datagrid" fitColumns="true"  pagination="true"
         rownumbers="true" url="gratelist"  toolbar="#tb">
      <thead>
        <tr>
            <th field="cb" checkbox="true"></th>
            <th field="id" width="50">编号</th>
            <th field="gradeName" width="100">班级名称</th>
            <th field="gradeDesc" width="250">班级描述</th>
        </tr>
      </thead>
  </table>
  <div id="tb">
      <div>
          <a href="javascript:openGradeAdd()" id="add" class="easyui-linkbutton" iconCls="icon-add"plain="true">添加</a>
          <a href="javascript:modification()" id="modification" class="easyui-linkbutton" iconCls="icon-edit"plain="true">修改</a>
          <a href="javascript:gradDelete()" id="delete" class="easyui-linkbutton" iconCls="icon-remove"plain="true">删除</a>
      </div>
      <div>&nbsp;班级名称&nbsp;<input type="text" name="s_gradName" id="s_gradeName">
          <a href="javascript:searchgrade()" id="seacher" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
      </div>
      <div id="dlg" class="easyui-dialog" style="width:400px;height:300px;padding:10px 20px;"
              closed="true" buttons="#dlg-button">
            <form id="fm" method="post">
                <table>
                <tr>
                    <th>班级名称： </th>
                        <th><label for="gradeName"></label><input type="text" name="gradeName" id="gradeName" class="easyui-validatebox" required="true"></th>

                </tr>

                <tr>
                    <th valign="top">班级描述:</th>
                    <th><label for="gradeDesc"></label><textarea rows="5" cols="12" name="gradeDesc" id="gradeDesc"></textarea></th>
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
