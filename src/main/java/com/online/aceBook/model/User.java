package com.online.aceBook.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class User extends AbstractPersistable<Long>  {

 /*   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
*/

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String passwordHash;
    
    @Column (name = "firstname", nullable = false)
    private String firstname;
    
    @Column (name = "lastname", nullable = false)
    private String lastname;
    
    @Column(name = "email", nullable = false)
    private String email;   
    
    @Column(name = "role", nullable = false)
    private String role;
    
 /*   @Column(name = "avatar")
    @Lob
    @Basic (fetch = FetchType.LAZY)
    private byte[] profileAvatar;
    
    */
    
    @OneToOne
    private Profile accountProfile;


	public User() {
    }

	public User(String username, String passwordHash, String firstname, String lastname, String email, String role, Profile accountprofile) {
		super();
		this.username = username;
		this.email = email;
		this.passwordHash = passwordHash;
		this.role = role;
		this.accountProfile = accountprofile;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	/*
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	*/

	public String getUsername() {
		return username;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}	


	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}

