package org.zhaobi.web.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zhaobi.web.dao.UserDao;
import org.zhaobi.web.entity.Users;
import org.zhaobi.web.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired  
	@Qualifier("userDao")
	private UserDao userDao;
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public String getOne() {
	
		return this.userDao.getOne();
		
	}

	@Override
	public List<Users> getOneUser(String name, String password) {
		return this.userDao.getOneUser(name,password);
	}

	@Override
	public void addUser(Users user) {
		this.userDao.addUser(user);
	}
	
	@Override
	public void deleteUser(int id) {
		this.userDao.deleteUser(id);
	}
	
	@Override
	public BigInteger countUser(boolean authority) {
		return this.userDao.countUser(authority);
	}
	
	@Override
	public List<Users> getUser(){
		return this.userDao.getUser();
	}
	
	public List<Users> getUser(int page){
		return this.userDao.getUser(page);
	}
	
	public void update(int id, String n, String pd, String e, boolean au){
		this.userDao.update(id, n, pd, e, au);
	}
	
	public List<Users> search(String name){
		return this.userDao.search(name);
	}
	
	public List<Users> search(String name, int page){
		return this.userDao.search(name, page);
	}

}
