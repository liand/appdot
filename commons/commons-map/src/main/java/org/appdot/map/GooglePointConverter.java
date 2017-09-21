/**
 * 
 */
package org.appdot.map;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.appdot.map.option.CellPoint;
import org.appdot.map.option.GeoPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author liand
 *
 */
public class GooglePointConverter implements PointConverter {

	private static final Logger logger = LoggerFactory.getLogger(GooglePointConverter.class);

	private static final String HOST_URL = "http://www.google.com/glm/mmap";

	/* (non-Javadoc)
	 * @see org.appdot.map.PointConverter#convert(com.keesail.map.option.CellPoint)
	 */
	public GeoPoint convert(CellPoint cell) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost postMethod = new HttpPost(HOST_URL);
		postMethod.setEntity(new MyCellIDRequestEntity(cell.getCellId(), cell.getLac()));
		try {
			HttpResponse response = httpClient.execute(postMethod);

			DataInputStream dis = new DataInputStream(response.getEntity().getContent());
			// Read some prior data 
			dis.readShort();
			dis.readByte();
			// Read the error-code 
			int errorCode = dis.readInt();
			if (errorCode == 0) {
				double lat = dis.readInt() / 1000000D;
				double lng = dis.readInt() / 1000000D;
				int range = dis.readInt(); // in metres
				if (logger.isDebugEnabled()) {
					logger.debug("From google with love: lat={}, lng={}, range={}", new Object[] { lat, lng, range });
				}
				return new GeoPoint(lat, lng);
			} else {
				logger.warn("error when get gps point from google, errorCode={}", errorCode);
				return null;
			}

		} catch (IOException ioe) {
			logger.warn("error occured when convert cell to gps point.", ioe);
			return null;
		} finally {
			postMethod.releaseConnection();
		}
	}

	static class MyCellIDRequestEntity implements HttpEntity {
		protected int myCellID;
		protected int myLAC;

		public MyCellIDRequestEntity(int aCellID, int aLAC) {
			this.myCellID = aCellID;
			this.myLAC = aLAC;
		}

		public Header getContentType() {
			return new BasicHeader("Content-Type", "application/binary");
		}

		public boolean isRepeatable() {
			return false;
		}

		public long getContentLength() {
			return -1;
		}

		@Override
		public boolean isChunked() {
			return false;
		}

		@Override
		public Header getContentEncoding() {
			return null;
		}

		@Override
		public InputStream getContent() throws IOException, IllegalStateException {
			return null;
		}

		/** Pretend to be a French Sony_Ericsson-K750 that 
		 * wants to receive its lat/long-values =) 
		 * The data written is highly proprietary !!! */
		@Override
		public void writeTo(OutputStream outstream) throws IOException {
			DataOutputStream os = new DataOutputStream(outstream);
			os.writeShort(21);
			os.writeLong(0);
			os.writeUTF("fr");
			os.writeUTF("Sony_Ericsson-K750");
			os.writeUTF("1.3.1");
			os.writeUTF("Web");
			os.writeByte(27);

			os.writeInt(0);
			os.writeInt(0);
			os.writeInt(3);
			os.writeUTF("");
			os.writeInt(myCellID); // CELL-ID 
			os.writeInt(myLAC); // LAC 
			os.writeInt(0);
			os.writeInt(0);
			os.writeInt(0);
			os.writeInt(0);
			os.flush();
		}

		@Override
		public boolean isStreaming() {
			return false;
		}

		@Override
		public void consumeContent() throws IOException {

		}

	}
}
