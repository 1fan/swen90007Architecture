package org.zhaobi.web.dao;

import java.math.BigInteger;
import java.util.List;

import org.zhaobi.web.entity.Users;

public interface UserDao {
	public String getOne();
	public List<Users> getOneUser(String name, String password);
	public void addUser(Users user);
	public void deleteUser(int id);
	public BigInteger countUser(boolean authority);
	public List<Users> getUser();
	public List<Users> getUser(int page);
	public void update(int id, String name, String password, String email, boolean authority);
	public List<Users> search(String name);
	public List<Users> search(String name, int page);
}
