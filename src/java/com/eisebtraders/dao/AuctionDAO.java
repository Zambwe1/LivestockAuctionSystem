package com.eisebtraders.dao;

import com.eisebtraders.Auction;
import com.eisebtraders.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuctionDAO {
    
    public boolean addAuction(Auction auction) {
        String sql = "INSERT INTO auctions (livestock_id, scheduled_date, start_price, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, auction.getLivestockId());
            ps.setTimestamp(2, Timestamp.valueOf(auction.getScheduledDate()));
            ps.setBigDecimal(3, auction.getStartPrice());
            ps.setString(4, auction.getStatus());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Auction getAuctionById(int auctionId) {
        String sql = "SELECT * FROM auctions WHERE auction_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, auctionId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToAuction(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Auction> getAllAuctions() {
        List<Auction> auctions = new ArrayList<>();
        String sql = "SELECT * FROM auctions";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                auctions.add(mapResultSetToAuction(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return auctions;
    }
    
    public List<Auction> getAuctionsByStatus(String status) {
        List<Auction> auctions = new ArrayList<>();
        String sql = "SELECT * FROM auctions WHERE status = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                auctions.add(mapResultSetToAuction(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return auctions;
    }
    
    public boolean updateAuction(Auction auction) {
        String sql = "UPDATE auctions SET scheduled_date = ?, start_price = ?, status = ?, winner_id = ?, final_price = ? WHERE auction_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTimestamp(1, Timestamp.valueOf(auction.getScheduledDate()));
            ps.setBigDecimal(2, auction.getStartPrice());
            ps.setString(3, auction.getStatus());
            ps.setInt(4, auction.getWinnerId());
            ps.setBigDecimal(5, auction.getFinalPrice());
            ps.setInt(6, auction.getAuctionId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean closeAuction(int auctionId, int winnerId, java.math.BigDecimal finalPrice) {
        String sql = "UPDATE auctions SET status = 'CLOSED', winner_id = ?, final_price = ? WHERE auction_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, winnerId);
            ps.setBigDecimal(2, finalPrice);
            ps.setInt(3, auctionId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private Auction mapResultSetToAuction(ResultSet rs) throws SQLException {
        Auction auction = new Auction();
        auction.setAuctionId(rs.getInt("auction_id"));
        auction.setLivestockId(rs.getInt("livestock_id"));
        auction.setScheduledDate(rs.getTimestamp("scheduled_date").toLocalDateTime());
        auction.setStartPrice(rs.getBigDecimal("start_price"));
        auction.setStatus(rs.getString("status"));
        auction.setWinnerId(rs.getInt("winner_id"));
        auction.setFinalPrice(rs.getBigDecimal("final_price"));
        return auction;
    }
}