package org.zhaobi.web.dao.impl;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.zhaobi.web.dao.QuestionDao;
import org.zhaobi.web.entity.Question;

@Repository("quetionDao")
public class QueDaoImpl implements QuestionDao{
	@Resource
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public BigInteger countQue() {
		String sql = "select count(*) from question q";
		return (BigInteger)this.sessionFactory.getCurrentSession().createSQLQuery(sql).uniqueResult();
	}
	
	public List<Question> getQuestion(int page){
		String sql = "from Question";
		return this.sessionFactory.getCurrentSession().createQuery(sql).setMaxResults(5).setFirstResult((page-1)*5).list();
	}

	public List<Question> getPersonalList(int page, int userID){
//		String sql = "from Question q where q.qid in (select questionID from List where userID="+userID+")";
		String sql = "from Question q where q.qid in (select questionID from List where userID="+userID+")";
		return this.sessionFactory.getCurrentSession().createQuery(sql).setMaxResults(5)
				.setFirstResult((page-1)*5).list();
	}

	public void update(int id,String content, String a, String b, String c, int cid) {
		String sql = "update question q set q.content=?,q.answera=?,q.answerb=?,q.correct=?,q.cid=? where q.qid=?";
		sessionFactory.getCurrentSession().createSQLQuery(sql).setString(0, content).setString(1, a).setString(2, b).setString(3, c).setInteger(4, cid).setInteger(5, id).executeUpdate();
	}
	
	public void deleteQues(int qid) {
		String sql = "delete from question where qid="+qid;
		sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}
	
	public void addQues(Question ques) {
		sessionFactory.getCurrentSession().save(ques);
	}
	
	public List<Question> search(int cid, int page){
		return this.sessionFactory.getCurrentSession().createQuery("from Question q where q.cid=?").setInteger(0, cid).setMaxResults(5)
				.setFirstResult((page-1)*5).list();
	}

	public void addQuesToList(int qid, int uid){
//		String sql = "INSERT into question q set q.content=?,q.answera=?,q.answerb=?,q.correct=?,q.cid=? where q.qid=?";
//		sessionFactory.getCurrentSession().createSQLQuery(sql).setString(0, content).setString(1, a).setString(2, b).setString(3, c).setInteger(4, cid).setInteger(5, id).executeUpdate();
		String sql = "INSERT INTO list (userID, questionID) VALUE (?,?)";
		sessionFactory.getCurrentSession().createSQLQuery(sql).setInteger(0,uid).setInteger(1, qid).executeUpdate();
	}
}
