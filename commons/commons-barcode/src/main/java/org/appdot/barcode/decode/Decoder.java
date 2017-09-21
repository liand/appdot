/**
 * 
 */
package org.appdot.barcode.decode;

import java.io.InputStream;

/**
 * @author Lian
 *
 */
public interface Decoder {

	/**
	 * 解一张JPEG图片码
	 * @param is	图片流
	 * @return	解码结果
	 */
	public String decodeJpeg(InputStream is);

	/**
	 * 直接解图片体数据（不带图片头）
	 * @param is	图片数据流
	 * @param width	宽
	 * @param height 高
	 * @return	解码结果
	 */
	public String decodePhotoData(InputStream is, int width, int height);

	/**
	 * 直接解图片体数据（不带图片头）
	 * @param photoData	图片数据
	 * @param width	宽
	 * @param height 高
	 * @return	解码结果
	 */
	public String decodePhotoData(byte[] photoData, int width, int height);

}
