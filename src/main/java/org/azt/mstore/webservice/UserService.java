package org.azt.mstore.webservice;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.azt.mstore.dao.UserDao;
import org.azt.mstore.model.Status;
import org.azt.mstore.model.User;
import org.azt.mstore.model.UserInfo;
import org.azt.mstore.utility.JSONUtil;
import org.azt.mstore.utility.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("service/users")
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	@ResponseBody
	public List<UserInfo> getAllUsers() {
		List<UserInfo> infos = this.userDao.getAllUserInfos();
		return infos;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	@ResponseBody
	public Status updateUser(@PathVariable("id") int id, HttpServletRequest request) {
		Status status = new Status();	
		
		try {
			UserInfo userInfo = this.deserialize(request);
			System.out.println(userInfo.getUserName());
			
			this.userDao.updteUserInfo(userInfo);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	@ResponseBody
	public Status addUser(HttpServletRequest request) {
		Status status = new Status();		
		try {
			UserInfo userInfo = this.deserialize(request);
			System.out.println(userInfo.getUserName());
			
			UserInfo dbInfo = this.userDao.getUserInfoByUserName(userInfo.getUserName());
			
			if (dbInfo == null) {
				this.userDao.addUserInfo(userInfo);
				dbInfo = this.userDao.getUserInfoByUserName(userInfo.getUserName());
				this.addUserAndPassword(dbInfo);
				status.setMessage(dbInfo.getId().toString());
			} else {
				status.setSuccess(false);
				status.setMessage("此用户已存在！");
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	private void addUserAndPassword(UserInfo userInfo) throws Exception {
		try {
			User user = new User();
			user.setUserId(userInfo.getId());
			user.setUserName(userInfo.getUserName());
			user.setPassword(MD5Util.encrypt("1"));
			this.userDao.addUserAndPassword(user);
		} catch (Exception e) {
			throw e;
		}		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public boolean deleteUser(@PathVariable("id") int id, HttpServletRequest request) {
		int result = this.userDao.deleteUserInfo(id);
		result = this.userDao.deleteUserAndPassword(id);
		return result != 0;
	}
	
	private UserInfo deserialize(HttpServletRequest request) throws IOException {
		InputStream inputStream = request.getInputStream();
		UserInfo userInfo = JSONUtil.readToObject(inputStream, UserInfo.class);
		return userInfo;
	}
}
