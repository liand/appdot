/**
 * 
 */
package org.appdot.exception;

/**
 * 应用异常的基类，所有非Runtime异常应从此继承
 * 
 * @author liand
 *
 */
public class ApplicationException extends Exception {

	private static final long serialVersionUID = -7581969997484074324L;

	public ApplicationException(String message, Throwable t) {
		super(message, t);

	}

	public ApplicationException(String message) {
		super(message);

	}

	public ApplicationException(Throwable t) {
		super(t);
	}

}
