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

	public RuleName() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RuleName(@Size(max = 125) String name, @Size(max = 125) String description, @Size(max = 125) String json,
			@Size(max = 512) String template, @Size(max = 125) String sqlStr, @Size(max = 125) String sqlPart) {
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
