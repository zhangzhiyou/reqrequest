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
    <script type="text/javascript" src="jquery-easyui-1.3.6/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.6/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="vakata-jstree-2f630b4%20(2)/dist/jstree.js"></script>
</head>
<body style="margin: 5px">
  <table id="dg" title="班级信息" class="easyui-datagrid" fitColumns="true"  pagination="true"
         rownumbers="true" url="gratelist" fit="true" toolbar="#tb">
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
        <a href="#" class="easyui-linkbutton" iconCls="icon-add"plain="true">添加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit"plain="true">修改</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove"plain="true">删除</a>
    </div>
</div>
</body>
</html>
