package net.jeeshop.web.util;

import java.util.Date;
import java.util.List;


public final class Page<T> {

	public Page() {
		super();
	} 
	//当前页
	private int currentPage;
	//总记录数
	private int count;
	//每页显示的记录数
	private int pageSize;
	//总页数
	private int pageCount;
	//上一页
	private int previousPage;
	//下一页
	private int nextPage;
	//连续显示几个页码数
	public static final int pageNumCount = 3;
	//连续显示的页码数的第一个页码数
	private int firstPageNum;
	//连续显示的最后一个页码数
	private int lastPageNum;
	
	public static final int DEFAULT_PAGE_SIZE = 5;
	private List<T> list;
	//当前页的起始数据的页码
	private int startRecord;
	private int endRecord;
	
	public static int getDefaultPageSize() {
		return DEFAULT_PAGE_SIZE;
	}

	public void setCount(int count) {
		this.count = count;
	}


	

	public Page(String current, int count, String pageSize) {
		this.count = count;
		init(current, pageSize);
		if(pageCount<=pageNumCount) {
			firstPageNum = 1;
			lastPageNum = pageCount;
		} else {
			firstPageNum = currentPage-pageNumCount/2;
			lastPageNum = currentPage+pageNumCount/2;
			
			if(firstPageNum<1) {
				firstPageNum = 1;
				lastPageNum = pageNumCount;
			}
			if(lastPageNum>pageCount) {
				lastPageNum = pageCount;
				firstPageNum = pageCount-pageNumCount+1;
			}
		}
	}

	private void init(String current, String size) {
		initPageSize(size);
		initPageCount();
		initCurrentPage(current);
		initPreviousPage();
		initNextPage();
		initStartRecord();
		initEndRecord();
	}

	private void initPageSize(String size) {
		if (size == null || size.trim().isEmpty()) {
			pageSize = DEFAULT_PAGE_SIZE;
			return;
		}
		try {
			pageSize = Integer.parseInt(size);
		} catch (Exception e) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
	}

	private void initEndRecord() {
		endRecord = startRecord + pageSize - 1;
		if (endRecord > count) {
			endRecord = count;
		}
	}

	private void initStartRecord() {
		startRecord = (currentPage - 1) * pageSize;
		if (startRecord < 0) {
			startRecord = 0;
		}
		if (startRecord > count) {
			startRecord = count;
		}
	}

	private void initNextPage() {
		nextPage = currentPage + 1;
		if (nextPage > pageCount) {
			nextPage = pageCount;
		}
	}

	private void initPreviousPage() {
		previousPage = currentPage - 1;
		if (previousPage < 1) {
			previousPage = 1;
		}
	}

	private void initCurrentPage(String current) {
		if (current == null || current.trim().isEmpty()) {
			currentPage = 1;
			return;
		}
		try {
			currentPage = Integer.parseInt(current);
		} catch (Exception e) {
			currentPage = 1;
		}
		if (currentPage < 1) {
			currentPage = 1;
			return;
		}
		if (currentPage > pageCount) {
			currentPage = pageCount;
		}
	}

	private void initPageCount() {
		pageCount = count / pageSize;
		if (count % pageSize != 0) {
			pageCount++;
		}
	}

	public int getCount() {
		return count;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}



	public int getPreviousPage() {
		return previousPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public int getStartRecord() {
		return startRecord;
	}

	public int getEndRecord() {
		return endRecord;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getFirstPageNum() {
		return firstPageNum;
	}

	public void setFirstPageNum(int firstPageNum) {
		this.firstPageNum = firstPageNum;
	}

	public int getLastPageNum() {
		return lastPageNum;
	}

	public void setLastPageNum(int lastPageNum) {
		this.lastPageNum = lastPageNum;
	}

	public static int getPagenumcount() {
		return pageNumCount;
	}

	@Override
	public String toString() {
		return "Page{" +
				"currentPage=" + currentPage +
				", count=" + count +
				", pageSize=" + pageSize +
				", pageCount=" + pageCount +
				", previousPage=" + previousPage +
				", nextPage=" + nextPage +
				", firstPageNum=" + firstPageNum +
				", lastPageNum=" + lastPageNum +
				", list=" + list +
				", startRecord=" + startRecord +
				", endRecord=" + endRecord +
				", T_0206='" + T_0206 + '\'' +
				", zifei='" + zifei + '\'' +
				", tuijian='" + tuijian + '\'' +
				", sort='" + sort + '\'' +
				", PageNo='" + PageNo + '\'' +
				", userNum=" + userNum +
				", khid='" + khid + '\'' +
				'}';
	}


	////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////==万==恶==的==分==割==线==///////////////////////////////////////////////////
// ///////////////////////////////////////////////////////////////////////////////////////////////

	//params
	private String T_0206;
	private String zifei;
	private String tuijian;

	private String sort;
	private String PageNo;

	private Integer userNum;

	public Integer getUserNum() {
		return userNum;
	}

	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}

	public String getPageNo() {
		return PageNo;
	}

	public void setPageNo(String pageNo) {
		PageNo = pageNo;
	}

	public String getT_0206() {
		return T_0206;
	}

	public void setT_0206(String t_0206) {
		T_0206 = t_0206;
	}

	public String getZifei() {
		return zifei;
	}

	public void setZifei(String zifei) {
		this.zifei = zifei;
	}

	public String getTuijian() {
		return tuijian;
	}

	public void setTuijian(String tuijian) {
		this.tuijian = tuijian;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	//商城管理员
	private String khid;

	public String getKhid() {
		return khid;
	}
	public void setKhid(String khid) {
		this.khid = khid;
	}




	private static final long serialVersionUID = 1L;

}
