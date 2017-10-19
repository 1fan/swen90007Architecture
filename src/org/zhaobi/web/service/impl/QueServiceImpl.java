package org.zhaobi.web.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zhaobi.web.dao.QuestionDao;
import org.zhaobi.web.entity.Question;
import org.zhaobi.web.entity.Users;
import org.zhaobi.web.service.QueService;

@Service("queService")
public class QueServiceImpl implements QueService{

	@Autowired  
	@Qualifier("quetionDao")
	private QuestionDao queDao;
	
	@Override
	public BigInteger countQue() {
		return this.queDao.countQue();
	}
	
	public List<Question> getQuestion(int page){
		return this.queDao.getQuestion(page);
	}
	
	public List<Question> getPersonalList(int page, int userID){
		return this.queDao.getPersonalList(page,userID);
	}
	
	public boolean update(int id, String content, String a, String b, String c, int cid, int v, String by) {
		if(this.queDao.update(id, content, a, b, c, cid, v, by)) {
			return true;
		}else{
			return false;
		}
	}
	
	public void deleteQues(int qid) {
		this.queDao.deleteQues(qid);
	}
	
	public void addQues(Question ques) {
		this.queDao.addQues(ques);
	}
	
	public List<Question> search(int cid, int page){
		return this.queDao.search(cid, page);
	}
	
	public List<Question> searchQuestionInList(int cid, int page, int userID){
		return this.queDao.searchQuestionInList(cid,page,userID);
	}

	public void addQuesToList(int qid, int uid){
		this.queDao.addQuesToList(qid,uid);
	}
	
	public String getModifyTime(int id) {
		return this.queDao.getModifyTime(id);
	}
	
	public String getModifyBy(int id) {
		return this.queDao.getModifyBy(id);
	}
}
