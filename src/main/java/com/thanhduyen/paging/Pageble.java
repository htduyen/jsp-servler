package com.thanhduyen.paging;

import com.thanhduyen.sort.Sorter;

public interface Pageble {

	Integer getPage();
	
	Integer getOffset();
	
	Integer getLimit();
	
	Sorter getSorter();
}
