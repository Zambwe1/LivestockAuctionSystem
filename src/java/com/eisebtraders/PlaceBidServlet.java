package com.eisebtraders;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.eisebtraders.dao.AuctionDAO;
import com.eisebtraders.dao.BidDAO;
import java.math.BigDecimal;

@WebServlet("/PlaceBidServlet")
public class PlaceBidServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BidDAO bidDAO = new BidDAO();
    private AuctionDAO auctionDAO = new AuctionDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null || !user.getUserType().equals("BUYER")) {
            response.sendRedirect("login.jsp?error=Only buyers can place bids");
            return;
        }
        
        try {
            int auctionId = Integer.parseInt(request.getParameter("auctionId"));
            int buyerId = Integer.parseInt(request.getParameter("buyerId"));
            BigDecimal bidAmount = new BigDecimal(request.getParameter("bidAmount"));
            
            Auction auction = auctionDAO.getAuctionById(auctionId);
            
            if (auction == null || !auction.getStatus().equals("ACTIVE")) {
                response.sendRedirect("buyer/auctions.jsp?error=Auction is not active");
                return;
            }
            
            Bid highestBid = bidDAO.getHighestBid(auctionId);
            if (highestBid != null && bidAmount.compareTo(highestBid.getBidAmount()) <= 0) {
                response.sendRedirect("buyer/auction.jsp?id=" + auctionId + "&error=Bid must be higher than current highest bid");
                return;
            }
            
            Bid bid = new Bid(auctionId, buyerId, bidAmount);
            
            if (bidDAO.placeBid(bid)) {
                response.sendRedirect("buyer/auction.jsp?id=" + auctionId + "&message=Bid placed successfully");
            } else {
                response.sendRedirect("buyer/auction.jsp?id=" + auctionId + "&error=Failed to place bid");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("buyer/auctions.jsp?error=Invalid input");
        }
    }
}