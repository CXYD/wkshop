package net.jeeshop.biz.base.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dinguangx@163.com
 * @date 2015-12-21 23:47
 */
public class PageBean<T> {
    private long recordsTotal;
    private long recordsFiltered;
    private List<T> list = new ArrayList<T>(); // 分页集合列表

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

	@Override
	public String toString() {
		return "PageBean [recordsTotal=" + recordsTotal + ", recordsFiltered="
				+ recordsFiltered + ", list=" + list + "]";
	}
}
