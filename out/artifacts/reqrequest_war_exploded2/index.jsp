<%--
  Created by IntelliJ IDEA.
  User: zhiyou
  Date: 15-3-10
  Time: 下午8:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>学生登入系统</title>
      <script type="text/javascript">
      function resetValue(){
          document.getElementById("username").value="";
          document.getElementById("password").value="";
      }
      </script>
      <style type="text/css">
          *{margin :0 auto}
          body{}
          .main{background-image: url("img/scenery.jpg");width: 100% ;height:100%}
      </style>
  </head>
  <body>
     <div class="main">
         <form action="login" method="post">
        <table>
            <tr height="180">
                <td colspan="4"></td>
            </tr>
             <tr height="10">
                 <td width="40%"></td>
                 <td width="10%">用户名：</td>
                 <td><input type="text" value="${username}" id="username" name="username"></td>
                 <td width="30%"></td>
             </tr>
            <tr>
                <td width="40%"></td>
                <td width="10%">密码：</td>
                <td><input type="password" value="${password}" id="password" name="password"></td>
                <td width="30%"></td>
            </tr>
            <tr>
                <td width="40%"></td>
                <td width="10%"><input type="submit" value="登入"></td>
                <td><input type="button" value="重置" onclick="resetValue()"></td>
                <td width="30%"></td>
            </tr>
            <tr>
                <td width="40%"></td>
               <font color="red">${ error}</font>
            </tr>
        </table>
       </form>
     </div>
  </body>
</html>
