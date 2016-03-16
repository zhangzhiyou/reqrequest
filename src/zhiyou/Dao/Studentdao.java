package zhiyou.Dao;

import zhiyou.Model.Student;
import zhiyou.Model.Pagebean;
import zhiyou.requestpaging.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by zhiyou on 15-8-27.
 */
public class Studentdao {
    public ResultSet studentlist (Connection con,Pagebean pagebean,Student student,String bbirthday,String ebirthday) throws SQLException {

        StringBuilder sb = new StringBuilder("select * from grade g,student s where s.gradeId=g.id");//表示从数据库中查到的所有的内容反转，然后形成新的字符串sb

        if(StringUtil.isNotEmpty(student.getStuNo())){
            sb.append(" and s.stuNo like '%"+student.getStuNo()+"%'");//todo 模糊查询
        }
        if(StringUtil.isNotEmpty(student.getStuName())){
            sb.append(" and s.stuName like '%"+student.getStuName()+"%'");
        }
        if(StringUtil.isNotEmpty(student.getSex())){
            sb.append(" and s.sex='"+student.getSex()+"' ");
        }
        if(StringUtil.isNotEmpty(bbirthday)){
            sb.append(" and TO_DAYS(s.birthday)>=TO_DAYS('"+bbirthday+"')");//todo TO_DAYS函数把日期转化为天数
        }
        if(StringUtil.isNotEmpty(ebirthday)){
            sb.append(" and TO_DAYS(s.birthday)<=TO_DAYS('"+ebirthday+"')");//todo 注意and前留个空格
        }
        if(student.getGradeId()!=-1){
            sb.append(" and s.gradeId='"+student.getGradeId()+"'");
        }

        // todo 在这先判断一下studentName值是不是空，来决定下面的sql语句中的and的是否换成where
        if(pagebean!=null){
            // sb.append("limit").append(pagebean.getPage()).append(',').append(pagebean.getRows());//追加内容到当前Stringbuffer对象的末尾
            sb.append(" limit "+pagebean.getStart()+","+pagebean.getRows());
        }
        //todo 如果有and就把and替换成where
       // PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and","where"));//把的append追加的值转换成toString类型
        PreparedStatement pstmt = con.prepareStatement(sb.toString());
        return pstmt.executeQuery();
        //  return pstmt.executeQuery();//返回，转化后的值
    }
    //todo 统计从数据库中一共查询出多少条数据
    public int studentCount(Connection con,Student student,String bbirthday,String ebirthday) throws Exception{//获取总计录数
        StringBuffer sql = new StringBuffer("select count(*) as total from student s,grade g where s.gradeId=g.id");

        if(StringUtil.isNotEmpty(student.getStuNo())){
            sql.append(" and s.stuNo like '%"+student.getStuNo()+"%'");
        }
        if(StringUtil.isNotEmpty(student.getStuName())){
            sql.append(" and s.stuName like '%"+student.getStuName()+"%'");
        }
        if(StringUtil.isNotEmpty(student.getSex())){
            sql.append(" and s.sex='"+student.getSex()+"' ");
        }
        if(StringUtil.isNotEmpty(bbirthday)){
            sql.append(" and TO_DAYS(s.birthday)>=TO_DAYS('"+bbirthday+"') ");//todo TO_DAYS函数把日期转化为天数
        }
        if(StringUtil.isNotEmpty(ebirthday)){
            sql.append(" and TO_DAYS(s.birthday)<=TO_DAYS('"+ebirthday+"')");
        }
        if(student.getGradeId()!=-1){
            sql.append(" and s.gradeId='"+student.getGradeId()+"'");
        }
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
    public int studentDelete(Connection con,String delIds) throws Exception{
        String sql = "delete from student where id in("+delIds+")";
        PreparedStatement psmtp = con.prepareStatement(sql);
        return  psmtp.executeUpdate();//统计删除了几条
    }
    // todo 添加数据
    public int studentAdd(Connection con,Student student) throws SQLException {
        String sql = "insert into student values(null,?,?,?,?,?,?,?)";
        PreparedStatement psmtp = con.prepareStatement(sql);
        psmtp.setString(1,student.getStuNo());
        psmtp.setString(2,student.getStuName());
        psmtp.setString(3,student.getSex());
        psmtp.setString(4, DateUtil.formatDate(student.getBirthday(),"yyyy-MM-dd"));
        psmtp.setString(5, student.getEmail());
        psmtp.setString(6,student.getStuDesc());
        psmtp.setInt(7,student.getGradeId());
        return psmtp.executeUpdate();
    }
    //todo 更新数据
    public int modifiction(Connection con,Student student) throws SQLException{
        String sql = "update student set stuNo=?, stuName=?,sex=?,birthday=?,gradeId=?,email=?,stuDesc=? where id=?";
        PreparedStatement psmtp = con.prepareStatement(sql);
        psmtp.setString(1,student.getStuNo());
        psmtp.setString(2,student.getStuName());
        psmtp.setString(3,student.getSex());
        psmtp.setString(4, DateUtil.formatDate(student.getBirthday(),"yyyy-MM-dd"));
        psmtp.setInt(5, student.getGradeId());
        psmtp.setString(6,student.getEmail());
        psmtp.setString(7, student.getStuDesc());
        psmtp.setInt(8,student.getId());
        return   psmtp.executeUpdate();
    }
    public boolean getStudentGradeId(Connection con ,String gradeId) throws Exception {
        String sql = "select * from student where gradeId=?";
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setString(1,gradeId);
        ResultSet rs = psmt.executeQuery();
        if(rs.next()){
            return true;
        }else {
            return false;
        }
    }
}
