/**
 * 
 */
package org.appdot.rs.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.appdot.rs.exception.RestException;
import org.appdot.rs.utils.ResponseBuilderHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author liand
 *
 */
@Component
@Provider
public class RestExceptionMapper implements ExceptionMapper<RestException> {

	private static final Logger logger = LoggerFactory.getLogger(RestExceptionMapper.class);

	public Response toResponse(RestException exception) {
		logger.warn("found rest exception", exception);
		return ResponseBuilderHelper.build(exception.getResponse().getStatus(), exception.getResponse().getEntity());

	}

}
