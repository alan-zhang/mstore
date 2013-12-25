package org.azt.mstore.webservice;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.azt.mstore.dao.RoleDao;
import org.azt.mstore.model.Role;
import org.azt.mstore.utility.JSONUtil;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("service/roles")
public class RoleService {

	@Autowired
	private RoleDao roleDao;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	@ResponseBody
	public List<Role> getRoles() {
		return this.roleDao.getAllRoles();
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	@ResponseBody
	public boolean addRole(HttpServletRequest request) {
		Role role;
		try {
			role = JSONUtil.readToObject(request.getInputStream(), Role.class);
			int result = this.roleDao.addRole(role);
			return result > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	@RequestMapping(value="/user-role", method=RequestMethod.GET)
	@ResponseBody
	public List<Role> getRolesByUserId(@RequestParam("userId") int userId) {
		return this.roleDao.getAllRolesByUserId(userId);
	}
		
	@RequestMapping(value="/user-role/{userId}", method=RequestMethod.POST)
	@ResponseBody
	public boolean saveRolesForUser(@PathVariable("userId") int userId, HttpServletRequest request) {
		try {
			List<Role> roles = JSONUtil.readToList(request.getInputStream(), Role.class);
			
			int del = this.roleDao.deleteUserRole(userId);
			
			if (del >= 0) {
				for (Role role : roles) {
					this.roleDao.addUserRole(userId, role.getId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
		
		return true;
	}
}
