package zhiyou.requestpaging;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by zhiyou on 15-8-5.
 */
public class Responseutil {
    public static void write(HttpServletResponse response,JSONObject jsonObject)throws Exception{
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(jsonObject.toString());
        out.flush();
        out.close();
    }
}
