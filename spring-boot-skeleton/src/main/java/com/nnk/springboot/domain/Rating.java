package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.sql.Timestamp;

@Entity
@Table(name = "Rating")
public class Rating {

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "Id")
	private Integer id;

	@Size(max=125, message="MoodysRating must not exceed 125 characters.")
	@Column(name = "moodysRating")
	private String moodysRating;

	@Size(max=125, message="SandPRating must not exceed 125 characters.")
	@Column(name = "sandPRating")
	private String sandPRating;
	
	@Size(max=125, message="FitchRating must not exceed 125 characters.")
	@Column(name = "fitchRating")
	private String fitchRating;

	@NotNull
	@Column(name = "orderNumber")
	private Integer orderNumber;

	public Rating() {
		super();
	}
	
	public Rating(@Size(max = 125, message = "MoodysRating must not exceed 125 characters.") String moodysRating,
			@Size(max = 125, message = "SandPRating must not exceed 125 characters.") String sandPRating,
			@Size(max = 125, message = "FitchRating must not exceed 125 characters.") String fitchRating,
			@NotNull Integer orderNumber) {
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
