package zhiyou.Dao;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * Created by zhiyou on 15-3-15.
 */
public class Jsonutil {//简单的封装util
    public static JSONArray formatRsToJsonArray(ResultSet rs) throws Exception{//JSONArry的格式都是键值对的格式name：value
        ResultSetMetaData md = rs.getMetaData();//ResultSetMetaData能获取一个纵向的集合
       int num=md.getColumnCount();
        JSONArray array = new JSONArray();
        while (rs.next()){//首先通过rs横向遍历取数据库中的第一条
            JSONObject mapOfColValues = new JSONObject();
            for(int i=1;i<=num;i++){
                mapOfColValues.put(md.getColumnName(i),rs.getObject(i));//mapOfColValues纵向的遍历，把纵向的每个键和值都封装起来
                //md.getCatalogName(i)，是键，rs.getObject(i)，是值
            }
            array.add(mapOfColValues);
        }
        return array;
    }
}
