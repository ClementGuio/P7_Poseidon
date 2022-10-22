package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.sql.Timestamp;

@Entity
@Table(name = "RuleName")
public class RuleName {
	//TODO: verifier message annotations
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "Id")
	private Integer id;

	@NotBlank
	@Size(max=125, message="Name must not exceed 125 characters.")
	@Column(name = "name")
	private String name;

	@NotBlank
	@Size(max=125, message="Description must not exceed 125 characters.")
	@Column(name = "description")
	private String description;

	@Size(max=125, message="Json must not exceed 125 characters.")
	@Column(name = "json")
	private String json;

	@Size(max=512, message="Template must not exceed 512 characters.")
	@Column(name = "template")
	private String template;

	@Size(max=125, message="SqlStr must not exceed 125 characters.")
	@Column(name = "sqlStr")
	private String sqlStr;
	
	@Size(max=125, message="SqlPart must not exceed 125 characters.")
	@Column(name = "sqlPart")
	private String sqlPart;

	public RuleName() {
		super();
	}

	public RuleName(@NotBlank @Size(max = 125, message = "Name must not exceed 125 characters.") String name,
			@NotBlank @Size(max = 125, message = "Description must not exceed 125 characters.") String description,
			@Size(max = 125, message = "Json must not exceed 125 characters.") String json,
			@Size(max = 512, message = "Template must not exceed 512 characters.") String template,
			@Size(max = 125, message = "SqlStr must not exceed 125 characters.") String sqlStr,
			@Size(max = 125, message = "SqlPart must not exceed 125 characters.") String sqlPart) {
		super();
		this.name = name;
		this.description = description;
		this.json = json;
		this.template = template;
		this.sqlStr = sqlStr;
		this.sqlPart = sqlPart;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getSqlStr() {
		return sqlStr;
	}

	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}

	public String getSqlPart() {
		return sqlPart;
	}

	public void setSqlPart(String sqlPart) {
		this.sqlPart = sqlPart;
	}

}
