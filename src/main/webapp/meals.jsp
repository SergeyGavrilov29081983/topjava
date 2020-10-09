<%--
  Created by IntelliJ IDEA.
  User: sergey
  Date: 09.10.2020
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="ru.javawebinar.topjava.util.TimeUtil" %>

<html lang="ru">
<head>
    <title>Meals</title>
    <style>
.red {
    color: red;
}
.green{
 color: green;
}

    </style>
</head>

<body>

<table>
    <thead>
    <tr>
        <th>Date Time</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${mealsList}" var="meal">

    <td class="${meal.excess ? 'green' : 'red'}">${TimeUtil.timeToString(meal.dateTime)}</td>
    <td class="${meal.excess ? 'green' : 'red'}">${meal.description}</td>
    <td class="${meal.excess ? 'green' : 'red'}">${meal.calories}</td>
    </tbody>
    </c:forEach>
</table>
</body>
</html>
