package com.nnk.springboot.domain;

import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "bidlist")
public class BidList {
    //TODO: add validations annotations
	//TODO: see validations for sql types
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "BidListId")
	Integer bidListId;
	
	@NotBlank
	@Size(max=30)
	@Column(name = "account")
	String account;
	
	@NotBlank
	@Size(max=30)
	@Column(name = "type")
	String type;

	@Column(name = "bidQuantity")
	Double bidQuantity;

	@Column(name = "askQuantity")
	Double askQuantity;
	
	@Column(name = "bid")
	Double bid;

	@Column(name = "ask")
	Double ask;

	@Size(max=125)
	@Column(name = "benchmark")
	String benchmark;

	//TODO: verify that LocalDateTime match with usage
	@Column(name = "bidListDate")
	LocalDateTime bidListDate;

	@Size(max=125)
	@Column(name = "commentary")
	String commentary;

	@Size(max=125)
	@Column(name = "security")
	String security;

	@Size(max=10)
	@Column(name = "status")
	String status;

	@Size(max=125)
	@Column(name = "trader")
	String trader;

	@Size(max=125)
	@Column(name = "book")
	String book;

	@Size(max=125)
	@Column(name = "creationName")
	String creationName;

	//TODO: verify that LocalDateTime match with usage
	@Column(name = "creationDate")
	LocalDateTime creationDate;

	@Size(max=125)
	@Column(name = "revisionName")
	String revisionName;

	//TODO: verify that LocalDateTime match with usage
	@Column(name = "revisionDate")
	LocalDateTime revisionDate;

	@Size(max=125)
	@Column(name = "dealName")
	String dealName;
	
	@Size(max=125)
	@Column(name = "dealType")
	String dealType;

	@Size(max=125)
	@Column(name = "sourceListId")
	String sourceListId;
	
	@Size(max=125)
	@Column(name = "side")
	String side;
	
}
