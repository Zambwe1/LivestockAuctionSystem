package com.eisebtraders;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.math.BigDecimal;

public class Bid implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int bidId;
    private int auctionId;
    private int buyerId;
    private BigDecimal bidAmount;
    private LocalDateTime bidTime;
    
    public Bid() {}
    
    public Bid(int auctionId, int buyerId, BigDecimal bidAmount) {
        this.auctionId = auctionId;
        this.buyerId = buyerId;
        this.bidAmount = bidAmount;
        this.bidTime = LocalDateTime.now();
    }
    
    public int getBidId() {
        return bidId;
    }
    
    public void setBidId(int bidId) {
        this.bidId = bidId;
    }
    
    public int getAuctionId() {
        return auctionId;
    }
    
    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }
    
    public int getBuyerId() {
        return buyerId;
    }
    
    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }
    
    public BigDecimal getBidAmount() {
        return bidAmount;
    }
    
    public void setBidAmount(BigDecimal bidAmount) {
        this.bidAmount = bidAmount;
    }
    
    public LocalDateTime getBidTime() {
        return bidTime;
    }
    
    public void setBidTime(LocalDateTime bidTime) {
        this.bidTime = bidTime;
    }
    
    @Override
    public String toString() {
        return "Bid{" +
                "bidId=" + bidId +
                ", auctionId=" + auctionId +
                ", buyerId=" + buyerId +
                ", bidAmount=" + bidAmount +
                ", bidTime=" + bidTime +
                '}';
    }
}