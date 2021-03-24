package com.online.aceBook.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
public class Post extends AbstractPersistable<Long> {
	
	/*
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;
	*/
	
//	@JsonIgnoreProperties ({"users"}) 
	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;
	private String message;
	private Date postDate;
	

	public Post() {}
	
	public Post(User user, String message, Date postDate) {
		super();
		this.user = user;
		this.message = message;
		this.postDate = postDate;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
}