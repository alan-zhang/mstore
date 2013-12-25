package org.azt.mstore.interceptor;

import java.util.List;

public class RequestExcludingHandler {

	private List<String> excludedUrls;

	public void setExcludedUrls(List<String> excludedUrls) {
		this.excludedUrls = excludedUrls;
	}
	
	public boolean isExcluding(String requestUrl) {
		
		for (String url : this.excludedUrls) {
			if (requestUrl.endsWith(url)) {
				return true;
			}
		}
		
		return false;
	}
}
