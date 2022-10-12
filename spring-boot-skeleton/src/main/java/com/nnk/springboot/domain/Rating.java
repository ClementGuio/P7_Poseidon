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

	public Rating() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rating(@Size(max = 125) String moodysRating, @Size(max = 125) String sandPRating,
			@Size(max = 125) String fitchRating, Integer orderNumber) {
		super();
		this.moodysRating = moodysRating;
		this.sandPRating = sandPRating;
		this.fitchRating = fitchRating;
		this.orderNumber = orderNumber;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMoodysRating() {
		return moodysRating;
	}

	public void setMoodysRating(String moodysRating) {
		this.moodysRating = moodysRating;
	}

	public String getSandPRating() {
		return sandPRating;
	}

	public void setSandPRating(String sandPRating) {
		this.sandPRating = sandPRating;
	}

	public String getFitchRating() {
		return fitchRating;
	}

	public void setFitchRating(String fitchRating) {
		this.fitchRating = fitchRating;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

}
