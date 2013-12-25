package org.azt.mstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("auth")
public class LoginController extends ControllerBase {

    @RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
    	System.out.println("login");
		return getView("login");
	}
    
    @RequestMapping(value="/login-success", method=RequestMethod.GET)
	public @ResponseBody ModelMap loginSuccess() {   
    	System.out.println("login successful");
    	ModelMap model = new ModelMap();
    	model.put("error", ""); 
    	model.put("direct", "home");
    	
		return model;
	}
    
    @RequestMapping(value="/login-failure", method=RequestMethod.GET)
	public @ResponseBody ModelMap loginFailure() {    	
    	System.out.println("login failure");
    	ModelMap model = new ModelMap();
    	model.put("error", "用户名或密码错误!"); 
    	model.put("direct", getView("login"));
    	
		return model;
	}

	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout() {
		return getView("login");
	}
	
	@RequestMapping(value="/denied", method=RequestMethod.GET)
	public String denied() {
		return getView("access-denied");
	}
}
