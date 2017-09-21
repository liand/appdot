/**
 * 
 */
package org.appdot.rs.exception;

import org.appdot.rs.utils.ResponseBuilderHelper;

/**
 * @author liand
 *
 */
public class InternalServerErrorException extends RestException {

	private static final long serialVersionUID = -5126377071960824767L;

	private static final String DEFAULT_MESSAGE = "服务器内部错误";

	public InternalServerErrorException() {
		this(DEFAULT_MESSAGE);
	}

	public InternalServerErrorException(String message) {
		super(ResponseBuilderHelper.buildInternalServerError(message));
	}
}
