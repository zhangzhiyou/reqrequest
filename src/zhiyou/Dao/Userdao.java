package zhiyou.Dao;

import zhiyou.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiyou on 15-3-11.
 */
public class Userdao {
    /**
     * 登录验证
     * @param con
     * @param user
     * @return
     * @throws Exception
     */
    public User login(Connection con,User user) throws SQLException {
        User resultUser = null;
      //  Connection conn = null;
        PreparedStatement pstmt=null;
        try {

            String sql = "select * from login where username=? and password=?";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            ResultSet rs = pstmt.executeQuery();//向下遍历
            if (rs.next()) {
                resultUser = new User();
                resultUser.setUsername(rs.getString("username"));
                resultUser.setPassword(rs.getString("password"));
            }
            return resultUser;
        } catch (Exception e){
            e.printStackTrace();
        }
        return resultUser;
    }
    public static String username(){
        Dbutil dbutil = new Dbutil();
        Connection con = null;
        PreparedStatement pstmt = null;

        String s = "用户名:<select name='hello' >";
        try {
            String sql = "select username from login";
            con = dbutil.getCon();
            pstmt=con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                s = s + "<option name='username' value="+rs.getString(1)+">" + rs.getString(1) + "</option>";
            }
            s = s + "<select>";
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}
