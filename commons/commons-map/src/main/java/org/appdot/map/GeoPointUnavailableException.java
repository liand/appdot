package org.appdot.map;

import java.io.IOException;

@SuppressWarnings("serial")
public class GeoPointUnavailableException extends IOException {

	public GeoPointUnavailableException(String arg0) {
		super(arg0);
	}

	public GeoPointUnavailableException(Throwable arg0) {
		super(arg0);
	}

	public GeoPointUnavailableException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
