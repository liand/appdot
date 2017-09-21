/**
 * 
 */
package org.appdot.exception;

/**
 * 通用的数据未找到异常
 * 
 * @author liand
 *
 */
public class DataNotFoundException extends ApplicationException {

	private static final long serialVersionUID = -3942538066622259969L;

	public DataNotFoundException(String message, Throwable t) {
		super(message, t);
	}

	/**
	 * @param message
	 */
	public DataNotFoundException(String message) {
		super(message);
	}

	/**
	 * @param t
	 */
	public DataNotFoundException(Throwable t) {
		super(t);
	}

}
