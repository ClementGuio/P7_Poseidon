package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
@Table(name = "trade")
public class Trade {
    // TODO: Map columns in data table TRADE with corresponding java fields
	
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "TradeId")
	Integer tradeId;

	@NotBlank
	@Size(max=30)
	@Column(name = "account")
	String account;

	@NotBlank
	@Size(max=30)
	@Column(name = "type")
	String type;
	
	@Column(name = "buyQuantity")
	Double buyQuantity;
	
	@Column(name = "sellQuantity")
	Double sellQuantity;

	@Column(name = "buyPrice")
	Double buyPrice;

	@Column(name = "sellPrice")
	Double sellPrice;

	//TODO: verify that LocalDateTime match with usage
	@Column(name = "tradeDate")
	LocalDateTime tradeDate;

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
	@Column(name = "benchmark")
	String benchmark;

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
