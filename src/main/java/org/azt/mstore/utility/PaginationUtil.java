package org.azt.mstore.utility;

import java.util.ArrayList;
import java.util.List;

public class PaginationUtil {

	@SuppressWarnings("rawtypes")
	public static List getPaginations(int page, int pageCount, List list) {
		List pageList = null;

		int begin = page == 1 ? 0 : (page - 1) * pageCount;
		int end = page * pageCount;

		if (list != null && list.size() > 0) {
			Integer dataSize = list.size();
			if (end <= dataSize) {
				pageList = list.subList(begin, end);
			} else {
				pageList = list.subList(begin, list.size());
			}
		}

		return pageList == null ? new ArrayList() : pageList;
	}

	public static int[] getPages(int total, int pageCount) {
		int c = total / pageCount;
		int mod = total % pageCount;
		int length = mod == 0 ? c : c + 1;

		int[] pages = new int[length];

		for (int i = 1; i <= length; i++) {
			pages[i - 1] = i;
		}

		return pages;
	}
}
