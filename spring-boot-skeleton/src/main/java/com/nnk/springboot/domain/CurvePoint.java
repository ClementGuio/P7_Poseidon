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
}
