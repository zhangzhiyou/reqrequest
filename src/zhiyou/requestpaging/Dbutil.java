package zhiyou.requestpaging;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by zhiyou on 15-8-5.
 */
public class Dbutil {
    String url = "jdbc:mysql://localhost:3306/User"
            + "user=root&password=0000&useUnicode=true&characterEncoding=UTF8";

    private String jdbcName="com.mysql.jdbc.Driver";

    /**
     * 获取数据库连接
     * @return
     * @throws Exception
     */
    public Connection getCon() throws Exception{

        Class.forName(jdbcName);
        Connection con= DriverManager.getConnection(url);

        return con;
    }

    /**
     * 关闭数据库连接
     * @param con
     * @throws Exception
     */
    public void closeCon(Connection con) throws Exception{
        if(con!=null){
            con.close();
        }
    }

    public static void main(String[] args) {
        Dbutil dbutil = new Dbutil();

        try {
            dbutil.getCon();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
