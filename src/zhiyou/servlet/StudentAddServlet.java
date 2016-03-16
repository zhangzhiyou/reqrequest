package zhiyou.servlet;

import net.sf.json.JSONObject;
import zhiyou.Dao.Dbutil;
import zhiyou.Dao.Responseutil;
import zhiyou.Dao.StringUtil;
import zhiyou.Dao.Studentdao;
import zhiyou.Model.Grade;
import zhiyou.Model.Student;
import zhiyou.requestpaging.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by zhiyou on 15-8-28.
 */
public class StudentAddServlet extends HttpServlet {
    Dbutil dbutil = new Dbutil();
    Studentdao studentdao = new Studentdao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//防止表单提交过来的数据中文数据乱码
        String stuNo = request.getParameter("stuNo");
        String stuName = request.getParameter("stuName");
        String sex = request.getParameter("sex");
        String birthday = request.getParameter("birthday");
        String gradeId = request.getParameter("gradeId");
        String email = request.getParameter("email");
        String stuDesc = request.getParameter("stuDesc");
        String id = request.getParameter("id");
        System.out.println("学号="+stuNo+"姓名="+stuName+"性别="+sex+"生日="+birthday+"年级"+gradeId+"邮件="+email+"描述="+stuDesc+"id="+id);
        Student student = null;
        try {
             student = new Student(stuNo, stuName, sex, DateUtil.formatString(birthday, "yyyy-MM-dd"), Integer.parseInt(gradeId), email, stuDesc);
        }
             catch (Exception e) {
            e.printStackTrace();
        }

        if (StringUtil.isNotEmpty(id)) {//如果id不为空
                student.setId(Integer.parseInt(id));
            }

            Connection con = null;
            try {
                con = dbutil.getCon();
                int saveNums = 0;
                JSONObject result = new JSONObject();//封装result
                if (StringUtil.isNotEmpty(id)) {//如果id不为空
                    saveNums = studentdao.modifiction(con, student);
                } else {
                    saveNums = studentdao.studentAdd(con, student);//获取总数
                }

                if (saveNums > 0) {
                    result.put("success", "true");
                } else {
                    result.put("success", "true");
                    result.put("errorMas", "删除失败");
                }
                Responseutil.write(response, result);//向页面输出数据
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
