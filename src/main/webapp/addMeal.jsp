<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<html>
<head>
    <title>Add new meal</title>
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
    <form action="${pageContext.request.contextPath}/meals?action=save_new" method="post">
        <tr style="width: 100px;text-align: center">
            <td><input type="text" id="description" name="description"/></td>
            <td><input type="datetime-local" id="dateTime" name="dateTime"/></td>
            <td><input type="text" id="Calories" name="calories"/></td>
        </tr>
        <tr>
            <td><input type="submit" id="submit" name="submit" value="Add new entry"/></td>
        </tr>
    </form>
</table>
</body>
</html>