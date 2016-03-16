<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="zhiyou.Dao.Userdao" %>
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
                data:treeData,//定义了类型的变量
                lines:true,//说明树分支有线
                onClick:function(node){//点击事件
                    if(node.attributes){//如果attributes属性存在
                        openTeb(node.text,node.attributes.url);//
                    }
                }
            });
        //    新增teb
            function openTeb(text,url){
                if($("#tabs").tabs("exists",text)){//tabs是div的id，.tabs是指tabs方法,如果存在
                    $("#tabs").tabs("select",text);//如果存在，就选中
                }
                else{
                    var content="<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%' src="+url+"><iframe/>"
                    $("#tabs").tabs('add',{
                        title:text,
                        closable:true,//该句是把打开的再关上
                        content:content
                    });
                }
            }
        });
        $(".add").append("<h1>nihao</h1>");
    </script>
    <style type="text/css">
        .select{
            font-size: 20px;
            margin-left: 20px;
            margin-top: 20px;
        }
    </style>
</head>

<body class="easyui-layout">
    <div region="north" style="height: 80px;">
        <div align="left" style="width:80%;float: left"><img src="img/yun.png" width="100%" height="%100"></div>
        <div style="padding-top: 50px ;padding-right: 20px"> 当前用户：${currentUser.username}<font color="red"></font></div>
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
    <div class="add" region="south" style="height: 30px;" align="center">版权所属山东理工大学<a  href="http://www.sdut.edu.cn/">www.sdut.edu.cn</a>
        <div class="select">
        <%=Userdao.username()%>
            </div>
    <div>
        <h1>你好</h1>
       nihao: <select name="select" id="s" style="font-size: 20px">
            <option value="1">1</option>
            <option value="2">2</option>
        </select>
    </div>
    </div>
</body>
</html>
