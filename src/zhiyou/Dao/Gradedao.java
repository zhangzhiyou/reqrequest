package zhiyou.Dao;

import zhiyou.Model.Grade;
import zhiyou.Model.Pagebean;

import java.sql.*;

/**
 * Created by zhiyou on 15-3-15.
 */
public class Gradedao {
      public ResultSet gradelist (Connection con,Pagebean pagebean, Grade grade) throws SQLException{

        StringBuilder sb = new StringBuilder("select * from grade");//表示从数据库中查到的所有的内容反转，然后形成新的字符串sb
          if( grade!=null&& StringUtil.isNotEmpty(grade.getGradeName())){
             sb.append(" and gradeName like '%"+grade.getGradeName()+"%'");//todo 就是把该字段追加到sb字符串上
         }
          if(pagebean!=null){
           // sb.append("limit").append(pagebean.getPage()).append(',').append(pagebean.getRows());//追加内容到当前Stringbuffer对象的末尾
            sb.append(" limit "+pagebean.getStart()+","+pagebean.getRows());
        }
                                                    //todo 如果有and就把and替换成where
        PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and","where"));//把的append追加的值转换成toString类型
            return pstmt.executeQuery();
      //  return pstmt.executeQuery();//返回，转化后的值
    }
    //todo 统计从数据库中一共查询出多少条数据
    public int gradeCount(Connection con) throws Exception{//获取总计录数
        String sql = "select count(*) as total from grade";
        PreparedStatement pstmt = con.prepareStatement(sql.toString());
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            return rs.getInt("total");
        }else {
            return 0;
        }
    }
    /*
    * delete from dableName where id in(1,2,3);删除表中id是1,2,3的数据
    * 统计删除了那些条记录
    * */
    public int gradeDelete(Connection con,String delIds) throws Exception{
        String sql = "delete from grade where id in("+delIds+")";
        PreparedStatement psmtp = con.prepareStatement(sql);
          return  psmtp.executeUpdate();//统计删除了几条
    }
    // todo 添加数据
    public int gradeAdd(Connection con,Grade grade) throws SQLException {
        String sql = "insert into grade values(null,?,?)";
        PreparedStatement psmtp = con.prepareStatement(sql);
        psmtp.setString(1,grade.getGradeName());
        psmtp.setString(2,grade.getGradeDesc());
        return psmtp.executeUpdate();
    }
    //todo 更新数据
    public int modifiction(Connection con,Grade grade) throws SQLException{
        String sql = "update grade set gradeName=?, gradeDesc=? where id=?";
        PreparedStatement psmtp = con.prepareStatement(sql);
        psmtp.setString(1,grade.getGradeName());
        psmtp.setString(2,grade.getGradeDesc());
        psmtp.setInt(3,grade.getId());
         return   psmtp.executeUpdate();
    }
}
