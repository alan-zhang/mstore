package org.azt.mstore.controller;

import org.azt.mstore.controller.views.ViewsWrapper;
import org.springframework.web.servlet.ModelAndView;


public class ControllerBase {
	protected ViewsWrapper<ControllerBase> viewsWrapper;
	
	public ControllerBase() {
		viewsWrapper = new ViewsWrapper<ControllerBase>();
	}
	
	protected String getView(String viewName) {
		return viewsWrapper.getViewPath(viewName, this);
	}
	
	protected ModelAndView getModelAndView(String viewName, String modelName, Object modelObject) {		
		return new ModelAndView(viewsWrapper.getViewPath(viewName, this), modelName, modelObject);
	}
}
