package zhiyou;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by zhiyou on 15-9-9.
 */
public class Jdbc {
    private String url="Jdbc:mysql://localhost:3306/ttt";
    private String username= "root";
    private String password = "****";
    private String jdbcName="com.mysql.jdbc.Driver";
    public Connection getCon() throws Exception {
        Class.forName(jdbcName);
        Connection con = DriverManager.getConnection(url,username,password);
        return con;
    }
    public void close(Connection con) throws Exception {
        if(con!=null){
            con.close();
        }

    }

}
