-- Livestock Auction Management System Database Schema
-- MySQL Database Script

-- Create Database
CREATE DATABASE IF NOT EXISTS livestock_auction_system;
USE livestock_auction_system;

-- Users Table (for authentication)
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    user_type ENUM('ADMIN', 'SELLER', 'BUYER') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE
);

-- Sellers Table
CREATE TABLE sellers (
    seller_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT UNIQUE NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone VARCHAR(20),
    address VARCHAR(255),
    city VARCHAR(50),
    province VARCHAR(50),
    postal_code VARCHAR(10),
    farm_name VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Buyers Table
CREATE TABLE buyers (
    buyer_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT UNIQUE NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone VARCHAR(20),
    address VARCHAR(255),
    city VARCHAR(50),
    province VARCHAR(50),
    postal_code VARCHAR(10),
    business_name VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Livestock Table
CREATE TABLE livestock (
    livestock_id INT PRIMARY KEY AUTO_INCREMENT,
    seller_id INT NOT NULL,
    livestock_type VARCHAR(50) NOT NULL,
    breed VARCHAR(50),
    age INT,
    weight DECIMAL(8, 2),
    health_status VARCHAR(50),
    description TEXT,
    base_price DECIMAL(10, 2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (seller_id) REFERENCES sellers(seller_id) ON DELETE CASCADE
);

-- Auctions Table
CREATE TABLE auctions (
    auction_id INT PRIMARY KEY AUTO_INCREMENT,
    livestock_id INT NOT NULL,
    scheduled_date DATETIME NOT NULL,
    start_price DECIMAL(10, 2),
    status ENUM('SCHEDULED', 'ACTIVE', 'CLOSED') DEFAULT 'SCHEDULED',
    winner_id INT,
    final_price DECIMAL(10, 2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (livestock_id) REFERENCES livestock(livestock_id) ON DELETE CASCADE,
    FOREIGN KEY (winner_id) REFERENCES buyers(buyer_id)
);

-- Bids Table
CREATE TABLE bids (
    bid_id INT PRIMARY KEY AUTO_INCREMENT,
    auction_id INT NOT NULL,
    buyer_id INT NOT NULL,
    bid_amount DECIMAL(10, 2) NOT NULL,
    bid_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (auction_id) REFERENCES auctions(auction_id) ON DELETE CASCADE,
    FOREIGN KEY (buyer_id) REFERENCES buyers(buyer_id) ON DELETE CASCADE
);

-- Auction Results Table
CREATE TABLE auction_results (
    result_id INT PRIMARY KEY AUTO_INCREMENT,
    auction_id INT NOT NULL UNIQUE,
    winner_id INT NOT NULL,
    final_price DECIMAL(10, 2),
    completion_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (auction_id) REFERENCES auctions(auction_id) ON DELETE CASCADE,
    FOREIGN KEY (winner_id) REFERENCES buyers(buyer_id)
);

-- Reports Table
CREATE TABLE reports (
    report_id INT PRIMARY KEY AUTO_INCREMENT,
    report_type VARCHAR(50) NOT NULL,
    generated_by INT NOT NULL,
    report_data LONGTEXT,
    generated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (generated_by) REFERENCES users(user_id)
);

-- Create Indexes for Performance
CREATE INDEX idx_user_username ON users(username);
CREATE INDEX idx_seller_user_id ON sellers(user_id);
CREATE INDEX idx_buyer_user_id ON buyers(user_id);
CREATE INDEX idx_livestock_seller_id ON livestock(seller_id);
CREATE INDEX idx_auction_livestock_id ON auctions(livestock_id);
CREATE INDEX idx_auction_status ON auctions(status);
CREATE INDEX idx_bids_auction_id ON bids(auction_id);
CREATE INDEX idx_bids_buyer_id ON bids(buyer_id);
CREATE INDEX idx_results_auction_id ON auction_results(auction_id);

-- Insert Sample Admin User
INSERT INTO users (username, password, email, user_type) 
VALUES ('admin', SHA2('admin123', 256), 'admin@eisebtraders.com', 'ADMIN');