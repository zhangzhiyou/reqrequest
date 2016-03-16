package zhiyou.servlet;




import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import zhiyou.Dao.Dbutil;
import zhiyou.Dao.Responseutil;
import zhiyou.Dao.Userdao;
import zhiyou.Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.concurrent.locks.Condition;

/**
 * Created by zhiyou on 15-3-11.
 */
@WebServlet(urlPatterns = "/login" ,name = "login")
public class Loginservlet extends HttpServlet {
    Dbutil dbutil = new Dbutil();
    Userdao userdao = new Userdao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        request.setAttribute("username",username);
        request.setAttribute("password",password);
        String s=null;

        if (username.isEmpty() || password.isEmpty()) {
            request.setAttribute("error", "用户名或密码为空");
            request.getRequestDispatcher("index.jsp").forward(request, response);//

        }
        User user = new User(username, password);
        Connection con = null;
        try {
            con = dbutil.getCon();//链接数据库
            User currentUser = userdao.login(con, user);
            if (currentUser == null) {
                request.setAttribute("error", "用户名或密码错误");
                //服务器跳转
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            else {
                //获取session
                HttpSession session = request.getSession();
                session.setAttribute("currentUser",currentUser);
                request.setAttribute("h1","你好");

                response.sendRedirect("hello.jsp");
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                dbutil.closeCon(con);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}