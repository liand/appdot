package org.appdot.barcode.decode;

import java.io.InputStream;

import junit.framework.Assert;

public class TestUDecoder {

	static UDecoder decoder = new UDecoder();

	public static void main(String[] args) {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("as10000000.jpg");
		Assert.assertNotNull(is);
		String barcode = decoder.decodeJpeg(is);
		Assert.assertEquals("as10000000", barcode);
	}
}
