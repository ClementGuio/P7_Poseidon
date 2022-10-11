package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.sql.Timestamp;

@Entity
@Table(name = "rulename")
public class RuleName {
    // TODO: verify validation annotations
	
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "Id")
	Integer id;

	@Size(max=125)
	@Column(name = "name")
	String name;

	@Size(max=125)
	@Column(name = "description")
	String description;

	@Size(max=125)
	@Column(name = "json")
	String json;

	@Size(max=512)
	@Column(name = "template")
	String template;

	@Size(max=125)
	@Column(name = "sqlStr")
	String sqlStr;
	
	@Size(max=125)
	@Column(name = "sqlPart")
	String sqlPart;

}
