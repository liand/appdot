package org.appdot.exception;

/**
 * Service层公用的Exception.
 * 
 * 继承自RuntimeException, 所有需要使用到RuntimeException类型异常的基类
 * 
 * 与ApplicationException最大的区别是从由Spring管理事务的函数中抛出时会触发事务回滚，并且不需要强制上层调用者Catch
 * 
 * @see ApplicationException
 * 
 * @author liand
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 3583566093089790852L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
