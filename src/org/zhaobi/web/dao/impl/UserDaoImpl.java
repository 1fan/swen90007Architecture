package org.zhaobi.web.dao.impl;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.zhaobi.web.dao.UserDao;
import org.zhaobi.web.entity.Users;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	@Resource
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public String getOne() {
		String sql = "select realname from user where user_id=2";
		return (String)this.sessionFactory.getCurrentSession().createSQLQuery(sql).uniqueResult();
		
		//return "Hello bi~~";
	}
	
	@Override
	public List<Users> getOneUser(String name, String password){
		return this.sessionFactory.getCurrentSession().createQuery("from Users u where u.name=? and u.password=?").setString(0, name).setString(1,password).list();
	}
	
	
	@Override
	public void addUser(Users user) {
		sessionFactory.getCurrentSession().save(user);
	}
	
	@Override
	public void deleteUser(int id) {
		String sql = "delete from users where id="+id;
		sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}
	
	@Override
	public BigInteger countUser(boolean authority) {
		String sql = "select count(*) from users u where u.authority="+authority;
		return (BigInteger)this.sessionFactory.getCurrentSession().createSQLQuery(sql).uniqueResult();
	}
	
	public List<Users> getUser(){
		String sql = "from Users";
		return this.sessionFactory.getCurrentSession().createQuery(sql).list();
	}
	
	public List<Users> getUser(int page){
		String sql = "from Users";
		return this.sessionFactory.getCurrentSession().createQuery(sql).setMaxResults(5).setFirstResult((page-1)*5).list();
	}
	
	public void update(int id, String n, String pd, String e, boolean au) {
		String sql = "update users u set u.name=?,u.password=?,u.email=?,u.authority=? where u.id=?";
		sessionFactory.getCurrentSession().createSQLQuery(sql).setString(0, n).setString(1, pd).setString(2, e).setBoolean(3, au).setInteger(4, id).executeUpdate();
	}
	
	public List<Users> search(String name){
		
		return this.sessionFactory.getCurrentSession().createQuery("from Users u where u.name=?").setString(0, name).list();
	}
	
	public List<Users> search(String name, int page){
		
		return this.sessionFactory.getCurrentSession().createQuery("from Users u where u.name=?").setString(0, name).setMaxResults(5)
				.setFirstResult((page-1)*5).list();
	}

}
