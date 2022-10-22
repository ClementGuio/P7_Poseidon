package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "Trade")
public class Trade {
	
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "TradeId")
	private Integer tradeId;

	@NotBlank(message="Account is mandatory.")
	@Size(max=30, message="Account must not exceed 30 characters.")
	@Column(name = "account")
	private String account;

	@NotBlank(message="Type is mandatory.")
	@Size(max=30, message="Type must not exceed 30 characters.")
	@Column(name = "type")
	private String type;
	
	@Column(name = "buyQuantity")
	private Double buyQuantity;
	
	@Column(name = "sellQuantity")
	private Double sellQuantity;

	@Column(name = "buyPrice")
	private Double buyPrice;

	@Column(name = "sellPrice")
	private Double sellPrice;

	@Column(name = "tradeDate")
	private Timestamp tradeDate;

	@Size(max=125, message="Security must not exceed 125 characters.")
	@Column(name = "security")
	private String security;

	@Size(max=10, message="Status must not exceed 10 characters.")
	@Column(name = "status")
	private String status;

	@Size(max=125, message="Trader must not exceed 125 characters.")
	@Column(name = "trader")
	private String trader;

	@Size(max=125, message="Benchmark must not exceed 125 characters.")
	@Column(name = "benchmark")
	private String benchmark;

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

	public Trade() {
		super();
	}

	public Trade(
			@NotBlank(message = "Account is mandatory.") @Size(max = 30, message = "Account must not exceed 30 characters.") String account,
			@NotBlank(message = "Type is mandatory.") @Size(max = 30, message = "Type must not exceed 30 characters.") String type) {
		super();
		this.account = account;
		this.type = type;
	}

	public Trade(
			@NotBlank(message = "Account is mandatory.") @Size(max = 30, message = "Account must not exceed 30 characters.") String account,
			@NotBlank(message = "Type is mandatory.") @Size(max = 30, message = "Type must not exceed 30 characters.") String type,
			@NotBlank(message = "BuyQuantity is mandatory") Double buyQuantity, Double sellQuantity, Double buyPrice,
			Double sellPrice, Timestamp tradeDate,
			@Size(max = 125, message = "Security must not exceed 125 characters.") String security,
			@Size(max = 10, message = "Status must not exceed 10 characters.") String status,
			@Size(max = 125, message = "Trader must not exceed 125 characters.") String trader,
			@Size(max = 125, message = "Benchmark must not exceed 125 characters.") String benchmark,
			@Size(max = 125, message = "Book must not exceed 125 characters.") String book,
			@Size(max = 125, message = "CreationName must not exceed 125 characters.") String creationName,
			Timestamp creationDate,
			@Size(max = 125, message = "RevisionName must not exceed 125 characters.") String revisionName,
			Timestamp revisionDate,
			@Size(max = 125, message = "DealName must not exceed 125 characters.") String dealName,
			@Size(max = 125, message = "DealType must not exceed 125 characters.") String dealType,
			@Size(max = 125, message = "SourceListId must not exceed 125 characters.") String sourceListId,
			@Size(max = 125, message = "Side must not exceed 125 characters.") String side) {
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

	public Timestamp getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Timestamp tradeDate) {
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
