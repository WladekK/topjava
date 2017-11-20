<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>CRUD</title>
</head>
<body>
<form method="post" action='MealServlet' name="addMeal">
    Meal time <input type="text"><br>
    Meal description <input type="text" value="description"><br>
    Meal calories <input type="number" value="calories"><br>
    <input type="submit" value="submit">
</form>
</body>
</html>
