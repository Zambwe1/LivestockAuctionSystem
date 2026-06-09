package com.eisebtraders.dao;

import com.eisebtraders.Bid;
import com.eisebtraders.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BidDAO {
    
    public boolean placeBid(Bid bid) {
        String sql = "INSERT INTO bids (auction_id, buyer_id, bid_amount) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bid.getAuctionId());
            ps.setInt(2, bid.getBuyerId());
            ps.setBigDecimal(3, bid.getBidAmount());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Bid> getBidsByAuction(int auctionId) {
        List<Bid> bids = new ArrayList<>();
        String sql = "SELECT * FROM bids WHERE auction_id = ? ORDER BY bid_amount DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, auctionId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bids.add(mapResultSetToBid(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bids;
    }
    
    public List<Bid> getBidsByBuyer(int buyerId) {
        List<Bid> bids = new ArrayList<>();
        String sql = "SELECT * FROM bids WHERE buyer_id = ? ORDER BY bid_time DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, buyerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bids.add(mapResultSetToBid(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bids;
    }
    
    public Bid getHighestBid(int auctionId) {
        String sql = "SELECT * FROM bids WHERE auction_id = ? ORDER BY bid_amount DESC LIMIT 1";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, auctionId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToBid(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int getHighestBidderCount(int auctionId) {
        String sql = "SELECT COUNT(DISTINCT buyer_id) as bidder_count FROM bids WHERE auction_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, auctionId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("bidder_count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    private Bid mapResultSetToBid(ResultSet rs) throws SQLException {
        Bid bid = new Bid();
        bid.setBidId(rs.getInt("bid_id"));
        bid.setAuctionId(rs.getInt("auction_id"));
        bid.setBuyerId(rs.getInt("buyer_id"));
        bid.setBidAmount(rs.getBigDecimal("bid_amount"));
        bid.setBidTime(rs.getTimestamp("bid_time").toLocalDateTime());
        return bid;
    }
}