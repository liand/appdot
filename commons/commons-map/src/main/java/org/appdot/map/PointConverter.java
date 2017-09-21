/**
 * 
 */
package org.appdot.map;

import org.appdot.map.option.CellPoint;
import org.appdot.map.option.GeoPoint;

/**
 * @author liand
 *
 */
public interface PointConverter {
	public GeoPoint convert(CellPoint cell);
}
