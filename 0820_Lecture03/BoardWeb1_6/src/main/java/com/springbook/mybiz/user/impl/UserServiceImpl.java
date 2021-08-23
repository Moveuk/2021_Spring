package com.springbook.mybiz.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.mybiz.user.UserService;
import com.springbook.mybiz.user.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	@Override
	public void insertUser(UserVO uVo) {
		userDAO.insertUser(uVo);
	}

	@Override
	public UserVO getUser(UserVO uVo) {
		return userDAO.getUser(uVo);
		
	}

}
