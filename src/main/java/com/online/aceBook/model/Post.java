package com.online.aceBook.model;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

	
    @JsonIgnore
	@ManyToOne
	private Profile profile;
	private String message;
	
	@DateTimeFormat(pattern = "dd-M-yyyy HH:mm:ss")
	private Date postDate;


	@JsonIgnoreProperties("accountProfile")
	@ManyToOne
	private User sentfrom;

	public String getPostDateStr() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		return formatter.format(postDate);
	}
}