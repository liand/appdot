/**
 * 
 */
package org.appdot.utils;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;

/**
 * @author liand
 *
 */

public class HttpClientBuilder {

	private static HttpClientBuilder instance = new HttpClientBuilder();

	private HttpClient cachedHttpClient;

	protected HttpClientBuilder() {
	}

	public static HttpClientBuilder getInstance() {
		return instance;
	}

	public HttpClient getMultiThreadedHttpClient() {
		if (cachedHttpClient == null) {
			// Create and initialize scheme registry 
			SchemeRegistry schemeRegistry = new SchemeRegistry();
			schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
			schemeRegistry.register(new Scheme("https", 443, PlainSocketFactory.getSocketFactory()));
			ClientConnectionManager cm = new PoolingClientConnectionManager(schemeRegistry);
			cachedHttpClient = new DefaultHttpClient(cm, new BasicHttpParams());
		}
		return cachedHttpClient;
	}
}
