/**
 * 
 */
package org.appdot.map;

import java.io.IOException;
import java.io.InputStream;

import org.appdot.map.option.StaticMapOptions;

/**
 * @author liand
 *
 */
public interface MapSupport {

	public String getMapImageUrl(StaticMapOptions options) throws GeoPointUnavailableException;

	public InputStream getMapImage(StaticMapOptions options) throws IOException;

}
