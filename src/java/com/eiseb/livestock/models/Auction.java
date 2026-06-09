package com.eiseb.livestock.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.math.BigDecimal;

public class Auction implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int auctionId;
    private int livestockId;
    private LocalDateTime scheduledDate;
    private BigDecimal startPrice;
    private String status; // SCHEDULED, ACTIVE, CLOSED
    private int winnerId;
    private BigDecimal finalPrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public Auction() {}
    
    public Auction(int livestockId, LocalDateTime scheduledDate, BigDecimal startPrice) {
        this.livestockId = livestockId;
        this.scheduledDate = scheduledDate;
        this.startPrice = startPrice;
        this.status = "SCHEDULED";
    }
    
    // Getters and Setters
    public int getAuctionId() {
        return auctionId;
    }
    
    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }
    
    public int getLivestockId() {
        return livestockId;
    }
    
    public void setLivestockId(int livestockId) {
        this.livestockId = livestockId;
    }
    
    public LocalDateTime getScheduledDate() {
        return scheduledDate;
    }
    
    public void setScheduledDate(LocalDateTime scheduledDate) {
        this.scheduledDate = scheduledDate;
    }
    
    public BigDecimal getStartPrice() {
        return startPrice;
    }
    
    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public int getWinnerId() {
        return winnerId;
    }
    
    public void setWinnerId(int winnerId) {
        this.winnerId = winnerId;
    }
    
    public BigDecimal getFinalPrice() {
        return finalPrice;
    }
    
    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    @Override
    public String toString() {
        return "Auction{" +
                "auctionId=" + auctionId +
                ", livestockId=" + livestockId +
                ", scheduledDate=" + scheduledDate +
                ", startPrice=" + startPrice +
                ", status='" + status + '\'' +
                ", winnerId=" + winnerId +
                ", finalPrice=" + finalPrice +
                '}';
    }
}