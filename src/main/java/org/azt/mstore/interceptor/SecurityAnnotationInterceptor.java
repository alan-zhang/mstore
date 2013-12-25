package org.azt.mstore.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public class SecurityAnnotationInterceptor extends ResourceExcludingHandlerInterceptor {

	private RequestExcludingHandler requestExcludingHandler;
	
	public void setRequestExcludingHandler(RequestExcludingHandler requestExcludingHandler) {
		this.requestExcludingHandler = requestExcludingHandler;
	}

	
	@Override
	public boolean doPreHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception {
		String requestUri = request.getRequestURI();

		if (this.requestExcludingHandler.isExcluding(requestUri)) {
			return true;
		}
		
//		if (handler instanceof HandlerMethod) {
//			HandlerMethod hm = (HandlerMethod) handler;
//			Login login = hm.getMethodAnnotation(Login.class);
//			
//			if (login != null) {
//				HttpSession session = request.getSession();
//				
//				if (session.getAttribute("user") == null) {
//					throw new AuthorizationException();
//				} else {
//					return true;
//				}
//			}
//		}
		
		return true;
	}

	@Override
	public void doAfterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void doPostHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
	}

}
