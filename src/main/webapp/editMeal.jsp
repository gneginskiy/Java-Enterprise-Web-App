<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<html>
<head>
    <title>Edit existing meal</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2 style="padding-left: 128px">Add new meal</h2>

<table>
    <tr>
        <th width="100px">Description</th>
        <th width="100px">Date & Time</th>
        <th width="100px">Calories</th>
    </tr>
    <form action="${pageContext.request.contextPath}/meals?action=save_existing&id=${current.getId()}&exceeds=${current.isExceed()}" method="post">
        <tr style="width: 100px;text-align: center">
            <td><input type="text" id="description" name="description" value="${current.getDescription()}"/></td>
            <td><input type="datetime-local" id="dateTime" name="dateTime" value="${current.getDateTime()}"/></td>
            <td><input type="text" id="Calories" name="calories" value="${current.getCalories()}"/></td>
        </tr>
        <tr>
            <td><input type="submit" id="submit" name="submit" value="Save changes"/></td>
        </tr>
    </form>
</table>
</body>
</html>