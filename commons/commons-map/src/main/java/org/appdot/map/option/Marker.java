/**
 * 
 */
package org.appdot.map.option;

/**
 * @author liand
 *
 */
public class Marker {

	/***
	 * 纬度值
	 */
	private GeoPoint geoPoint;

	/***
	 * 尺寸
	 */
	private MarkerSize size = MarkerSize.mid;

	public static enum MarkerSize {
		tiny, mid, small
	}

	private MarkerColor color = MarkerColor.red;

	public static enum MarkerColor {
		black, brown, green, purple, yellow, blue, gray, orange, red, white
	}

	private Character character = 'A';

	public Marker() {
	}

	public Marker(GeoPoint geoPoint) {
		this.geoPoint = geoPoint;
	}

	public MarkerSize getSize() {
		return size;
	}

	public void setSize(MarkerSize size) {
		this.size = size;
	}

	public MarkerColor getColor() {
		return color;
	}

	public void setColor(MarkerColor color) {
		this.color = color;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public GeoPoint getGeoPoint() {
		return geoPoint;
	}

	public void setGeoPoint(GeoPoint geoPoint) {
		this.geoPoint = geoPoint;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Marker [character=");
		builder.append(character);
		builder.append(", color=");
		builder.append(color);
		builder.append(", geoPoint=");
		builder.append(geoPoint);
		builder.append(", size=");
		builder.append(size);
		builder.append("]");
		return builder.toString();
	}

}
