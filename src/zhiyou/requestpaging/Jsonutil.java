package zhiyou.requestpaging;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;

/**
 * Created by zhiyou on 15-8-5.
 */
public class Jsonutil {
    public static JSONArray formatRsToJsonArray(ResultSet rs) throws Exception{//JSONArry的格式都是键值对的格式name：value
        ResultSetMetaData md = rs.getMetaData();//ResultSetMetaData能获取一个纵向的集合
        int num=md.getColumnCount();
        JSONArray array = new JSONArray();
        while (rs.next()){//首先通过rs横向遍历取数据库中的第一条
            JSONObject mapOfColValues = new JSONObject();
            for(int i=1;i<=num;i++){
                //todo json遇到日期对象的时候会把错，我们要把date类型封装成字符串，再给json
                Object obj=  rs.getObject(i);
                if(obj instanceof Date){
                   mapOfColValues.put(md.getCatalogName(i),DateUtil.formatDate((Date)obj,"yyyy-MM-dd"));
                }else {
                    mapOfColValues.put(md.getColumnName(i), rs.getObject(i));//mapOfColValues纵向的遍历，把纵向的每个键和值都封装起来
                    //md.getCatalogName(i)，是键，rs.getObject(i)，是值
                }
            }
            array.add(mapOfColValues);
        }
        return array;
    }
}
