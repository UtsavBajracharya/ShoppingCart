package com.cart.servlet;

import java.io.IOException;
import java.util.ArrayList;
import com.cart.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Product> cart = (ArrayList<Product>) session.getAttribute("cart");
        
        if (cart == null) {
            cart = new ArrayList<>();
        }

        String action = request.getParameter("action");
        String productName = request.getParameter("productName");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        if ("add".equalsIgnoreCase(action)) {
            boolean found = false;
            for (Product p : cart) {
                if (p.getName().equalsIgnoreCase(productName)) {
                    p.setQuantity(p.getQuantity() + quantity);
                    found = true;
                    break;
                }
            }
            if (!found) {
                cart.add(new Product(productName, quantity));
            }
        } else if ("remove".equalsIgnoreCase(action)) {
            cart.removeIf(p -> p.getName().equalsIgnoreCase(productName));
        }

        session.setAttribute("cart", cart);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

}
