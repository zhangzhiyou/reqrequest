package zhiyou.requestpaging;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhiyou on 15-8-27.
 */
public class DateUtil {
    public static String formatDate(Date date,String format){//第一个参数是从页面传过来的日期，第二个参数你要转化成的类型
        String result="";
        SimpleDateFormat sft = new SimpleDateFormat(format);
        if(date!=null) {
            result = sft.format(date);
        }
        return result;
    }
    public static Date formatString(String str,String format) throws Exception {
        SimpleDateFormat sft = new SimpleDateFormat(format);
        return sft.parse(str);
    }

}
