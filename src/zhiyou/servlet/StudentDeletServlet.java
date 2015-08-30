package zhiyou.servlet;

import net.sf.json.JSONObject;
import zhiyou.Dao.Dbutil;
import zhiyou.Dao.Responseutil;
import zhiyou.Dao.Studentdao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by zhiyou on 15-8-28.
 */
public class StudentDeletServlet  extends HttpServlet{
    Dbutil dbutil= new Dbutil();
    Studentdao studentdao =new Studentdao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deiLds = request.getParameter("deiLds");//获取jsp页面传过来的参数pags
        Connection con = null;
        try {
            con=dbutil.getCon();
            JSONObject result = new JSONObject();//封装result
            int delNums=studentdao.studentDelete(con,deiLds);//获取总数
            if(delNums>0){
                result.put("success","true");
                result.put("delNums",delNums);
            }else{
                System.out.println("删除出错");
                result.put("errorMas","删除失败");
            }
            Responseutil.write(response, result);//todo 向页面输出数据,输出的格式是json的格式
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                dbutil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
