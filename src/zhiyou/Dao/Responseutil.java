package zhiyou.Dao;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by zhiyou on 15-3-15.
 */
public class Responseutil {//封装向页面直接输出数据的
    public static void write(HttpServletResponse response,JSONObject jsonObject)throws Exception{
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonObject.toString());
        out.flush();
        out.close();
    }
}
