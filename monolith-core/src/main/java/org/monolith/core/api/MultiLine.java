package org.monolith.core.api;

import java.util.List;
import java.util.Map;

public class MultiLine {
	private long total;
	private List<Map<String, Object>> rows;

	public MultiLine(long total, List<Map<String, Object>> rows) {
		this.total = total;
		this.rows = rows;
	}

	public MultiLine() {
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<Map<String, Object>> getRows() {
		return rows;
	}

	public void setRows(List<Map<String, Object>> rows) {
		this.rows = rows;
	}

}
