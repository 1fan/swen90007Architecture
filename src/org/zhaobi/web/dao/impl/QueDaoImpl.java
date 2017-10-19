package org.zhaobi.web.dao.impl;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		String sql = "from Question q where q.qid in (select questionID from List where userID="+userID+")";
		return this.sessionFactory.getCurrentSession().createQuery(sql).setMaxResults(5)
				.setFirstResult((page-1)*5).list();
	}
	
	public boolean update(int id, String content, String a, String b, String c, int cid, int version, String by) {
		String sql = "select version from Question q where q.qid=? LOCK IN SHARE MODE";
		int v = (int)this.sessionFactory.getCurrentSession().createSQLQuery(sql).setInteger(0, id).uniqueResult();
		
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm");
		Date dateobj = new Date();
		String modified_time = df.format(dateobj);
		
		if(v == version) {
			sql = "update question q set q.content=?,q.answera=?,q.answerb=?,q.correct=?,q.cid=?,q.version=q.version+1,modified_time=?,modified_by=? where q.qid=?";
			sessionFactory.getCurrentSession().createSQLQuery(sql).setString(0, content).setString(1, a).setString(2, b).setString(3, c).setInteger(4, cid).setString(5, modified_time).setString(6, by).setInteger(7, id).executeUpdate();
			return true;
		}else {
			return false;
		}
	}
	
	public String getModifyTime(int id) {
		String sql ="select modified_time from Question q where q.qid=?";
		return (String)this.sessionFactory.getCurrentSession().createSQLQuery(sql).setInteger(0, id).uniqueResult();
	}
	
	public String getModifyBy(int id) {
		String sql ="select modified_by from Question q where q.qid=?";
		return (String)this.sessionFactory.getCurrentSession().createSQLQuery(sql).setInteger(0, id).uniqueResult();
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
		String sql = "INSERT INTO list (userID, questionID) VALUE (?,?)";
		sessionFactory.getCurrentSession().createSQLQuery(sql).setInteger(0,uid).setInteger(1, qid).executeUpdate();
	}

	public List<Question> searchQuestionInList(int cid, int page, int userID){
		String sql = "from Question q where q.cid=? and q.qid in (select questionID from List where userID="+userID+")";
		return this.sessionFactory.getCurrentSession().createQuery(sql).setInteger(0, cid).setMaxResults(5)
				.setFirstResult((page-1)*5).list();
	}
}
