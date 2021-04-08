package com.online.aceBook.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends AbstractPersistable<Long>  {

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
    
    @OneToOne
    private Profile accountProfile;

	public String getFullname() {
		return firstname + " " + lastname;
	}

	@Override
	public String toString() {
		return  firstname + "." + lastname + "." + accountProfile.getId();
	}
}

