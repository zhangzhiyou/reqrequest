<%--
  Created by IntelliJ IDEA.
  User: zhiyou
  Date: 15-8-4
  Time: 下午5:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试分页效果</title>
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.6/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.6/themes/icon.css">
    <script type="text/javascript" src="jquery-easyui-1.3.6/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.6/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="vakata-jstree-2f630b4%20(2)/dist/jstree.js"></script>
    <script type="text/javascript">
           $(function(){//todo 内置函数
               var treeData = [//treeData设置成全局的
                   {
                       text: "信息管理",
                       children: [
                           {
                               text: "班级信息管理",
                               attributes: {//是属性
                                   url:"classifmanage.jsp"
                               }
                           },
                           {
                               text: "学生信息管理",
                               attributes: {
                                   url:"studentinmanage.jsp"
                               }
                           }
                       ]
                   }
               ];
               $("#tree").tree({
                   data:treeData,
                   lines:true,
                   onClick:function(node){
                       if(node.attributes){
                           openTeb(node.text,node.attributes.url);
                       }
                   }
               });
               function openTeb(text,url){
                   if($("#tabs").tabs("exists",text)){
                       $("#tabs").tabs("select",text);
                   }else {
                       var content = "<iframe frameborder='0' scrolling='auto'style='width:100% ;height: 100%' src=" + url + "></iframe>"
                       $("#tabs").tabs('add', {
                           title: text,
                           closable:true,
                           content: content
                       })
                   }
               }
       });


    </script>
</head>
<body class="easyui-layout">
    <div region="north" style="background-color: red ; height:80px"></div>
    <div region="center" style="background-color: #00438a ;">
        <div class="easyui-tabs"  border="false" id="tabs">
            <div title="首页">
                    <div align="center" style="padding-top :100px"><font color="red">欢迎使用</font></div>
            </div>
        </div>
    </div>
    <div region="west" style="width: 150px;" title="导航菜单" split="true">
        <ul id="tree"></ul>
    </div>
    <div region="south"  style="height: 30px; background-color: #C6E746" align="center"></div>
</body>
</html>
