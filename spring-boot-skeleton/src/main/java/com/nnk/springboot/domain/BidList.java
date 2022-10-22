package com.nnk.springboot.domain;

import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.Digits;//TODO: BigDecimal
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "BidList")
public class BidList {

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "BidListId")
	private Integer bidListId;
	
	@NotBlank(message="Account is mandatory.")
	@Size(max=30, message="Account must not exceed 30 characters.")
	@Column(name = "account")
	private String account;
	
	@NotBlank(message="Type is mandatory.")
	@Size(max=30, message="Type must not exceed 30 characters.")
	@Column(name = "type")
	private String type;

	@NotNull(message = "BidQuantity is mandatory.")
	@Column(name = "bidQuantity")
	private Double bidQuantity;

	@Column(name = "askQuantity")
	private Double askQuantity;
	
	@Column(name = "bid")
	private Double bid;

	@Column(name = "ask")
	private Double ask;

	@Size(max=125, message="Benchmark must not exceed 125 characters.")
	@Column(name = "benchmark")
	private String benchmark;

	@Column(name = "bidListDate")
	private Timestamp bidListDate;

	@Size(max=125, message="Commentary must not exceed 125 characters.")
	@Column(name = "commentary")
	private String commentary;

	@Size(max=125, message="Security must not exceed 125 characters.")
	@Column(name = "security")
	private String security;

	@Size(max=10, message="Status must not exceed 10 characters.")
	@Column(name = "status")
	private String status;

	@Size(max=125, message="Trader must not exceed 125 characters.")
	@Column(name = "trader")
	private String trader;

	@Size(max=125, message="Book must not exceed 125 characters.")
	@Column(name = "book")
	private String book;

	@Size(max=125, message="CreationName must not exceed 125 characters.")
	@Column(name = "creationName")
	private String creationName;

	@Column(name = "creationDate")
	private Timestamp creationDate;

	@Size(max=125, message="RevisionName must not exceed 125 characters.")
	@Column(name = "revisionName")
	private String revisionName;

	@Column(name = "revisionDate")
	private Timestamp revisionDate;

	@Size(max=125, message="DealName must not exceed 125 characters.")
	@Column(name = "dealName")
	private String dealName;
	
	@Size(max=125, message="DealType must not exceed 125 characters.")
	@Column(name = "dealType")
	private String dealType;

	@Size(max=125, message="SourceListId must not exceed 125 characters.")
	@Column(name = "sourceListId")
	private String sourceListId;
	
	@Size(max=125, message="Side must not exceed 125 characters.")
	@Column(name = "side")
	private String side;

	public BidList() {
		super();
	}

	public BidList(
			@NotBlank(message = "Account is mandatory.") @Size(max = 30, message = "Account must not exceed 30 characters.") String account,
			@NotBlank(message = "Type is mandatory.") @Size(max = 30, message = "Type must not exceed 30 characters.") String type,
			@NotNull Double bidQuantity) {
		super();
		this.account = account;
		this.type = type;
		this.bidQuantity = bidQuantity;
	}
	
	public BidList(@NotBlank(message = "Account is mandatory.") @Size(max = 30, message = "Account must not exceed 30 characters.") String account,
			@NotBlank(message = "Type is mandatory.") @Size(max = 30, message = "Type must not exceed 30 characters.") String type,
			@NotNull Double bidQuantity, Double askQuantity, Double bid, Double ask,
			@Size(max = 125, message = "Benchmark must not exceed 125 characters.") String benchmark, Timestamp bidListDate,
			@Size(max = 125, message = "Commentary must not exceed 125 characters.") String commentary,
			@Size(max = 125, message = "Security must not exceed 125 characters.") String security,
			@Size(max = 10, message = "Status must not exceed 10 characters.") String status,
			@Size(max = 125, message = "Trader must not exceed 125 characters.") String trader,
			@Size(max = 125, message = "Book must not exceed 125 characters.") String book,
			@Size(max = 125, message = "CreationName must not exceed 125 characters.") String creationName,
			Timestamp creationDate,
			@Size(max = 125, message = "RevisionName must not exceed 125 characters.") String revisionName,
			Timestamp revisionDate, @Size(max = 125, message = "DealName must not exceed 125 characters.") String dealName,
			@Size(max = 125, message = "DealType must not exceed 125 characters.") String dealType,
			@Size(max = 125, message = "SourceListId must not exceed 125 characters.") String sourceListId,
			@Size(max = 125, message = "Side must not exceed 125 characters.") String side) {
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

	public Timestamp getBidListDate() {
		return bidListDate;
	}

	public void setBidListDate(Timestamp bidListDate) {
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

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public String getRevisionName() {
		return revisionName;
	}

	public void setRevisionName(String revisionName) {
		this.revisionName = revisionName;
	}

	public Timestamp getRevisionDate() {
		return revisionDate;
	}

	public void setRevisionDate(Timestamp revisionDate) {
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
