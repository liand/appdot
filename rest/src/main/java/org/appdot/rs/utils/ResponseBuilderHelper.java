/**
 * 
 */
package org.appdot.rs.utils;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.appdot.rs.dto.ErrorDTO;

/**
 * @author liand
 */
public class ResponseBuilderHelper {

	public static final Response buildBadRequest(String message) {
		return buildErrorResponse(Status.BAD_REQUEST, new ErrorDTO(Status.BAD_REQUEST.getStatusCode(), message));
	}

	public static final Response buildInternalServerError(String message) {
		return buildErrorResponse(Response.Status.INTERNAL_SERVER_ERROR,
				new ErrorDTO(Status.INTERNAL_SERVER_ERROR.getStatusCode(), message));
	}

	public static final Response buildNotFound(String message) {
		return buildErrorResponse(Response.Status.NOT_FOUND, new ErrorDTO(Status.NOT_FOUND.getStatusCode(), message));
	}

	public static final Response build(int status, Object entity) {
		return Response.status(status).entity(new ErrorDTO(status, entity.toString())).build();
	}

	public static final Response buildErrorResponse(Response.Status status, ErrorDTO errorDto) {
		return Response.status(status).entity(errorDto).build();
	}

	public static final Response buildErrorResponse(ErrorDTO errorDto) {
		return Response.ok().entity(errorDto).build();
	}
}
