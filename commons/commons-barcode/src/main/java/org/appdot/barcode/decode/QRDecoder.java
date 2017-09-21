package org.appdot.barcode.decode;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

public class QRDecoder implements Decoder {

	private static final Logger logger = LoggerFactory.getLogger(QRDecoder.class);

	@Override
	public String decodeJpeg(InputStream is) {

		try {
			BufferedImage image = ImageIO.read(is);
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

	@Override
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

	@Override
	public String decodePhotoData(byte[] photoData, int width, int height) {
		Result result = decode(photoData, width, height);
		return result == null ? null : result.getText();
	}

	private Result decode(byte[] photoData, int width, int height) {
		Map<DecodeHintType, Object> hints = new EnumMap<DecodeHintType, Object>(DecodeHintType.class);
		hints.put(DecodeHintType.TRY_HARDER, BarcodeFormat.QR_CODE);
		hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
		hints.put(DecodeHintType.CHARACTER_SET, "UTF8");
		int[] rgbData = new int[photoData.length];
		for (int i = 0; i < rgbData.length; i++) {
			rgbData[i] = photoData[i];
		}
		try {
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new RGBLuminanceSource(width, height, rgbData)));
			Result result = new MultiFormatReader().decode(bitmap, hints);
			return result;
		} catch (NotFoundException nfe) {
			return null;
		}
	}
}
