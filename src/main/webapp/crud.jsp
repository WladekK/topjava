<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title>CRUD</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/mealServlet?action=all_meals">
    id<input hidden name="id" value="${id}">${id}<br>
    Meal time <input type="datetime-local" name="date" value="${meal.dateTime}"/><br>
    Meal description <input type="text" name="description" value="<c:out value="${meal.description}"/>"/><br>
    Meal calories <input type="number" name="calories" value="<c:out value="${meal.calories}"/>"/><br>
    <input type="submit" value="submit">
</form>
</body>
</html>
