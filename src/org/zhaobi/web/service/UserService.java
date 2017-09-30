package org.zhaobi.web.service;

import java.math.BigInteger;
import java.util.List;

import org.zhaobi.web.entity.Users;

public interface UserService {
	
	public String getOne();
	public List<Users> getOneUser(String name, String password);
	public void addUser(Users user);
	public void deleteUser(int id);
	public BigInteger countUser(boolean athority);
	public List<Users> getUser();
	public List<Users> getUser(int page);
	public void update(int id, String n, String pd, String e, boolean au);
	public List<Users> search(String name);
	public List<Users> search(String name, int page);
}
