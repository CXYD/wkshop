package net.jeeshop.web.controller.common;

import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtils {

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date){
		return date2Str(date,"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date){
		if(StringUtils.isNotBlank(date)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 按照参数format的格式，日期转字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date,String format){
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}else{
			return "";
		}
	}
	
	 public static String replaceBlank(String str) {
	        String dest = "";
	        if (str!=null) {
	            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
	            Matcher m = p.matcher(str);
	            dest = m.replaceAll("");
	        }
	        return dest;
	    }
	    
	    public static String getLongTime(){
	    	SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
	    	String suffix = fmt.format(new Date());
	    	return suffix;
	        
	    }
	    
	    public static String getTimeSeq(){
	    	SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
	    	String suffix = fmt.format(new Date());
	    	
	    	return suffix.substring(0, 16);
	        
	    }
	    
	    public static String getCurrentTime(){
	    	SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    	String suffix = fmt.format(new Date());
	    	return suffix;
	    }


		public static String getCurrentYearMonthDay(){
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
			String suffix = fmt.format(new Date());
			return suffix;
		}
	    
	    public static String getYearMonth(){
	    	SimpleDateFormat fmt = new SimpleDateFormat("yyyyMM");
	    	String suffix = fmt.format(new Date());
	    	return suffix;
	    }
	    
	    public static String getYearMonthDay(){
	    	SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd"); 
	    	String suffix = fmt.format(new Date());
	    	return suffix;
	    }
	    
	    
	    public static String getDay(){
	    	SimpleDateFormat fmt = new SimpleDateFormat("d"); 
	    	String suffix = fmt.format(new Date());
	    	return suffix;
	    }
	    
	    
	 public static long getSysTime(long year0,long mon0,long day,long hour,long min,long sec){
			long mon=mon0,year=year0;
			if(0>(mon-=2)){
				mon+=12;
				year-=1;
			}
			return (long)((((year/4-year/100+year/400+367*mon/12+day)+
			year*365-719499)*24+hour)*60+min)*60+sec;
	}
	
	public static long getSysTime(){
		
		 return System.currentTimeMillis()/1000;
	}
	
	public static long getAfterSysTime(long aftertime){
		
		 return System.currentTimeMillis()/1000+aftertime;
	}
	
	public static String sysTime2DateString(long time){
		SimpleDateFormat format = new SimpleDateFormat( "yyyy年MM月dd日" );
		 String d = format.format(time);
		 return d;
	}
	
	

	public static String sysTime2StringTime(long timestamp){
		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		 String d = format.format(new Date(timestamp));
		 return d;
	}
	
	/**
	 * 
	
	* @Title: TimeStamp2Date 
	
	* @Description: 时间戳转成日期
	
	* @param @param timestampString
	* @param @param formats
	* @param @return    设定文件 
	
	* @return String    返回类型 
	
	* @throws
	 */
	public static String TimeStamp2Date(long timestampString, String formats){  
//		  Long timestamp = Long.parseLong(timestampString)*1000; 
//		System.out.println("timestamp:"+timestampString);
		  Long timestamp = timestampString*1000;
		  String date = new SimpleDateFormat(formats).format(new Date(timestamp));
		  return date;  
		}
	
	
	public static String Date2TimeStampString(Date date){
		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	// 字符串转unix timestamp
	public static long String2TimeStamp(String timeString,String formats){
	
		
		try {
			Date epoch = new SimpleDateFormat(formats).parse(timeString);
			return epoch.getTime()/1000;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.print("字符和格式不对应！");
			return 0;
		}
		
		
	}
	
	/**
     * 当月最后一天
     * @return
     */
    public  static String getLastDay() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();
        String s = df.format(theDate);
        StringBuffer str = new StringBuffer().append(s).append(" 23:59:59");
        return str.toString();

    }

    public static String getTime(){
    	SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss"); 
    	String suffix = fmt.format(new Date());
    	
    	return suffix;
        
    }
    /**
     * 得到上个月 ,当月,下个月 对应的时间
     * @param date
     * @return
     */
    public static String getLastMonth(String date,int flag){
    	
    	DateFormat fmt = fmt = new SimpleDateFormat("yyyyMM");;
		Calendar c = null;
		try {
			c = Calendar.getInstance();
			c.setTime(fmt.parse(date));
			c.add(Calendar.MONTH, flag);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return fmt.format(c.getTime());
    	
    }
    
    public static String getMoreMonth(String date,int flag){
    	
    	DateFormat fmt = fmt = new SimpleDateFormat("yyyyMM");;
		Calendar c = null;
		try {
			c = Calendar.getInstance();
			c.setTime(fmt.parse(date));
			c.add(Calendar.MONTH, flag);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return fmt.format(c.getTime());
    	
    }
	public static void main(String[] args) throws ParseException {
		long sysTime = getSysTime();
	
System.out.println(TimeStamp2Date(1449560162,"yyyy-MM-dd HH:mm:ss"));
//		System.out.println(TimeStamp2Date(1430841600000,"yyyy-MM-dd"));
//		
//		System.out.println(String2TimeStamp("2015-06-01 00:00:00"));
//		Date epoch = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2015-05-06 15:35:00");
//		Date epoch = new SimpleDateFormat("yyyyMMdd").parse(getYearMonthDay());
//		System.out.println(epoch.getTime()/1000);

//		getYearMonthDay();
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		
//		System.out.println(DateUtils.String2TimeStamp(DateUtils.getLastDay(), "yyyy-MM-dd HH:mm:ss"));
		
    	//System.out.println(getMoreMonth("201512",-11));
	    
	}
	

}
