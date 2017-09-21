/**
 * 
 */
package org.appdot.rs.exception;

import org.appdot.rs.utils.ResponseBuilderHelper;

/**
 * @author liand
 *
 */
public class BadRequestException extends RestException {

	private static final long serialVersionUID = -7985147778733442753L;

	private static final String DEFAULT_MESSAGE = "非法请求";

	public BadRequestException() {
		this(DEFAULT_MESSAGE);
	}

	/**
	 * @param message
	 */
	public BadRequestException(String message) {
		super(ResponseBuilderHelper.buildBadRequest(message));
	}

}
