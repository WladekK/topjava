<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals List</title>
</head>
<body>
<table style="width: 100%">
    <tr>
        <th>Meal ID</th>
        <th>Meal description</th>
        <th>Meal date</th>
        <th>Calories</th>
        <th colspan=2>Action</th>
    </tr>
    <c:forEach var="meal" items="${meals}">
        <tr align="center">
            <td>${meal.key}</td>
            <td>${meal.value.getDescription()}</td>
            <td>${meal.value.getDateTime().toString().replace("T", " ")}</td>
            <td>${meal.value.getCalories()}</td>
            <td><a href="${pageContext.request.contextPath}/mealServlet?action=edit&mealId=${meal.key}">Update</a></td>
            <td><a href="${pageContext.request.contextPath}/mealServlet?action=delete&mealId=${meal.key}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<p><a href="${pageContext.request.contextPath}/mealServlet?action=create">add a new meal</a></p>
</body>
</html>
