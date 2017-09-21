package org.appdot.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.time.DateFormatUtils;

/**
 * @author haifeng E-mail:changhaifeng@kingdeed.cn
 * @version 创建时间：2009-11-7 下午05:02:38 类说明，获取时间基础类
 */
public class DateUtils {

	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static final SimpleDateFormat sdfNoTime = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 获取某月的开始时间
	 *
	 * @param monthDiff eg. -1 表示当前月的前一个月, 0表示当前月, +1表示下一个月
	 */
	public static Date getStartOfMonth(int monthDiff) {
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.MONTH, monthDiff);
		cal = org.apache.commons.lang.time.DateUtils.truncate(cal, Calendar.MONTH);
		return cal.getTime();
	}

	/**
	 * 获取月份最后的结束时间
	 * 
	 * @param monthDiff eg. -1 表示当前月的前一个月, 0表示当前月, +1表示下一个月
	 */
	public static Date getEndOfMonth(int monthDiff) {
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		cal.set(Calendar.DATE, 1);
		cal.add(Calendar.MONTH, monthDiff);
		cal = org.apache.commons.lang.time.DateUtils.ceiling(cal, Calendar.MONTH);
		return cal.getTime();
	}

	/**
	 * 获取月份最后的结束时间
	 * @param year	指定年份
	 * @param month	指定月（从1开始）
	 */
	public static Date getEndOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.MONTH, month - 1);
		cal = org.apache.commons.lang.time.DateUtils.ceiling(cal, Calendar.MONTH);
		return cal.getTime();
	}

	/**
	 * 根据"年"、"月"获得月初第一天
	 *
	 * @param year
	 * @param month 从1开始
	 */
	public static Date getStartOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.MONTH, month - 1);
		cal = org.apache.commons.lang.time.DateUtils.truncate(cal, Calendar.MONTH);
		return cal.getTime();
	}

	/**
	 * 返回YYYY-MM-dd HH:mm:ss的默认时间格式字符串
	 * @param d	日期时间
	 */
	public static String getDefaultDateStr(Date d) {
		return DateFormatUtils.format(d, DEFAULT_DATE_PATTERN);
	}

	/**
	 * 获得某时间之前一个月的时间
	 * 例如：当前时间是2010-10-05 12:44:55，
	 * 则此方法获得的时间为2010-09-05 12:44:55
	 *
	 * @param now	参照的时间
	 */
	@SuppressWarnings("static-access")
	public static Date getOneMonthBefore(Date now) {
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		cal.setTime(now);
		cal.add(Calendar.MONTH, cal.MONTH - 3);
		return cal.getTime();
	}

	/**
	 * 获取本周的开始时间
	 * 
	 * @return 本周第一天零时零分零秒 （周一）
	 */
	public static Date startOfWeek() {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		int dayofweek = cal.get(Calendar.DAY_OF_WEEK) - cal.getFirstDayOfWeek();
		cal.add(Calendar.DATE, 1 - dayofweek);
		return org.apache.commons.lang.time.DateUtils.truncate(cal.getTime(), Calendar.DATE);
	}

	/**
	 * 获取指定时间的周开始时间
	 * 
	 * @return 所需年月周的第一天零时零分零秒 （周一）
	 */
	public static Date startOfWeek(Date date) {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(date);
		int dayofweek = cal.get(Calendar.DAY_OF_WEEK) - cal.getFirstDayOfWeek();
		cal.add(Calendar.DATE, 1 - dayofweek);
		return org.apache.commons.lang.time.DateUtils.truncate(cal.getTime(), Calendar.DATE);
	}

	/**
	 * 获取指定的周开始时间
	 * 
	 * @return 所需年月周的零时零分零秒 （周一）
	 */
	public static Date startOfWeek(int year, int month, int week) {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, (week - 1) * 7 + 1);
		return cal.getTime();
	}

	/**
	 * 获取指定的周末尾时间（下周一的00:00:00）
	 * 
	 * 获得所需年月周的零时零分零秒 （下个周一）
	 */
	public static Date endOfWeek(int year, int month, int week) {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, week * 7 + 1);
		return cal.getTime();
	}

	/**
	 * 获得参考时间所在年月周的下一周开始时间
	 */
	public static Date endOfWeek(Date date) {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(date);
		int dayofweek = cal.get(Calendar.DAY_OF_WEEK) - cal.getFirstDayOfWeek();
		cal.add(Calendar.DATE, 7 - dayofweek);
		cal.add(Calendar.DATE, dayofweek - 7);
		return org.apache.commons.lang.time.DateUtils.ceiling(cal.getTime(), Calendar.DATE);
	}

	/**
	 * 获得本周的最后一天最后一刻即23时59分59秒
	 */
	public static Date endOfWeek() {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		int dayofweek = cal.get(Calendar.DAY_OF_WEEK) - cal.getFirstDayOfWeek();
		cal.add(Calendar.DATE, 7 - dayofweek);
		cal.add(Calendar.DATE, dayofweek - 7);
		return org.apache.commons.lang.time.DateUtils.ceiling(cal.getTime(), Calendar.DATE);
	}

	/**
	 * 得到某月的总天数
	 */
	public static int getDayCountOfMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DATE, -1);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 日期减去分钟差值后的时间
	 *
	 * @param timeDiff 分钟差值	单位：分钟
	 */
	public static Date getDiffTime(Date date, Long timeDiff) {
		if (null != timeDiff) {
			Long milliseconds = date.getTime();
			Long timeDiffLong = timeDiff * 60 * 1000;
			Date result = new Date(milliseconds - timeDiffLong);
			return result;
		} else {
			return date;
		}

	}

	/**
	 * 格式化日期到缺省的格式字符串yyyy-MM-dd
	 */
	public static String format2DefaultYMD(Date source) {
		return sdfNoTime.format(source);
	}

	/**
	 * 获取已经过去的月份
	 * 
	 * @param year	指定年
	 * @return	月份的整型数组（从1开始）
	 */
	public static Integer[] getMonthByYear(int year) {
		List<Integer> months = new ArrayList<Integer>();
		int maxMonth = 12;
		if (Calendar.getInstance().get(Calendar.YEAR) == year) {
			maxMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		}
		for (int i = 1; i <= maxMonth; i++) {
			months.add(i);
		}
		return months.toArray(new Integer[0]);
	}

	/**
	 * 获取当年已经过去的月份
	 * 
	 * @return	月份的整型数组（从1开始）
	 */
	public static Integer[] getMonthByYear() {
		return getMonthByYear(Calendar.getInstance().get(Calendar.YEAR));
	}

	/**
	 * 获取最近4年
	 * 
	 * @return	年的整型数组
	 */
	public static Integer[] getNearlyYears() {
		List<Integer> years = new ArrayList<Integer>();
		int nowYear = Calendar.getInstance().get(Calendar.YEAR);
		for (int i = nowYear - 3; i <= nowYear; i++) {
			years.add(i);
		}
		return years.toArray(new Integer[0]);
	}

	/**
	 * 获取当月已经过去的周
	 * 
	 * @return	周的整型数组，例如现在是本月第3周，则返回{1,2,3}
	 */
	public static List<Integer> getNearlyWeekByMonth() {
		List<Integer> weeksObj;
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		int thisWeek = cal.get(Calendar.WEEK_OF_MONTH);
		weeksObj = new ArrayList<Integer>();
		for (int i = 1; i <= thisWeek; i++) {
			weeksObj.add(i);
		}
		return weeksObj;
	}

	/**
	 * 获取当前周的第一天,如果第一天不在当前月,则取当前月的第一天
	 */
	public static Date startOfWeekInMonth(int year, int month, int week) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.WEEK_OF_MONTH, week);
		c.set(Calendar.DAY_OF_WEEK, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		if (c.get(Calendar.MONTH) != month - 1) {
			return getStartOfMonth(year, month);
		}
		return c.getTime();
	}

	/**
	 * 获取当前周的最后一天,如果最后一天不在当前月,则取当前月的最后一天
	 */
	public static Date getEndOfWeekInMonth(int year, int month, int week) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.WEEK_OF_MONTH, week);
		c.set(Calendar.DAY_OF_WEEK, 7);
		c.set(Calendar.HOUR_OF_DAY, 24);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		if (c.get(Calendar.MONTH) != month - 1) {
			return getEndOfMonth(year, month);
		}
		return c.getTime();
	}

	/**
	 * 获取指定年月的星期数
	 * @param year	年
	 * @param month	月，从1开始
	 * @return
	 */
	public static int getWeeksInMonth(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		return c.getMaximum(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * 返回YYYYMM字符串表示年月
	 * @param year	年
	 * @param month	月（从1开始）
	 * @return
	 */
	public static String getStringDate(Integer year, Integer month) {
		StringBuilder sb = new StringBuilder();
		sb.append(year);
		if (month < 10) {
			sb.append(0);
		}
		sb.append(month);
		if (sb.toString().length() != 6) {
			throw new IllegalArgumentException("check the params year: " + year + ", month: " + month);
		}
		return sb.toString();
	}

	/**
	 * 获得6位年月字符串
	 * @param date	日期
	 * @return	yyyyMM 月从01开始
	 */
	public static String getStringDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return getStringDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1);
	}

	/**
	 * 根据6位年月字符串得到整形的年份
	 * @param ym	yyyyMM	6位字符串表示的年月，月从01开始
	 * @return		年
	 */
	public static Integer getYearFromYm(String ym) {
		String year = ym.substring(0, 4);
		return Integer.parseInt(year);
	}

	/**
	 * 根据6位年月字符串得到整形的月份
	 * @param ym	yyyyMM	6位字符串表示的年月，月从01开始
	 * @return	月份	从1开始
	 */
	public static Integer getMonthFromYm(String ym) {
		String month = ym.substring(4, 6);
		return Integer.parseInt(month);
	}

	/**
	 * 获取缺省的当前时间
	 */
	public static Date getNow() {
		return new Date();
	}

}
