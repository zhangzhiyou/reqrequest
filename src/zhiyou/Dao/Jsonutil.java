package zhiyou.Dao;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import zhiyou.requestpaging.DateUtil;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;

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
            for(int i=1;i<=num;i++){//todo 连表查询的话会查询到两个id，所以建表的时候命名id时最好表明是那个表的id
                Object obj=  rs.getObject(i);
                if(obj instanceof Date){
                    mapOfColValues.put(md.getCatalogName(i), DateUtil.formatDate((Date)obj,"yyyy-MM-dd"));
                }else {
                    mapOfColValues.put(md.getColumnName(i), rs.getObject(i));//mapOfColValues纵向的遍历，把纵向的每个键和值都封装起来
                }
            }

            array.add(mapOfColValues);
        }
        return array;
    }
}
