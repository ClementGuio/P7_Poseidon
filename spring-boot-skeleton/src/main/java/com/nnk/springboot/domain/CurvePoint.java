package com.nnk.springboot.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "CurvePoint")
public class CurvePoint {
	
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "Id")
	private Integer id;
	
	@NotNull(message="CurveId is mandatory.")
	@Column(name = "CurveId")
	private Integer curveId;
	
	@Column(name = "asOfDate")
	private Timestamp asOfDate;

	@NotNull(message="Term is mandatory.")
	@Column(name = "term")
	private Double term;

	@NotNull(message="Value is mandatory.")
	@Column(name = "value")
	private Double value;

	@Column(name = "creationDate")
	private Timestamp creationDate;

	public CurvePoint() {
		super();
	}
	
	public CurvePoint(@NotNull(message = "CurveId is mandatory.") Integer curveId,
			@NotNull(message = "Term is mandatory.") Double term,
			@NotNull(message = "Value is mandatory.") Double value) {
		super();
		this.curveId = curveId;
		this.term = term;
		this.value = value;
	}
	
	public CurvePoint(@NotNull(message = "CurveId is mandatory.") Integer curveId, Timestamp asOfDate,
			@NotNull(message = "Term is mandatory.") Double term,
			@NotNull(message = "Value is mandatory.") Double value, Timestamp creationDate) {
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

	public Timestamp getAsOfDate() {
		return asOfDate;
	}

	public void setAsOfDate(Timestamp asOfDate) {
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

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

}
