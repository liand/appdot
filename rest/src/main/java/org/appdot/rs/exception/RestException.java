/**
 * 
 */
package org.appdot.rs.exception;


import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


/**
 * @author Lian
 *
 */
public class RestException extends WebApplicationException {

	private static final long serialVersionUID = 2988570220027236150L;

	public RestException(int status, Object entity) {
		this(buildResponse(status, entity));
	}

	/**
	 * @param cause
	 * @param response
	 */
	public RestException(Throwable cause, int status, Object entity) {
		this(cause, buildResponse(status, entity));
	}

	private static Response buildResponse(int status, Object entity) {
		return Response.status(status).entity(entity).build();
	}

	public RestException() {
		super();
	}

	/**
	 * @param response
	 */
	public RestException(Response response) {
		super(response);
	}

	/**
	 * @param status
	 */
	public RestException(int status) {
		super(status);
	}

	/**
	 * @param status
	 */
	public RestException(Status status) {
		super(status);
	}

	/**
	 * @param cause
	 */
	public RestException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param cause
	 * @param response
	 */
	public RestException(Throwable cause, Response response) {
		super(cause, response);
	}

	/**
	 * @param cause
	 * @param status
	 */
	public RestException(Throwable cause, int status) {
		super(cause, status);
	}

	/**
	 * @param cause
	 * @param status
	 */
	public RestException(Throwable cause, Status status) {
		super(cause, status);
	}

}
