package com.online.aceBook.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post extends AbstractPersistable<Long> {

	@ManyToOne
    private Profile profile;
	private String message;
	private Date postDate;
}