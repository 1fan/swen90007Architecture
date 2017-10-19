package org.zhaobi.web.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="list")
public class List {
    private int id;
    private int userID;
    private int questionID;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public int getQuestionID() {
        return questionID;
    }
    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

}
