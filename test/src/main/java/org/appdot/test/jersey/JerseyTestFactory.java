/**
 * 
 */
package org.appdot.test.jersey;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.Filter;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.google.common.collect.Maps;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import com.sun.jersey.test.framework.WebAppDescriptor.Builder;

/**
 * JerseyTest工厂，可创建带Shiro上下文的或者不带安全的JerseyTest实例，只创建一次
 * 
 * @author Lian
 *
 */
public class JerseyTestFactory {

	private static JerseyTest jerseyTest;

	public static JerseyTest create(String springConfigContext, boolean isSecure) throws Exception {
		Map<Class<? extends Filter>, String> filters = Maps.newHashMap();
		if (isSecure) {
			filters.put(DelegatingFilterProxy.class, "shiroFilter");
		}
		return create(springConfigContext, filters);
	}

	public static JerseyTest create(String springConfigContext, Map<Class<? extends Filter>, String> filters)
			throws Exception {
		if (jerseyTest == null) {
			Builder builder = new WebAppDescriptor.Builder("org.appdot.archetypes.api", "org.appdot.rs")
					.contextPath("/").contextParam("contextConfigLocation", springConfigContext)
					.servletClass(SpringServlet.class).contextListenerClass(ContextLoaderListener.class);
			Set<Entry<Class<? extends Filter>, String>> filterEntries = filters.entrySet();
			for (Entry<Class<? extends Filter>, String> filterEntry : filterEntries) {
				builder.addFilter(filterEntry.getKey(), filterEntry.getValue());
			}
			jerseyTest = new JerseyTest(builder.build()) {
			};
			jerseyTest.setUp();
			initJerseyTest(jerseyTest);
		}
		return jerseyTest;
	}

	protected static void initJerseyTest(final JerseyTest jt) throws Exception {
		jt.setUp();

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				quit(jt);
			}
		});
	}

	protected static void quit(JerseyTest jt) {
		if (jt != null) {
			try {
				jt.tearDown();
			} catch (Exception e) {
				System.err.println("Error happen while quit jerseyTest :" + e.getMessage());
			}
			jt = null;
		}
	}
}
