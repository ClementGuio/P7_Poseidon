package com.nnk.springboot.domain;

import org.springframework.beans.factory.annotation.Required;//TODO: quelle intérêt??

import javax.persistence.*;
import javax.validation.constraints.Digits;//TODO: BigDecimal
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

	public BidList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BidList(@NotBlank @Size(max = 30) String account, @NotBlank @Size(max = 30) String type,
			Double bidQuantity) {
		super();
		this.account = account;
		this.type = type;
		this.bidQuantity = bidQuantity;
	}

	public BidList(@NotBlank @Size(max = 30) String account, @NotBlank @Size(max = 30) String type,
			Double bidQuantity, Double askQuantity, Double bid, Double ask, @Size(max = 125) String benchmark,
			LocalDateTime bidListDate, @Size(max = 125) String commentary, @Size(max = 125) String security,
			@Size(max = 10) String status, @Size(max = 125) String trader, @Size(max = 125) String book,
			@Size(max = 125) String creationName, LocalDateTime creationDate, @Size(max = 125) String revisionName,
			LocalDateTime revisionDate, @Size(max = 125) String dealName, @Size(max = 125) String dealType,
			@Size(max = 125) String sourceListId, @Size(max = 125) String side) {
		super();
		this.account = account;
		this.type = type;
		this.bidQuantity = bidQuantity;
		this.askQuantity = askQuantity;
		this.bid = bid;
		this.ask = ask;
		this.benchmark = benchmark;
		this.bidListDate = bidListDate;
		this.commentary = commentary;
		this.security = security;
		this.status = status;
		this.trader = trader;
		this.book = book;
		this.creationName = creationName;
		this.creationDate = creationDate;
		this.revisionName = revisionName;
		this.revisionDate = revisionDate;
		this.dealName = dealName;
		this.dealType = dealType;
		this.sourceListId = sourceListId;
		this.side = side;
	}

	public Integer getBidListId() {
		return bidListId;
	}

	public void setBidListId(Integer bidListId) {
		this.bidListId = bidListId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getBidQuantity() {
		return bidQuantity;
	}

	public void setBidQuantity(Double bidQuantity) {
		this.bidQuantity = bidQuantity;
	}

	public Double getAskQuantity() {
		return askQuantity;
	}

	public void setAskQuantity(Double askQuantity) {
		this.askQuantity = askQuantity;
	}

	public Double getBid() {
		return bid;
	}

	public void setBid(Double bid) {
		this.bid = bid;
	}

	public Double getAsk() {
		return ask;
	}

	public void setAsk(Double ask) {
		this.ask = ask;
	}

	public String getBenchmark() {
		return benchmark;
	}

	public void setBenchmark(String benchmark) {
		this.benchmark = benchmark;
	}

	public LocalDateTime getBidListDate() {
		return bidListDate;
	}

	public void setBidListDate(LocalDateTime bidListDate) {
		this.bidListDate = bidListDate;
	}

	public String getCommentary() {
		return commentary;
	}

	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}

	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTrader() {
		return trader;
	}

	public void setTrader(String trader) {
		this.trader = trader;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getCreationName() {
		return creationName;
	}

	public void setCreationName(String creationName) {
		this.creationName = creationName;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public String getRevisionName() {
		return revisionName;
	}

	public void setRevisionName(String revisionName) {
		this.revisionName = revisionName;
	}

	public LocalDateTime getRevisionDate() {
		return revisionDate;
	}

	public void setRevisionDate(LocalDateTime revisionDate) {
		this.revisionDate = revisionDate;
	}

	public String getDealName() {
		return dealName;
	}

	public void setDealName(String dealName) {
		this.dealName = dealName;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public String getSourceListId() {
		return sourceListId;
	}

	public void setSourceListId(String sourceListId) {
		this.sourceListId = sourceListId;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}
	
}
