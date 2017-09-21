/**
 * 
 */
package org.appdot.utils;

import java.util.Map;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import com.google.common.collect.Maps;

/**
 * 
 * 字符串Utility方法
 * 
 * @author liand
 *
 */
public class CNUtils {

	private static final HanyuPinyinOutputFormat PINYIN_FORMAT = new HanyuPinyinOutputFormat();
	private static final Map<String, String> DEFAULT_CN_CHARS = Maps.newHashMap();
	static {
		PINYIN_FORMAT.setVCharType(HanyuPinyinVCharType.WITH_V);
		PINYIN_FORMAT.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		PINYIN_FORMAT.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		DEFAULT_CN_CHARS.put("长", "c");
	}

	/**
	 * 将中文字符翻译为拼音首字母，例如我们为wm
	 * @param chinese	中文字符串
	 * @return			简拼
	 */
	public static final String getFirstPinyin(String chinese) {

		char[] cnChars = chinese.toCharArray();
		StringBuilder pyResult = new StringBuilder();
		for (char cnChar : cnChars) {
			if (DEFAULT_CN_CHARS.containsKey(String.valueOf(cnChar))) {
				pyResult.append(DEFAULT_CN_CHARS.get(String.valueOf(cnChar)));
				continue;
			}
			String[] pyStrs;
			try {
				pyStrs = PinyinHelper.toHanyuPinyinStringArray(cnChar, PINYIN_FORMAT);
			} catch (BadHanyuPinyinOutputFormatCombination e) {
				continue;
			}
			if (pyStrs == null) {
				continue;
			}
			for (int i = 0; i < pyStrs.length; i++) {
				if (org.apache.commons.lang.StringUtils.isNotBlank(pyStrs[i])) {
					pyResult.append(pyStrs[i].charAt(0));
					break;
				}
			}
		}
		return pyResult.toString();
	}

	/**
	 * 将中文字符翻译为拼音字母，例如我们为women
	 * @param chinese	中文字符串
	 * @return			全拼
	 */
	public static final String getQuanpin(String chinese) {
		char[] cnChars = chinese.toCharArray();
		StringBuilder pyResult = new StringBuilder();
		for (char cnChar : cnChars) {
			String[] pyStrs;
			try {
				pyStrs = PinyinHelper.toHanyuPinyinStringArray(cnChar, PINYIN_FORMAT);
			} catch (BadHanyuPinyinOutputFormatCombination e) {
				continue;
			}
			if (pyStrs == null) {
				continue;
			}
			for (String pyStr : pyStrs) {
				if (org.apache.commons.lang.StringUtils.isNotBlank(pyStr)) {
					pyResult.append(pyStr);
					break;
				}
			}
		}
		return pyResult.toString();
	}

	/**
	 * 首字母小写
	 */
	public static final String getFirstLetterLower(String oldStr) {
		StringBuilder sb = new StringBuilder();
		String firstLetter = oldStr.substring(0, 1);
		String firstLetterLower = firstLetter.toLowerCase();
		sb.append(firstLetterLower);
		sb.append(oldStr.substring(1));
		return sb.toString();
	}
}
