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
	private int version;
	private String modified_time;
	private String modified_by;
	
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
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getModified_time() {
		return modified_time;
	}
	public void setModified_time(String modified_time) {
		this.modified_time = modified_time;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	
}