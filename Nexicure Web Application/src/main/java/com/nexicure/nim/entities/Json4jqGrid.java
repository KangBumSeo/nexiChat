package com.nexicure.nim.entities;

import java.util.List;

import com.nexicure.nim.entities.vo.MuserVO;

public class Json4jqGrid {
    private int total;
    private int page;
    private long records;
    private List rows;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public long getRecords() {
		return records;
	}
	public void setRecords(long records) {
		this.records = records;
	}
	public List<Object> getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}

    
}
