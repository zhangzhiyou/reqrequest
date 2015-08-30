package zhiyou.servlet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import zhiyou.Dao.Gradedao;
import zhiyou.Dao.Jsonutil;
import zhiyou.Dao.Responseutil;
import zhiyou.Model.Grade;
import zhiyou.Model.Pagebean;
import zhiyou.requestpaging.Dbutil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by zhiyou on 15-8-28.
 */
public class GradeComboListServlet extends HttpServlet {
   zhiyou.Dao.Dbutil dbutil = new zhiyou.Dao.Dbutil();
    Gradedao gradedao = new Gradedao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        try {
            con = dbutil.getCon();
            JSONArray jsonArray =new JSONArray();//todo 必须先初始化
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id","");
            jsonObject.put("gradeName","请选择...");
            jsonArray.add(jsonObject);
            jsonArray.addAll(Jsonutil.formatRsToJsonArray(gradedao.gradelist(con, null, null)));
            Responseutil.write(response, jsonArray);//向页面输出数据
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbutil.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
