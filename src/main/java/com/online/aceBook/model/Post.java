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
//	@ManyToOne
//	@JoinColumn(name = "userid")
//	private User user;

	
	@ManyToOne
    private Profile profile;
	private String message;
	private Date postDate;

	public Post() {}
	
	public Post(Profile profile, String message, Date postDate) {
		super();
		this.profile = profile;
		this.message = message;
		this.postDate = postDate;
	}
	


	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
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