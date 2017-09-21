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
public class IllegalArgumentsMapper implements ExceptionMapper<IllegalArgumentException> {

	private static final Logger logger = LoggerFactory.getLogger(IllegalArgumentsMapper.class);

	// TODO 使用资源文件配置
	private static final String DEFAULT_MESSAGE = "非法请求";

	public Response toResponse(IllegalArgumentException exception) {
		logger.warn("illegal argument exception", exception);
		return ResponseBuilderHelper.buildBadRequest(DEFAULT_MESSAGE);

	}

}
