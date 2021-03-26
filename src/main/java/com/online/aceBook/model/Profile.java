package com.online.aceBook.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Profile extends AbstractPersistable<Long>  {
	
//	  @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false, updatable = false)
//    private Long id;
	
	@OneToOne(mappedBy = "accountProfile")
	private User user;
	
	
	@OneToMany(mappedBy = "profile")
	private List<Post> post = new ArrayList<>();


	public byte[] getProfileAvatar() {
		return profileAvatar;
	}

	public void setProfileAvatar(byte[] profileAvatar) {
		this.profileAvatar = profileAvatar;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name = "avatar")
    @Lob
    @Basic (fetch = FetchType.LAZY)
    private byte[] profileAvatar;
	
	private String avatarContentType;
	private Long avatarContentLength;

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return user.getFirstname() + " " + user.getLastname();
	}

	public String getAvatarContentType() {
		return avatarContentType;
	}

	public void setAvatarContentType(String avatarContentType) {
		this.avatarContentType = avatarContentType;
	}

	public Long getAvatarContentLength() {
		return avatarContentLength;
	}

	public void setAvatarContentLength(Long avatarContentLength) {
		this.avatarContentLength = avatarContentLength;
	}
	
	


	
	
	
}
