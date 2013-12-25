package org.azt.mstore.controller.views;

import java.io.File;

import org.azt.mstore.controller.AdminController;
import org.azt.mstore.controller.LoginController;

public class ViewsWrapper<T> {

	public String getViewPath(String viewName, T t) {
		String folder = this.getFolder(t);
		
		return folder + File.separator + viewName;
	}
	
	private String getFolder(T t) {
		String folder = ViewsConstants.EMPTY;
		
		if (t instanceof LoginController) {
			folder = ViewsConstants.AUTH; 
		} else if (t instanceof AdminController) {
			folder = ViewsConstants.ADMIN;
		}
		
		return folder;
	}
}
