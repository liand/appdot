/**
 * 
 */
package org.appdot.test.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

/**
 * @author Lian
 *
 */
@ContextConfiguration(value = { "classpath*:/applicationContext-resources.xml",
		"classpath:/applicationContext-dao.xml", "classpath:/applicationContext-service.xml",
		"classpath*:/applicationContext.xml", "classpath*:/applicationContext-shiro.xml" })
public class BaseSpringTransactionalTestCase extends SpringTransactionalTestCase {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
}
