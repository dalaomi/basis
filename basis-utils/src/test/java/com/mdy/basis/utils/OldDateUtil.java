package com.mdy.basis.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 日期工具类
 * 
 * 
 */
public class OldDateUtil {
	
	/**
	 * 将Date类型转换为字符串
	 * 
	 * @param date
	 *            日期类型
	 * @return 日期字符串
	 */
	public static String format(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 将Date类型转换为字符串
	 * 
	 * @param date
	 *            日期类型
	 * @param pattern
	 *            字符串格式
	 * @return 日期字符串
	 */
	public static String formatToDay(Date date) {
		return format(date, "yyyy-MM-dd");
	}
	/**
	 * 
	 * @Description: 给日期加上时分秒
	 * @Author: jiaoguojin
	 * @Company: 宜信-变现通项目组 
	 * @Version: V1.0
	 * @Create Date: 2015年1月29日
	 */
	public static String formatToTime(Date date) {
		return format(date, "HH:mm:ss");
	}
	/**
	 * 将Date类型转换为字符串
	 * 
	 * @param date
	 *            日期类型
	 * @param pattern
	 *            字符串格式
	 * @return 日期字符串
	 */
	public static String format(Date date, String pattern) {
		if (date == null) {
			return "null";
		}
		if (pattern == null || pattern.equals("") || pattern.equals("null")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		return new java.text.SimpleDateFormat(pattern).format(date);
	}
	
	
	
	/**
	 * 日期加减運算
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date addDay(Date date, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, day);
		return cal.getTime();
	}
	/**
	 * 分钟加减運算
	 * @param date
	 * @param min
	 * @return
	 */
	public static Date addMin(Date date, int min) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(Calendar.MINUTE, min);
	    return cal.getTime();
	}

	/**
	 * 将字符串转换为Date类型
	 * 
	 * @param date
	 *            字符串类型
	 * @return 日期类型
	 */
	public static Date format(String date) {
		return format(date, null);
	}

	/**
	 * 将字符串转换为Date类型
	 * 
	 * @param date
	 *            字符串类型
	 * @param pattern
	 *            格式
	 * @return 日期类型
	 */
	public static Date format(String date, String pattern) {
		if (pattern == null || pattern.equals("") || pattern.equals("null")) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		if (date == null || date.equals("") || date.equals("null")) {
			return new Date();
		}
		Date d = null;
		try {
			d = new java.text.SimpleDateFormat(pattern).parse(date);
		} catch (ParseException pe) {
		}
		return d;
	}
	
	  
    /**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        System.out.println("开始时间为："+sdf.format(smdate)+"结束时间为："+sdf.format(bdate));
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }    
    
    
    /**
     * @Description: 两个字符串时间相减，得到小时
     * @param:
     * @return: int
     * @throws:
     * @Author: Wangty
     * @Create: Date: 2014年12月19日 上午10:48:39
     */
    public static int hoursBetween(String startTime,String endTime){
    	
    	long start = fromDateStringToLong(startTime);
    	long end = fromDateStringToLong(endTime);
    	
    	long ss=(start-end)/(1000); //共计秒数  
    	int hh=(int)ss/3600;  //共计小时数
    	//int MM = (int)ss/60;   //共计分钟数  
    	//int dd=(int)hh/24;   //共计天数  
    	
    	return hh;
    	
    }    
    
    /**
     * @Description: 两个字符串时间相减，得到秒
     * @param:
     * @return: int
     * @throws:
     * @Author: Wangty
     * @Create: Date: 2015年1月21日 下午1:33:01
     */
    public static long ssBetween(String startTime,String endTime){
    	
    	long start = fromDateStringToLong(startTime);
    	long end = fromDateStringToLong(endTime);
    	long ss=(start-end)/(1000); //共计秒数  
    	
    	return ss;
    	
    }    
    /**
     * @Description: 两个日期相减，得到秒
     * @param:
     * @return: int
     * @throws:
     * @Author: Wangty
     * @Create: Date: 2015年1月21日 下午1:33:01
     */
    public static long ssBetween(Date startTime,Date endTime){
    	return ssBetween(format(startTime, null),format(endTime, null));
    	
    }    
    public static long fromDateStringToLong(String inVal) {
    	Date date = null;   //定义时间类型         
    	SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
    	try {   
    	  date = inputFormat.parse(inVal); //将字符型转换成日期型  
    	} catch (Exception e) {   
    	  e.printStackTrace();   
    	}   
    	return date.getTime();   //返回毫秒数  
    }   
    /**
     * 将毫秒数格式化成时分秒
     * @param l
     * @return
     */
    public static String longToDate(long l){
    	StringBuilder sb =new StringBuilder();
    	try {
//        	Date date = new Date(l);
//        	SimpleDateFormat aFormat = new SimpleDateFormat( "HH:mm:ss ");
//        	s = aFormat.format(date);
    		long hours = l/(60*60);
    		long min = (l-(hours*60*60))/60;
    		long seconds=l-(hours*60*60)-min*60;
    		if (hours<=9) {
				sb.append("0"+hours);
			}else {
				sb.append(""+hours);
			}
    		sb.append(":");
    		if (min<=9) {
				sb.append("0"+min);
			}else {
				sb.append(""+min);
			}
    		sb.append(":");
    		if (seconds<=9) {
				sb.append("0"+seconds);
			}else {
				sb.append(""+seconds);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
	    	return sb.toString();
		}

    }
    //获取资金生效日
    public static String getIncomeDate(){
    	String incomeDate=null;
    	try {
    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		Date now = new java.util.Date(System.currentTimeMillis());
    		String time = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date( System.currentTimeMillis()))+" 23:50:00";
			Date d = sdf.parse(time);
			if (now.getTime()>d.getTime()) {//超过23：50：00   按第二天处理
				incomeDate = new SimpleDateFormat("yyyy-MM-dd").format(addDay(now, 1));
			}else {
				incomeDate = new SimpleDateFormat("yyyy-MM-dd").format(now);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return incomeDate;
    }
    public static String getNowTime(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		Date d = new Date();  
		String str=sdf.format(d);  
		return str;
	}
    
    /**
     * 
     * @Title: getNextDay 
     * @Description: 得到当前日期的下一天
     * @param today
     * @return
     */
    public static Date getNextDay(Date today){
    	if(today == null) return null;
    	Calendar day = Calendar.getInstance();
    	day.setTime(today);
    	day.add(Calendar.DATE, 1);
    	return day.getTime();
    }
    /**
     * 获取当前时间
     * @return
     */
    public static Date getCurrentDate() throws Exception{
    	Calendar ca = Calendar.getInstance();// 得到一个Calendar的实例  
    	ca.setTime(new Date()); // 设置时间为当前时间  
    	return ca.getTime(); 
    }
    
    
    public static void main(String[] args) throws ParseException, Exception {
    	Calendar ca = Calendar.getInstance();// 得到一个Calendar的实例  
    	ca.setTime(new Date()); // 设置时间为当前时间  
    	ca.add(Calendar.DATE, 10);
    	System.out.println(daysBetween(getCurrentDate(), ca.getTime()));
    	System.out.println(daysBetween(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2016-05-26 01:04:24"), new Date()));
    }
}
