package zhiyou.servlet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import zhiyou.Dao.*;
import zhiyou.Model.Grade;
import zhiyou.Model.Pagebean;
import zhiyou.Model.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by zhiyou on 15-8-27.
 */
public class StudentlistServlet extends HttpServlet {
    Dbutil dbutil = new Dbutil();
    Studentdao studentdao = new Studentdao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String stuNo = request.getParameter("stuNo");
        String stuName = request.getParameter("stuName");
        String sex = request.getParameter("sex");
        String bbirthday = request.getParameter("bbirthday");
        String ebirthday = request.getParameter("ebirthday");
        String gradeId = request.getParameter("gradeId");

        Student student = new Student();
        if(stuNo!=null){
            student.setStuNo(stuNo);
            student.setStuName(stuName);
            student.setSex(sex);
            if(StringUtil.isNotEmpty(gradeId)){
                student.setGradeId(Integer.parseInt(gradeId));
            }
        }

        String page = request.getParameter("page");//获取jsp页面传过来的参数pags
        String rows = request.getParameter("rows");
        String gradeName = request.getParameter("gradeName");
        int i=0;
        if(gradeName==null){
            gradeName="";
        }
        Grade grade = new Grade();
        grade.setGradeName(gradeName);

        Pagebean pagebean = new Pagebean(Integer.parseInt(page),Integer.parseInt(rows));//强制转换page和rows
        Connection con = null;
        try {
            con=dbutil.getCon();
            JSONObject result = new JSONObject();//封装result
            JSONArray jsonArray= Jsonutil.formatRsToJsonArray(studentdao.studentlist(con, pagebean,student,bbirthday,ebirthday));//gradedao.gradelist(con, pagebean)的返回值是ResultSet类型的值
            //Jsonutil.formatRsToJsonArray（）参数类型是ResultSet类型的 返回 JSONArray类型。把gradedao.gradelist(con, pagebean)转换成json,
            int total=studentdao.studentCount(con,student,bbirthday,ebirthday);//获取总数
            result.put("rows",jsonArray);
            result.put("total",total);
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
