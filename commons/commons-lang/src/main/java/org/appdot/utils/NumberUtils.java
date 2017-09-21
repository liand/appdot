/**
 * 
 */
package org.appdot.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * 数值Utility类
 * 
 * {@link #format(double)}	格式化
 * {@link #isPositive(Number)}	正数判断
 * 
 * @author liand
 *
 */
public class NumberUtils {

	public static final NumberFormat NF = NumberFormat.getNumberInstance();
	static {
		NF.setMinimumFractionDigits(2);
		NF.setMaximumFractionDigits(2);
	}

	private static final NumberFormat PERCENT_NF = NumberFormat.getPercentInstance(Locale.CHINA);
	static {
		PERCENT_NF.setMaximumFractionDigits(2);
	}

	private static final NumberFormat CURRENCY_NF = NumberFormat.getCurrencyInstance(Locale.CHINA);
	static {
		CURRENCY_NF.setMaximumFractionDigits(2);
		CURRENCY_NF.setMinimumFractionDigits(2);
	}

	/**
	 * 返回精确为2位小数的数字字符串
	 */
	public static final String format(double value) {
		return NF.format(value);
	}

	/**
	 * 返回精确到2位小数的百分比字符串，例如0.377777777返回37.78%
	 */
	public static final String formatPercent(double value) {
		return PERCENT_NF.format(value);
	}

	/**
	 * 返回精确到2位小数的货币字符串，例如0.377777777返回￥0.38
	 */
	public static final String formatCurrency(double value) {
		return CURRENCY_NF.format(value);
	}

	/**
	 * 判断数值是否不为空并且大于0
	 * @param n	可以是整数或小数
	 * @return	true表示合法，即n为正数
	 */
	public static final boolean isPositive(Number... number) {
		for (Number n : number) {
			if (n == null || n.doubleValue() <= 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 返回数的值，null返回0
	 */
	public static final Number getNumberSafely(Number number) {
		if (number == null) {
			return 0;
		}
		return number;
	}

	/**
	 * double型的 四舍五入取整
	 * @param d
	 * @deprecated	please use Math.round(double)
	 */
	public static Long getRoundHalfUp(Double d) {
		if (d == null) {
			return 0L;
		}
		BigDecimal bd = new BigDecimal(d).setScale(0, BigDecimal.ROUND_HALF_UP);
		return bd.longValue();
	}

}
