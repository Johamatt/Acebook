package com.online.aceBook.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Profile extends AbstractPersistable<Long>  {
	
	@OneToOne(mappedBy = "accountProfile")
	private User user;
	
	@OneToMany(mappedBy = "profile")
	private List<Post> post = new ArrayList<>();
	
	@Column(name = "avatar")
    @Lob
    @Basic (fetch = FetchType.LAZY)
    private byte[] profileAvatar;	
	private String avatarContentType;
	private Long avatarContentLength;
	
	@Override
	public String toString() {
		return user.getFirstname() + " " + user.getLastname();
	}
}
