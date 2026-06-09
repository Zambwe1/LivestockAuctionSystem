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

@WebServlet("/CloseAuctionServlet")
public class CloseAuctionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AuctionDAO auctionDAO = new AuctionDAO();
    private BidDAO bidDAO = new BidDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null || !user.getUserType().equals("ADMIN")) {
            response.sendRedirect("login.jsp?error=Unauthorized");
            return;
        }
        
        try {
            int auctionId = Integer.parseInt(request.getParameter("auctionId"));
            
            Bid highestBid = bidDAO.getHighestBid(auctionId);
            
            if (highestBid == null) {
                response.sendRedirect("admin/auctions.jsp?error=No bids placed for this auction");
                return;
            }
            
            if (auctionDAO.closeAuction(auctionId, highestBid.getBuyerId(), highestBid.getBidAmount())) {
                response.sendRedirect("admin/auctions.jsp?message=Auction closed successfully. Winner: Buyer " + highestBid.getBuyerId() + " with bid: " + highestBid.getBidAmount());
            } else {
                response.sendRedirect("admin/auctions.jsp?error=Failed to close auction");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("admin/auctions.jsp?error=Invalid input");
        }
    }
}