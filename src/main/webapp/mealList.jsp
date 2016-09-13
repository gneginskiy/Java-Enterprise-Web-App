<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Meal list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2 style="padding-left: 128px">Meal list</h2>
<table>
    <tr>
        <th width="100px">Description</th>
        <th width="100px">Date</th>
        <th width="100px">Calories</th>
    </tr>
    <c:forEach items="${meals}" var="current">
        <tr style= "color: ${current.exceed?"red":"green"};width: 100px;text-align: center">
           <td>${current.description}</td>
           <td>${current.dateTime.toString().replace("T"," ")}</td>
           <td>${current.calories}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>