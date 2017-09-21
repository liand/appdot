package org.appdot.barcode.decode;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author sly
 *
 */
public class TestQRDecoder {
	private static final Decoder decoder = new QRDecoder();

	public static void main(String[] args) throws IOException {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("qrcode.jpg");
		byte[] array = new byte[is.available()];
		is.read(array);
		ByteArrayInputStream in = new ByteArrayInputStream(array);
		int count = 300;
		long currentTime = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			String barcode = decoder.decodeJpeg(in);
			System.out.println(barcode);
			in.reset();
		}
		System.out.println("解析" + count + "个码所耗费时间" + (System.currentTimeMillis() - currentTime));
		is.close();
	}
}
