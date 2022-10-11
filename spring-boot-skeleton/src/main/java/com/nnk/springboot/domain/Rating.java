package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.sql.Timestamp;

@Entity
@Table(name = "rating")
public class Rating {
    // TODO: verify validation annotations
	
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "Id")
	Integer id;

	@Size(max=125)
	@Column(name = "moodysRating")
	String moodysRating;

	@Size(max=125)
	@Column(name = "sandPRating")
	String sandPRating;
	
	@Size(max=125)
	@Column(name = "fitchRating")
	String fitchRating;

	@Column(name = "orderNumber")
	Integer orderNumber;

}
