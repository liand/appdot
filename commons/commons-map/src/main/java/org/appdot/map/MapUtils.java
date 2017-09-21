package org.appdot.map;

import org.appdot.map.option.GeoPoint;

/**
 * 根据经纬度获取距离，单位为米
 *
 */
public class MapUtils {
	
	private final static double EARTH_RADIUS = 6378.137*1000;
	private static double rad(double d)
	{
	   return d * Math.PI / 180.0;
	}

	public static double getDistance(double lat1, double lng1, double lat2, double lng2)
	{	
	   double radLat1 = rad(lat1);
	   double radLat2 = rad(lat2);
	   double a = radLat1 - radLat2;
	   double b = rad(lng1) - rad(lng2);
	   double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) + Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
	   s = s * EARTH_RADIUS;
	   s = Math.round(s * 10000) / 10000;
	   return s;
	}
	
	public static double getDistance(GeoPoint point1, GeoPoint point2)
	{	
		double lat1 = point1.getLatitude();
		double lng1 = point1.getLongitude(); 
		double lat2 = point2.getLatitude();
		double lng2 = point2.getLongitude();
		return getDistance(lat1,lng1,lat2,lng2);
	}
	
	public static void main(String[] args) {
		GeoPoint beijing = new GeoPoint(39.9,116.3);
		GeoPoint guangzhou = new GeoPoint(23.167,113.233);
		System.out.println(getDistance(beijing,guangzhou));
	}
}
