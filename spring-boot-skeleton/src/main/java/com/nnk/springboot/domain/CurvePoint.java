package com.nnk.springboot.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    // TODO: Verify validations annotations
	
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "Id")
	Integer id;
	
	@Column(name = "CurveId")
	Integer curveId;
	
	//TODO: verify that LocalDateTime match with usage
	@Column(name = "asOfDate")
	LocalDateTime asOfDate;
	
	@Column(name = "term")
	Double term;

	@Column(name = "value")
	Double value;

	//TODO: verify that LocalDateTime match with usage
	@Column(name = "creationDate")
	LocalDateTime creationDate;

	public CurvePoint() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CurvePoint(Integer curveId, Double term, Double value) {
		super();
		this.curveId = curveId;
		this.term = term;
		this.value = value;
	}

	public CurvePoint(Integer curveId, LocalDateTime asOfDate, Double term, Double value,
			LocalDateTime creationDate) {
		super();
		this.curveId = curveId;
		this.asOfDate = asOfDate;
		this.term = term;
		this.value = value;
		this.creationDate = creationDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCurveId() {
		return curveId;
	}

	public void setCurveId(Integer curveId) {
		this.curveId = curveId;
	}

	public LocalDateTime getAsOfDate() {
		return asOfDate;
	}

	public void setAsOfDate(LocalDateTime asOfDate) {
		this.asOfDate = asOfDate;
	}

	public Double getTerm() {
		return term;
	}

	public void setTerm(Double term) {
		this.term = term;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
}
