package com.springbook.biz.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;

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
