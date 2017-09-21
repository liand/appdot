/**
 * 
 */
package org.appdot.rs.exception;

import javax.ws.rs.core.Response.Status;

import org.appdot.rs.dto.ErrorDTO;
import org.appdot.rs.utils.ResponseBuilderHelper;

/**
 * @author liand
 *
 */
public class UnAuthorizedException extends RestException {

	private static final long serialVersionUID = -5126377071960824767L;

	private static final String DEFAULT_MESSAGE = "用户未认证";

	public UnAuthorizedException() {
		this(DEFAULT_MESSAGE);
	}

	public UnAuthorizedException(String message) {
		super(ResponseBuilderHelper.buildErrorResponse(new ErrorDTO(Status.UNAUTHORIZED.getStatusCode(), message)));
	}
}
