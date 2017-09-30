package org.zhaobi.web.service;

import java.math.BigInteger;
import java.util.List;

import org.zhaobi.web.entity.Question;

public interface QueService {
	public BigInteger countQue();
	public List<Question> getQuestion(int page);
	public List<Question> getPersonalList(int page, int userID);
	public void update(int id, String content, String a, String b, String c, int cid);
	public void deleteQues(int qid);
	public void addQues(Question ques);
	public void addQuesToList(int qid, int uid);
	public List<Question> search(int cid, int page);
}