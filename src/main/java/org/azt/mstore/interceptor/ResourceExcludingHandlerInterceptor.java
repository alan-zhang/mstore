package org.azt.mstore.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

public abstract class ResourceExcludingHandlerInterceptor implements HandlerInterceptor {

	public abstract boolean doPreHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;

	public abstract void doPostHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception;

	public abstract void doAfterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception;  
    
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return isResourceHandler(handler) ? true : doPreHandle(request, response, handler);
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, 
			ModelAndView modelAndView) throws Exception {
		
		if (!isResourceHandler(handler)) {
			doPostHandle(request, response, handler, modelAndView);
		}
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, 
			Exception ex) throws Exception {
		
		if (!isResourceHandler(handler)) {
			doAfterCompletion(request, response, handler, ex);
		}
	}

	private boolean isResourceHandler(Object handler) {
		return handler instanceof ResourceHttpRequestHandler;
	}
}
