/**
 * 
 */
package org.appdot.map.option;

/**
 * 用经纬度表示的点
 * 
 * @author liand
 *
 */
public class GeoPoint {

	/**
	 * 指定纬度值，精确到 6 位小数。
	 */
	private double latitude;

	/**
	 * 指定经度值，精确到 6 位小数。
	 */
	private double longitude;

	public GeoPoint(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GeoPoint [latitude=");
		builder.append(latitude);
		builder.append(", longitude=");
		builder.append(longitude);
		builder.append("]");
		return builder.toString();
	}

}
