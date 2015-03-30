<%--
  Created by IntelliJ IDEA.
  User: zhiyou
  Date: 15-3-10
  Time: 下午8:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>学生界面</title>
    <%
        if(session.getAttribute("currentUser")==null){
            System.out.println("返回");
            response.sendRedirect("index.jsp");
            return;
        }
    %>
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
                    text: "根",
                    children: [
                        {
                            text: "班级信息管理",
                            attributes: {
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
                data:treeData ,
                lines:true,
                onClick:function(node){
                    if(node.attributes){
                        openTeb(node.text,node.attributes.url);
                    }
                }


            });
            //新增teb
            function openTeb(text,url){
                if($("#tabs").tabs("exists",text)){
                    $("#tabs").tabs("select",text);
                }
                else{
                    var content="<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%' src="+url+"><iframe/>"
                    $("#tabs").tabs('add',{
                        title:text,
                        closable:true,
                        content:content
                    });
                }
            }
        });
    </script>
</head>

<body class="easyui-layout">
    <div region="north" style="height: 80px;">
        <div align="left" style="width:80%;float: left"><img src="img/yun.png" width="100%" height="%100"></div>
        <div style="padding-top: 50px ;padding-right: 20px"> 当前用户： &nbsp; ${currentUser.username}<font color="red">${currentUser.username}</font></div>
        </div>
    <div region="center">
        <div class="easyui-tabs" fit="true" border="false" id="tabs">
            <div title="首页">
                <div align="center" style="padding-top: 100px"><font color="red" size="10">欢迎使用</font></div>
            </div>
        </div>
    </div>
    <div region="west" style="width: 150px;" title="导航菜单" split="true">

        <ul id="tree"></ul>
    </div>
    <div region="south" style="height: 30px;" align="center">版权所属山东理工大学<a href="http://www.sdut.edu.cn/">www.sdut.edu.cn</a></div>
</body>
</html>
