/**
 * 
 */
package org.appdot.map.option;

import java.awt.Dimension;
import java.util.Arrays;

/**
 * @author liand
 *
 */
public class StaticMapOptions {

	/**
	 * 指定经纬度值，精确到 6 位小数。
	 * @see GeoPoint
	 */
	private GeoPoint geoPoint;

	/**
	 * 基站位置信息，除经纬度外的第二选择。
	 */
	private CellPoint cellPoint;

	private int zoom = 16;

	private Dimension dimension = new Dimension(640, 480);

	private ImageFormat format = ImageFormat.PNG;

	private MapType mapType = MapType.roadmap;

	private Marker[] markers;

	private Path path;

	private boolean sensor = false;

	private String key;

	public StaticMapOptions() {
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public StaticMapOptions(GeoPoint geoPoint) {
		this(geoPoint, new Marker(geoPoint));
	}

	public StaticMapOptions(GeoPoint geoPoint, Marker marker) {
		this.geoPoint = geoPoint;
		this.markers = new Marker[] { marker };
	}

	public GeoPoint getGeoPoint() {
		return geoPoint;
	}

	public void setGeoPoint(GeoPoint geoPoint) {
		this.geoPoint = geoPoint;
	}

	public int getZoom() {
		return zoom;
	}

	public void setZoom(int zoom) {
		this.zoom = zoom;
	}

	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	public ImageFormat getFormat() {
		return format;
	}

	public void setFormat(ImageFormat format) {
		this.format = format;
	}

	public MapType getMapType() {
		return mapType;
	}

	public void setMapType(MapType mapType) {
		this.mapType = mapType;
	}

	public Marker[] getMarkers() {
		return markers;
	}

	public void setMarkers(Marker[] markers) {
		this.markers = markers;
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public boolean isSensor() {
		return sensor;
	}

	public void setSensor(boolean sensor) {
		this.sensor = sensor;
	}

	public CellPoint getCellPoint() {
		return cellPoint;
	}

	public void setCellPoint(CellPoint cellPoint) {
		this.cellPoint = cellPoint;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StaticMapOptions [cellPoint=");
		builder.append(cellPoint);
		builder.append(", dimension=");
		builder.append(dimension);
		builder.append(", format=");
		builder.append(format);
		builder.append(", geoPoint=");
		builder.append(geoPoint);
		builder.append(", key=");
		builder.append(key);
		builder.append(", mapType=");
		builder.append(mapType);
		builder.append(", markers=");
		builder.append(Arrays.toString(markers));
		builder.append(", path=");
		builder.append(path);
		builder.append(", sensor=");
		builder.append(sensor);
		builder.append(", zoom=");
		builder.append(zoom);
		builder.append("]");
		return builder.toString();
	}

}
