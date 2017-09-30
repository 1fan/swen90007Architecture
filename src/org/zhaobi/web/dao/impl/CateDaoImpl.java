package org.zhaobi.web.dao.impl;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.zhaobi.web.dao.CategoryDao;
import org.zhaobi.web.entity.Category;
import org.zhaobi.web.entity.Question;
import org.zhaobi.web.entity.Users;

@Repository("categoryDao")
public class CateDaoImpl implements CategoryDao{
	
	@Resource
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public BigInteger countCate() {
		String sql = "select count(*) from category";
		return (BigInteger)this.sessionFactory.getCurrentSession().createSQLQuery(sql).uniqueResult();
	}
	
	public List<Category> getCategory(){
		String sql = "from Category";
		return this.sessionFactory.getCurrentSession().createQuery(sql).list();
	}

	public void update(int cid, String name) {
		String sql = "update category c set c.cate=? where c.cid=?";
		sessionFactory.getCurrentSession().createSQLQuery(sql).setString(0, name).setInteger(1, cid).executeUpdate();
	}
	
	public List<Category> search(int cid){
		return this.sessionFactory.getCurrentSession().createQuery("from Category c where c.cid=?").setInteger(0, cid).list();
	}
	
	public List<Category> showCategory(int page){
		String sql = "select C.* from category C,question Q where Q.cid=C.cid order by Q.qid";
		return sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Category.class).setMaxResults(5)
				.setFirstResult((page-1)*5).list();
	}
	
	public List<Category> showSearch(int cid, int page){
		String sql = "select C.* from category C,question Q where Q.cid=C.cid and C.cid=?";
		return sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Category.class).setInteger(0, cid).setMaxResults(5)
				.setFirstResult((page-1)*5).list();
	}
	
}
