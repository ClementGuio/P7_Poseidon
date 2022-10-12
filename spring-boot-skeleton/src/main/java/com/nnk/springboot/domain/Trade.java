package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
@Table(name = "trade")
public class Trade {
	
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

	public Trade() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Trade(@NotBlank @Size(max = 30) String account, @NotBlank @Size(max = 30) String type) {
		super();
		this.account = account;
		this.type = type;
	}

	public Trade(@NotBlank @Size(max = 30) String account, @NotBlank @Size(max = 30) String type, Double buyQuantity,
			Double sellQuantity, Double buyPrice, Double sellPrice, LocalDateTime tradeDate,
			@Size(max = 125) String security, @Size(max = 10) String status, @Size(max = 125) String trader,
			@Size(max = 125) String benchmark, @Size(max = 125) String book, @Size(max = 125) String creationName,
			LocalDateTime creationDate, @Size(max = 125) String revisionName, LocalDateTime revisionDate,
			@Size(max = 125) String dealName, @Size(max = 125) String dealType, @Size(max = 125) String sourceListId,
			@Size(max = 125) String side) {
		super();
		this.account = account;
		this.type = type;
		this.buyQuantity = buyQuantity;
		this.sellQuantity = sellQuantity;
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
		this.tradeDate = tradeDate;
		this.security = security;
		this.status = status;
		this.trader = trader;
		this.benchmark = benchmark;
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

	public Integer getTradeId() {
		return tradeId;
	}

	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
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

	public Double getBuyQuantity() {
		return buyQuantity;
	}

	public void setBuyQuantity(Double buyQuantity) {
		this.buyQuantity = buyQuantity;
	}

	public Double getSellQuantity() {
		return sellQuantity;
	}

	public void setSellQuantity(Double sellQuantity) {
		this.sellQuantity = sellQuantity;
	}

	public Double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public Double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public LocalDateTime getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(LocalDateTime tradeDate) {
		this.tradeDate = tradeDate;
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

	public String getBenchmark() {
		return benchmark;
	}

	public void setBenchmark(String benchmark) {
		this.benchmark = benchmark;
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
