/**
 * 
 */
package org.appdot.map;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.appdot.map.option.GeoPoint;
import org.appdot.map.option.Marker;
import org.appdot.map.option.StaticMapOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liand
 *
 */
public class GoogleMapSupport implements MapSupport {

	private static final Logger logger = LoggerFactory.getLogger(GoogleMapSupport.class);

	private static final String DEFAULT_MAP_SERVICE_URL = "http://ditu.google.cn/maps/api/staticmap";
	private static final String SEPARATOR = "%7C";
	private static final String COLON = "%3A";
	private static final String DEFAULT_API_KEY = "ABQIAAAAHxf6D4KfV1AKdrj-dOcdixTkZg5xMUwETRIzds9OY9d-xJzDjRSaZsv-v8_xY1vA8GvOf9z1ci3h2g";

	private String serviceUrl = DEFAULT_MAP_SERVICE_URL;

	private String apiKey = DEFAULT_API_KEY;

	private PointConverter pointConverter;

	public InputStream getMapImage(StaticMapOptions options) throws GeoPointUnavailableException, IOException {
		options = validateMapOptions(options);
		logger.debug("request static map options: {}", options);
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet getMap = new HttpGet(populateRequest(options));
		// 设置语言为中文
		getMap.setHeader("Accept-Language", "zh-cn");
		HttpResponse response = httpClient.execute(getMap);
		return response.getEntity().getContent();
	}

	public String getMapImageUrl(StaticMapOptions options) throws GeoPointUnavailableException {
		logger.debug("request static map options: {}", options);
		options = validateMapOptions(options);
		return populateRequest(options);
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public void setPointConverter(PointConverter pointConverter) {
		this.pointConverter = pointConverter;
	}

	private String populateRequest(StaticMapOptions options) {
		GeoPoint point = options.getGeoPoint();
		StringBuilder sb = new StringBuilder();
		sb.append(serviceUrl);
		sb.append("?");

		sb.append("center=");
		sb.append(point.getLatitude());
		sb.append(",");
		sb.append(point.getLongitude());

		sb.append("&zoom=");
		sb.append(options.getZoom());

		sb.append("&size=");
		sb.append((int) options.getDimension().getWidth());
		sb.append("x");
		sb.append((int) options.getDimension().getHeight());

		sb.append("&format=");
		sb.append(options.getFormat());

		sb.append("&maptype=");
		sb.append(options.getMapType());

		Marker[] markers = options.getMarkers();
		for (Marker marker : markers) {
			sb.append("&markers=");
			if (marker.getSize() != null) {
				sb.append("size");
				sb.append(COLON);
				sb.append(marker.getSize());
				sb.append(SEPARATOR);
			}
			if (marker.getColor() != null) {
				sb.append("color");
				sb.append(COLON);
				sb.append(marker.getColor());
				sb.append(SEPARATOR);
			}
			if (marker.getCharacter() != null) {
				sb.append("label");
				sb.append(COLON);
				sb.append(marker.getCharacter());
				sb.append(SEPARATOR);
			}
			if (marker.getGeoPoint() == null) {
				marker.setGeoPoint(options.getGeoPoint());
			}
			sb.append(SEPARATOR);
			sb.append(marker.getGeoPoint().getLatitude());
			sb.append(",");
			sb.append(marker.getGeoPoint().getLongitude());
		}

		sb.append("&key=");
		if (StringUtils.isNotBlank(options.getKey())) {
			sb.append(options.getKey());
		} else {
			sb.append(apiKey);
		}
		sb.append("&sensor=");
		sb.append(false);

		logger.debug("image url: {}", sb);
		return sb.toString();
	}

	private StaticMapOptions validateMapOptions(StaticMapOptions options) throws GeoPointUnavailableException {
		if (options.getGeoPoint() == null) {
			GeoPoint geoPoint = null;
			// 已设置pointConverter
			if (pointConverter != null) {
				geoPoint = pointConverter.convert(options.getCellPoint());
			} else {
				throw new IllegalArgumentException("no point converter and lat,lng info.");
			}
			// 转换出错
			if (geoPoint == null) {
				throw new GeoPointUnavailableException("error when converting cellPoint to geoPoint with "
						+ options.getCellPoint());
			}
			options.setGeoPoint(geoPoint);
			Marker[] markers = options.getMarkers();
			for (Marker marker : markers) {
				if (marker.getGeoPoint() == null) {
					marker.setGeoPoint(geoPoint);
				}
			}
		}
		return options;
	}
}
