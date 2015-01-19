package com.example.myspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myspring.dao.LoginLogDao;
import com.example.myspring.dao.UserDao;
import com.example.myspring.domain.LoginLog;
import com.example.myspring.domain.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private LoginLogDao loginLogDao;
	
	public boolean hasMatchUser(String userName, String password){
		return userDao.getMatchCount(userName, password)>0;
	}
	
	public User findUserbyName(String userName){
		return userDao.findUserByName(userName);
	}
	
	public void loginSuccess(User user){
		user.setCredits(5+ user.getCredits());
		LoginLog loginLog = new LoginLog();
		loginLog.setIp(user.getLastIp());
		loginLog.setUserId(user.getUserId());
		loginLog.setLoginDate(user.getLastVisit());
		userDao.updateLoginInfo(user);
		loginLogDao.insertLoginLog(loginLog);
	}
}
