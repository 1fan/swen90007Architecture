package org.zhaobi.web.dao;

import java.math.BigInteger;
import java.util.List;

import org.zhaobi.web.entity.Question;
import org.zhaobi.web.entity.Users;

public interface QuestionDao {
	
	public BigInteger countQue();
	public List<Question> getQuestion(int page);
	public List<Question> getPersonalList(int page, int userID);
	public boolean update(int id, String content, String a, String b, String c, int cid, int v, String by);
	public void deleteQues(int qid);
	public void addQues(Question ques);
	public List<Question> search(int cid, int page);
	public List<Question> searchQuestionInList(int cid, int page, int userID);
	public void addQuesToList(int qid, int uid);
	public String getModifyTime(int id);
	public String getModifyBy(int id);

}
