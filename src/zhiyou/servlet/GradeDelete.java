package zhiyou.servlet;

import net.sf.json.JSONObject;
import zhiyou.Dao.Dbutil;
import zhiyou.Dao.Gradedao;
import zhiyou.Dao.Responseutil;
import zhiyou.Dao.Studentdao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by zhiyou on 15-8-26.
 */
public class GradeDelete extends HttpServlet{
    Dbutil dbutil = new Dbutil();
    Gradedao gradedao = new Gradedao();
    Studentdao studentdao = new Studentdao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deiLds = request.getParameter("deiLds");//获取jsp页面传过来的参数pags
        Connection con = null;
        try {
            JSONObject result = new JSONObject();//封装result
            con=dbutil.getCon();
            String str[] = deiLds.split(",");//todo 把字符数组,用","分开
            for(int i=0;i<str.length;i++){
                boolean f =studentdao.getStudentGradeId(con,str[i]);
                if(f){
                    result.put("errorindex",i);
                    result.put("errorMas","班级下面有学生不能删除");
                    Responseutil.write(response, result);
                    return;
                }
            }
            int delNums=gradedao.gradeDelete(con,deiLds);//获取总数
            if(delNums>0){
                result.put("success","true");
                result.put("delNums",delNums);
            }else{
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
