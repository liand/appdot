package org.appdot.utils;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void testFirstDayOfWeek() {
		Date startOfWeek = DateUtils.startOfWeek();
		System.out.println(startOfWeek);
	}

	@Test
	public void testlastDayOfWeek() {
		Date lastOfWeek = DateUtils.endOfWeek();
		System.out.println(lastOfWeek);
	}

	@Test
	public void testStartOfWeek() {
		int year = 2010;
		int month = 5;
		int week = 2;
		Date start = DateUtils.startOfWeek(year, month, week);
		start = DateUtils.startOfWeek(start);
		System.out.println(start);
	}

	@Test
	public void testGetFirstDayOfMonth() {
		Date result1 = DateUtils.getStartOfMonth(2011, 6);
		Date result2 = DateUtils.getStartOfMonth(2011, 5);
		System.out.println(result1);
		System.out.println(result2);
		Assert.assertEquals(6, result1.getMonth() + 1);
		Assert.assertEquals(5, result2.getMonth() + 1);
	}

	@Test
	public void testGetLastDayOfMonth() {
		Date result1 = DateUtils.getEndOfMonth(2011, 6);
		Date result2 = DateUtils.getEndOfMonth(2011, 5);
		System.out.println(result1);
		System.out.println(result2);
		Assert.assertEquals(7, result1.getMonth() + 1);
		Assert.assertEquals(6, result2.getMonth() + 1);
	}
}
