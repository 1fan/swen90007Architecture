package org.zhaobi.web.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="question")
public class Question {
		
	private int qid;
	private String content;
	private String answera;
	private String answerb;
	private String correct;
	private int cid; 
	
	@Id
	@GeneratedValue
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAnswera() {
		return answera;
	}
	public void setAnswera(String answera) {
		this.answera = answera;
	}
	public String getAnswerb() {
		return answerb;
	}
	public void setAnswerb(String answerb) {
		this.answerb = answerb;
	}
	public String getCorrect() {
		return correct;
	}
	public void setCorrect(String correct) {
		this.correct = correct;
	}
	public int getCid() {
		return this.cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	
	
}