package org.appdot.utils;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilsTest {

	@Test
	public void testGetFirstPinyin() {
		String cnStr = "长白山（高山流水）";
		String result = CNUtils.getFirstPinyin(cnStr);
		System.out.println(cnStr + " spell is " + result);
		Assert.assertEquals("cbs", result.substring(0, 3));

		cnStr = "玉溪";
		result = CNUtils.getFirstPinyin(cnStr);
		System.out.println(cnStr + " spell is " + result);
		Assert.assertEquals("yx", result);

		cnStr = "红塔山";
		result = CNUtils.getFirstPinyin(cnStr);
		System.out.println(cnStr + " spell is " + result);
		Assert.assertEquals("hts", result);
	}

}
