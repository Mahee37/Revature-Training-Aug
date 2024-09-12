<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inventory</title>
</head>
<body>
    <h1>Product Inventory</h1>
    <ul>
        <c:forEach var="product" items="${product_list}">
            <li>
                <h2>${product.name}</h2>
                <p>Price: ${product.price}</p>
                <p>Description: ${product.description}</p>
                <img src="${product.imageUrl}" alt="${product.name}" width="100" />

                <!-- Form for deleting the product -->
                <form action="${pageContext.request.contextPath}/product/deleteProduct" method="post">
                    <input type="hidden" name="id" value="${product.id}" />
                    <input type="submit" value="Remove Product">
                </form>
            </li>
        </c:forEach>
    </ul>
</body>
</html>
