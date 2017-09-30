package org.zhaobi.web.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="zbmap")
public class ZbMap {
	
	private int id;
	private String zvalue;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getZvalue() {
		return zvalue;
	}
	public void setZvalue(String zvalue) {
		this.zvalue = zvalue;
	}
	



	
	
	

	
	
}
