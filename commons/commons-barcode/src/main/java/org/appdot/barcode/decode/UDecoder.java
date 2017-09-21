/**
 * 
 */
package org.appdot.barcode.decode;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.appdot.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;
import org.xvolks.jnative.exceptions.NativeException;
import org.xvolks.jnative.pointers.Pointer;
import org.xvolks.jnative.pointers.memory.MemoryBlockFactory;

/**
 * 优码解码器，支持对JPEG图片文件解码
 * 
 * @author Lian
 *
 */
public class UDecoder implements Decoder {

	private static Logger logger = LoggerFactory.getLogger(UDecoder.class);

	private static final String SUCCESS = "1";

	static {
		try {
			FileUtils.checkNativeFile("udecode.dll");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String decodePhotoData(InputStream is, int width, int height) {
		try {
			byte[] photoData = new byte[width * height];
			is.read(photoData);
			return decodePhotoData(photoData, width, height);
		} catch (IOException ioe) {
			logger.error("io error", ioe);
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see org.appdot.decode.Decoder#decodeJpeg(java.io.InputStream)
	 */
	public String decodeJpeg(InputStream is) {

		BufferedImage image = null;
		try {
			image = ImageIO.read(is);
			byte[] imageBody = new byte[image.getWidth() * image.getHeight()];
			int index = 0;
			for (int i = 0; i < image.getHeight(); i++) {
				for (int j = 0; j < image.getWidth(); j++) {
					int RGB = image.getRGB(j, i);
					imageBody[index] = (byte) RGB;
					index++;
				}
			}
			return decodePhotoData(imageBody, image.getWidth(), image.getHeight());
		} catch (IOException ioe) {
			logger.error("buffered image generate failed", ioe);
			return null;
		}

	}

	public String decodePhotoData(byte[] photoData, int width, int height) {
		JNative nativeFunction = null;
		try {
			// 加载DLL
			nativeFunction = new JNative("udecode", "decode_bmp_code");
			Pointer barcode = new Pointer(MemoryBlockFactory.createMemoryBlock(2000));
			Pointer msglen = new Pointer(MemoryBlockFactory.createMemoryBlock(4));
			Pointer encodedData = new Pointer(MemoryBlockFactory.createMemoryBlock(4 * width * height));

			for (int i = 0; i < photoData.length; i++) {
				encodedData.setIntAt(4 * i, photoData[i]);
			}
			for (int i = 0; i < 2000; i++) {
				barcode.setByteAt(i, (byte) 0);
			}

			int paramIndex = 0;
			nativeFunction.setRetVal(Type.INT);
			nativeFunction.setParameter(paramIndex++, encodedData);
			nativeFunction.setParameter(paramIndex++, Type.INT, String.valueOf(width));
			nativeFunction.setParameter(paramIndex++, Type.INT, String.valueOf(height));
			nativeFunction.setParameter(paramIndex++, barcode);
			nativeFunction.setParameter(paramIndex++, msglen);
			int isBGR = 0;
			nativeFunction.setParameter(paramIndex++, Type.INT, String.valueOf(isBGR));
			nativeFunction.invoke();

			String result = nativeFunction.getRetVal();
			logger.debug("返回值： {}", result);

			logger.debug("解码结果：{}", barcode.getAsString());

			if (SUCCESS.equals(result)) {
				return barcode.getAsString();
			}
			encodedData.dispose();
			barcode.dispose();
			msglen.dispose();
		} catch (NativeException ne) {
			logger.error("native function failed", ne);
		} catch (IllegalAccessException iae) {
			logger.error("native function illegal access error", iae);
		} finally {

			if (nativeFunction != null)
				try {
					nativeFunction.dispose();
				} catch (Exception e) {
					logger.warn("nativeFunction dispose error, may cause resouce leak", e);
				}
		}
		return null;
	}

}
