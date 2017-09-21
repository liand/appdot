package org.appdot.map;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.appdot.map.option.GeoPoint;
import org.appdot.map.option.Marker;
import org.appdot.map.option.StaticMapOptions;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class GoogleMapSupportTest {

	@Test
	@Ignore
	public void testGetMapImage() throws IOException {
		GoogleMapSupport support = new GoogleMapSupport();
		GeoPoint point = new GeoPoint(39.979706, 116.369380);
		InputStream is = support.getMapImage(new StaticMapOptions(point, new Marker(point)));
		Assert.assertNotNull(is);
		File f = new File("mapImage.png");
		FileOutputStream fos = new FileOutputStream(f);
		IOUtils.copy(is, fos);
		fos.close();
		f.deleteOnExit();
	}

	@Test
	@Ignore
	public void testGetMapImageUrl() throws GeoPointUnavailableException {
		GoogleMapSupport support = new GoogleMapSupport();
		GeoPoint point = new GeoPoint(39.979706, 116.369380);
		String url = support.getMapImageUrl(new StaticMapOptions(point, new Marker(point)));
		System.out.println(url);
		org.junit.Assert.assertNotNull(url);
	}

}
