package zhiyou.servlet;

import net.sf.json.JSONObject;
import zhiyou.Dao.Dbutil;
import zhiyou.Dao.Gradedao;
import zhiyou.Dao.Responseutil;
import zhiyou.Dao.StringUtil;
import zhiyou.Model.Grade;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by zhiyou on 15-8-26.
 */
public class GradeSaveServlet extends HttpServlet {
    Dbutil dbutil = new Dbutil();
    Gradedao gradedao = new Gradedao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//防止表单提交过来的数据中文数据乱码
        String gradeName = request.getParameter("gradeName");
        String gradeDesc = request.getParameter("gradeDesc");
        String id = request.getParameter("id");
        Grade grade = new Grade(gradeName,gradeDesc);
        if(StringUtil.isNotEmpty(id)){//如果id不为空
            grade.setId(Integer.parseInt(id));
        }
        Connection con = null;
        try {
            con=dbutil.getCon();
            int saveNums=0;
            JSONObject result = new JSONObject();//封装result
            if(StringUtil.isNotEmpty(id)){//如果id不为空
                saveNums=gradedao.modifiction(con,grade);
            }else {
                saveNums=gradedao.gradeAdd(con,grade);//获取总数
            }

            if(saveNums>0){
                result.put("success","true");
            }else {
                result.put("success","true");
                result.put("errorMas","删除失败");
            }
            Responseutil.write(response, result);//向页面输出数据
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
