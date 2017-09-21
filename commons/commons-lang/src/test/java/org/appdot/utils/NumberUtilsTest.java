package org.appdot.utils;

import org.junit.Assert;
import org.junit.Test;

public class NumberUtilsTest {

	@Test
	public void testFormat() {
		String result = NumberUtils.format(1.1234d);
		Assert.assertEquals("1.12", result);
	}

	@Test
	public void testFormatPercent() {
		double d = 0.833333333333;
		String result = NumberUtils.formatPercent(d);
		Assert.assertEquals("83.33%", result);
	}

	@Test
	public void testFormatCurrency() {
		double d = 0.833333333333;
		String result = NumberUtils.formatCurrency(d);
		Assert.assertEquals("￥0.83", result);
	}

}
