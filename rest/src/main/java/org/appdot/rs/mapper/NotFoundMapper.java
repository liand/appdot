/**
 * 
 */
package org.appdot.rs.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.appdot.rs.exception.NotFoundException;
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
public class NotFoundMapper implements ExceptionMapper<NotFoundException> {

	private static final Logger logger = LoggerFactory.getLogger(NotFoundMapper.class);

	private static final String DEFAULT_MESSAGE = "找不到服务，请检查访问的地址是否正确。";

	public Response toResponse(NotFoundException exception) {
		logger.warn("service url not found exception", exception);
		return ResponseBuilderHelper.buildNotFound(DEFAULT_MESSAGE);

	}

}
