/**
 * 
 */
package org.appdot.map.option;

/**
 * 表示一个移动基站定位的点
 * 
 * @author liand
 *
 */
public class CellPoint {

	private int lac;
	private int cellId;

	public CellPoint(int lac, int cellId) {
		this.lac = lac;
		this.cellId = cellId;
	}

	public int getLac() {
		return lac;
	}

	public void setLac(int lac) {
		this.lac = lac;
	}

	public int getCellId() {
		return cellId;
	}

	public void setCellId(int cellId) {
		this.cellId = cellId;
	}

	public static final boolean isValid(Integer cellId, Integer lac) {
		// lac and cellid are both positive.
		if (lac != null && lac > 0 && cellId != null && cellId > 0) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CellPoint [cellId=");
		builder.append(cellId);
		builder.append(", lac=");
		builder.append(lac);
		builder.append("]");
		return builder.toString();
	}

}
