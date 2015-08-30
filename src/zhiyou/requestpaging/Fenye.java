package zhiyou.requestpaging;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by zhiyou on 15-8-8.
 */
public class Fenye extends HttpServlet {
    Dbutil dbutil = new Dbutil();
    DownDao downDao = new DownDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");//获取jsp页面传过来的参数pags
        String rows = request.getParameter("rows");
        Pagebean pagebean = new Pagebean(Integer.parseInt(page),Integer.parseInt(rows));//强制转换page和rows
        Connection con = null;
        try {
            con=dbutil.getCon();
            JSONObject result = new JSONObject();//封装result
            JSONArray jsonArray= Jsonutil.formatRsToJsonArray(downDao.gradelist(con, pagebean));//gradedao.gradelist(con, pagebean)的返回值是ResultSet类型的值
            //Jsonutil.formatRsToJsonArray（）参数类型是ResultSet类型的 返回 JSONArray类型。把gradedao.gradelist(con, pagebean)转换成json,

            int total=downDao.gradeCount(con);//获取总数
            result.put("rows",jsonArray);
            result.put("total",total);
            Responseutil.write(response,result);//向页面输出数据
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
