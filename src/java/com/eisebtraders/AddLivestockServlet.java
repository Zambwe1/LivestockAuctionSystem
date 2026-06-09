package com.eisebtraders;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.eisebtraders.dao.LivestockDAO;
import java.math.BigDecimal;

@WebServlet("/AddLivestockServlet")
public class AddLivestockServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LivestockDAO livestockDAO = new LivestockDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null || !user.getUserType().equals("ADMIN")) {
            response.sendRedirect("login.jsp?error=Unauthorized");
            return;
        }
        
        try {
            int sellerId = Integer.parseInt(request.getParameter("sellerId"));
            String livestockType = request.getParameter("livestockType");
            String breed = request.getParameter("breed");
            int age = Integer.parseInt(request.getParameter("age"));
            BigDecimal weight = new BigDecimal(request.getParameter("weight"));
            String healthStatus = request.getParameter("healthStatus");
            String description = request.getParameter("description");
            BigDecimal basePrice = new BigDecimal(request.getParameter("basePrice"));
            
            Livestock livestock = new Livestock(sellerId, livestockType, breed, age, weight, healthStatus, basePrice);
            livestock.setDescription(description);
            
            if (livestockDAO.addLivestock(livestock)) {
                response.sendRedirect("admin/livestock.jsp?message=Livestock added successfully");
            } else {
                response.sendRedirect("admin/addLivestock.jsp?error=Failed to add livestock");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("admin/addLivestock.jsp?error=Invalid input");
        }
    }
}