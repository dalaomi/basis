package com.mdy.basis.utils;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;

import junit.framework.TestCase;

/**
 * 日期工具Demo
 * @author Looly
 *
 */
public class DateUtilDemo extends TestCase{
	
	public void testFormatDate(){
		Date dateTime = EaseDateUtil.parse("2015-03-25 12:04:23.0");
		String dateOld = OldDateUtil.formatToDay(dateTime);
		String dateNew = EaseDateUtil.formatDate(dateTime);
		Assert.assertEquals(dateNew, dateOld);
		
		dateTime = EaseDateUtil.parse("2015-03-25 12:04:23");
		dateOld = OldDateUtil.formatToDay(dateTime);
		dateNew = EaseDateUtil.formatDate(dateTime);
		Assert.assertEquals(dateNew, dateOld);
		
		dateTime = EaseDateUtil.parse("2015-03-25");
		dateOld = OldDateUtil.formatToDay(dateTime);
		dateNew = EaseDateUtil.formatDate(dateTime);
		Assert.assertEquals(dateNew, dateOld);
		
		dateTime = EaseDateUtil.parse("60150625");
		dateOld = OldDateUtil.formatToDay(dateTime);
		dateNew = EaseDateUtil.formatDate(dateTime);
		Assert.assertEquals(dateNew, dateOld);
		
		Assert.assertEquals(OldDateUtil.formatToDay(null),"null");
		Assert.assertNull(EaseDateUtil.formatDate(null));
		
	}
	public void testBetweenDate() throws Exception{
		Date startDate = EaseDateUtil.parse("2015-03-25");
		Date endDate = EaseDateUtil.parse("2016-03-25");
		System.out.println(OldDateUtil.daysBetween(startDate, endDate));
		System.out.println(EaseDateUtil.daysBetween(startDate, endDate));
		Assert.assertEquals(OldDateUtil.daysBetween(startDate, endDate), EaseDateUtil.daysBetween(startDate, endDate));
		
	}
	public void testAdd() throws Exception{
		Date startDate = EaseDateUtil.parse("2016-01-01 12:23:34");
		Date oldAddDate = OldDateUtil.addDay(startDate, 1);
		Date newAddDate = EaseDateUtil.offsiteDay(startDate, 1);
		System.out.println(oldAddDate);
		System.out.println(newAddDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
		System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
		System.out.println(calendar.get(Calendar.DATE));
	}

}
