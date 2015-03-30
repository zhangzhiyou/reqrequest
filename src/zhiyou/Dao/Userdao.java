package zhiyou.Dao;

import zhiyou.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
