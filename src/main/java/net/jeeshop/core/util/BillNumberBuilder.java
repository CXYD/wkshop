package net.jeeshop.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: zhaodh
 * Date: 16/7/13
 * Time: 07:12
 */
public class BillNumberBuilder extends  Thread{
    private static Object locker = new Object();

    private static long orderNum = 0l;
    private static String date ;

    /**
     * 生成订单编号
     * @return
     */
    public static synchronized String getOrderNo() {
        String str = new SimpleDateFormat("yyMMddHHmm").format(new Date());
        if(date==null||!date.equals(str)){
            date = str;
            orderNum  = 0l;
        }
        orderNum ++;
        long orderNo = Long.parseLong((date)) * 10000;
        orderNo += orderNum;;
        return orderNo+"";
    }
    // 防止创建类的实例
    private BillNumberBuilder(){}
}
