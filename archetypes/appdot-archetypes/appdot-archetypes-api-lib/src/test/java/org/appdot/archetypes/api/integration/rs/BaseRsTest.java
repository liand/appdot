/**
 * 
 */
package org.appdot.archetypes.api.integration.rs;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.Filter;
import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.binary.Base64;
import org.appdot.test.jersey.JerseyTestFactory;
import org.junit.Assert;
import org.junit.BeforeClass;

import com.google.common.collect.Maps;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.JerseyTest;

/**
 * @author liand
 *
 */
public abstract class BaseRsTest {

	protected static JerseyTest jerseyTest;

	@BeforeClass
	public static void initTest() throws Exception {
		if (jerseyTest == null) {
			jerseyTest = JerseyTestFactory.create(getConfigLocations(), getFilters());
		}
	}

	protected static Map<Class<? extends Filter>, String> getFilters() {
		Map<Class<? extends Filter>, String> filters = Maps.newHashMap();
		// TODO Add filters the project needed.
		return filters;
	}

	protected static String getConfigLocations() {
		// TODO Adding your spring context files
		return "classpath:/applicationContext-dao.xml, classpath:/applicationContext-service.xml, classpath:/applicationContext-resources.xml, classpath*:/applicationContext.xml";
	}

	public WebResource resource() {
		return jerseyTest.resource();
	}

	protected String getBasicAuthorization(String username, String password) {
		try {
			return "Basic " + new String(Base64.encodeBase64((username + ":" + password).getBytes("utf-8")));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Form Login
	 * @param username
	 * @param password
	 * @param loginUrl
	 * @param postBody
	 * @param respContainedString
	 * @return	WebResource
	 */
	protected WebResource login(String username, String password, String loginUrl, String postBody,
			String respContainedString) {
		WebResource webResource = resource();
		String response = webResource.path(loginUrl).type(MediaType.APPLICATION_FORM_URLENCODED)
				.post(String.class, postBody);
		System.out.println(response);
		Assert.assertTrue(response.contains(respContainedString));
		return webResource;
	}

}
