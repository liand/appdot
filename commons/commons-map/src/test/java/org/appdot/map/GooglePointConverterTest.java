/**
 * 
 */
package org.appdot.map;

import org.appdot.map.option.CellPoint;
import org.appdot.map.option.GeoPoint;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author liand
 *
 */
public class GooglePointConverterTest {

	/**
	 * Test method for {@link com.keesail.map.GooglePointConverter#convert(org.appdot.map.option.CellPoint)}.
	 */
	@Test
	@Ignore
	public void testConvert() {
		GooglePointConverter converter = new GooglePointConverter();
		GeoPoint point = converter.convert(new CellPoint(16449, 40053));
		System.out.println(point);
		Assert.assertNotNull(point);
	}

}
