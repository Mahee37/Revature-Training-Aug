<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View My Product Reviews</title>
</head>
<body>
    <h1>View My Product Reviews</h1>

    <!-- Form to view reviews for the logged-in retailer's products -->
    <form action="${pageContext.request.contextPath}/product/ProductReviews" method="get">
        <input type="hidden" name="sellerId" value="${sessionScope.sellerId}" />
        <input type="submit" value="View Reviews" />
    </form>

    <!-- Link to go back to the retailer dashboard or other relevant pages -->
    <a href="${pageContext.request.contextPath}/dashboard">Back to Dashboard</a>
</body>
</html>
