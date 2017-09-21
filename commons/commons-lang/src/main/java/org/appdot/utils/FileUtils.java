/**
 * 
 */
package org.appdot.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件支持类
 * 
 * @author Lian
 *
 */
public class FileUtils {

	/**
	 * 根据文件名检测是否文件存在于当前运行目录下，如果不存在，尝试通过classpath加载文件，并复制到当前运行目录下
	 * 适用于加载native文件，例如dll或者so等需要存在于当前目录的特殊文件
	 */
	public static final boolean checkNativeFile(String fileName) throws IOException {
		File f = new File(fileName);
		if (f.exists()) {
			return true;
		}
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		if (is == null) {
			return false;
		}

		FileOutputStream fos = new FileOutputStream(fileName);
		int b = -1;
		while ((b = is.read()) != -1) {
			fos.write(b);
		}
		fos.flush();
		fos.close();
		return true;
	}
}
