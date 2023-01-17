package com.nadeem.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jcyear")
public class JCyear {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "jcdates")
	private Date jcdates;

	public JCyear() {

	}

	public JCyear(int id, Date jcdates) {
		this.id = id;
		this.jcdates = jcdates;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getJcdates() {
		return jcdates;
	}

	public void setJcdates(Date jcdates) {
		this.jcdates = jcdates;
	}

}
