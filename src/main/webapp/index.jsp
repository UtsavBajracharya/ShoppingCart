<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping Cart</title>
<style>
        body { font-family: Arial, sans-serif; background-color: #f9f9f9; margin: 20px; }
        .container { max-width: 800px; margin: auto; padding: 20px; background: white; border-radius: 5px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        table, th, td { border: 1px solid #ddd; padding: 8px; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Shopping Cart</h2>
        <form action="CartServlet" method="post">
            <label>Product Name:</label>
            <input type="text" name="productName" required>
            <label>Quantity:</label>
            <input type="number" name="quantity" min="1" required>
            <input type="submit" name="action" value="add">
            <input type="submit" name="action" value="remove">
        </form>
        <hr>
        <h3>Cart Items:</h3>
        <c:if test="${not empty cart}">
            <table>
                <tr>
                    <th>Product Name</th>
                    <th>Quantity</th>
                </tr>
                <c:forEach var="product" items="${cart}">
                    <tr>
                        <td>${product.name}</td>
                        <td>${product.quantity}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${empty cart}">
            <p>Your cart is empty.</p>
        </c:if>
    </div>
</body>
</html>