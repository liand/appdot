/**
 * 
 */
package org.appdot.rs.exception;

import org.appdot.rs.utils.ResponseBuilderHelper;

/**
 * @author liand
 *
 */
public class NotFoundException extends RestException {

	private static final long serialVersionUID = -5126377071960824767L;

	private static final String DEFAULT_MESSAGE = "未找到对象";

	public NotFoundException() {
		this(DEFAULT_MESSAGE);
	}

	public NotFoundException(String message) {
		super(ResponseBuilderHelper.buildNotFound(message));
	}
}
