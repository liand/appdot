/**
 * 
 */
package org.appdot.rs.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

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
public class UnCaughtExceptionMapper implements ExceptionMapper<Exception> {

	private static final Logger logger = LoggerFactory.getLogger(UnCaughtExceptionMapper.class);

	// TODO 使用资源文件配置
	private static final String DEFAULT_MESSAGE = "服务器内部错误";

	public Response toResponse(Exception exception) {
		logger.warn("found uncaught exception", exception);
		return ResponseBuilderHelper.buildInternalServerError(DEFAULT_MESSAGE);

	}

}
