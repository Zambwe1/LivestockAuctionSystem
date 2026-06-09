package com.eiseb.livestock.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.math.BigDecimal;

public class Livestock implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int livestockId;
    private int sellerId;
    private String livestockType; // Cattle, Goats, Sheep, etc.
    private String breed;
    private int age;
    private BigDecimal weight;
    private String healthStatus;
    private String description;
    private BigDecimal basePrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Constructors
    public Livestock() {}
    
    public Livestock(int sellerId, String livestockType, String breed, int age, 
                    BigDecimal weight, String healthStatus, BigDecimal basePrice) {
        this.sellerId = sellerId;
        this.livestockType = livestockType;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
        this.healthStatus = healthStatus;
        this.basePrice = basePrice;
    }
    
    // Getters and Setters
    public int getLivestockId() {
        return livestockId;
    }
    
    public void setLivestockId(int livestockId) {
        this.livestockId = livestockId;
    }
    
    public int getSellerId() {
        return sellerId;
    }
    
    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }
    
    public String getLivestockType() {
        return livestockType;
    }
    
    public void setLivestockType(String livestockType) {
        this.livestockType = livestockType;
    }
    
    public String getBreed() {
        return breed;
    }
    
    public void setBreed(String breed) {
        this.breed = breed;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public BigDecimal getWeight() {
        return weight;
    }
    
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
    
    public String getHealthStatus() {
        return healthStatus;
    }
    
    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public BigDecimal getBasePrice() {
        return basePrice;
    }
    
    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
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
        return "Livestock{" +
                "livestockId=" + livestockId +
                ", sellerId=" + sellerId +
                ", livestockType='" + livestockType + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", healthStatus='" + healthStatus + '\'' +
                ", basePrice=" + basePrice +
                '}';
    }
}