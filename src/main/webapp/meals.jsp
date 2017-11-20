<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<table style="width: 100%">
    <tr>
        <th>Meal description</th>
        <th>Meal date</th>
        <th>Calories</th>
        <th>Exceeded</th>
    </tr>
    <c:forEach var="meal" items="${meals}">
        <tr align="center">
            <td>${meal.getDescription()}</td>
            <td>${meal.getDateTime().toString().replace("T", " ")}</td>
            <td>${meal.getCalories()}</td>
            <c:choose>
                <c:when test="${meal.isExceed() == true}">
                    <td><font color="red">${meal.isExceed()}</font></td>
                </c:when>
                <c:otherwise>
                    <td><font color="green">${meal.isExceed()}</font></td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
</table>
</body>
</html>
