package org.appdot.test.spring;

import org.junit.Before;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath*:/applicationContext-shiro.xml",
		"classpath*:/applicationContext-dao.xml", "classpath*:/applicationContext-service.xml",
		"classpath:/applicationContext-resources.xml", "classpath*:/applicationContext.xml", // for modular archetypes
		"/WEB-INF/spring-mvc.xml", "/WEB-INF/applicationContext*.xml", "/WEB-INF/security.xml" })
public abstract class BaseControllerTestCase extends AbstractTransactionalJUnit4SpringContextTests {

	@Before
	public void onSetUp() {
	}

	/**
	 * Convenience methods to make tests simpler
	 *
	 * @param url the URL to post to
	 * @return a MockHttpServletRequest with a POST to the specified URL
	 */
	public MockHttpServletRequest newPost(String url) {
		return new MockHttpServletRequest("POST", url);
	}

	public MockHttpServletRequest newGet(String url) {
		return new MockHttpServletRequest("GET", url);
	}
}
