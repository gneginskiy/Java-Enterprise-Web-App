<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

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
        <th width="100px">Date & Time</th>
        <th width="100px">Calories</th>
    </tr>
    <c:forEach items="${meals}" var="current">
        <tr style= "color: ${current.exceed?"red":"green"};width: 100px;text-align: center">
           <td>${current.description}</td>
           <td>${current.dateTime.toString().replace("T"," ")}</td>
           <td>${current.calories}</td>
           <td><input type="button" onclick="location.href='meals?action=edit&id=${current.getId()}';  " value="Edit"/></td>
           <td><input type="button" onclick="location.href='meals?action=remove&id=${current.getId()}';" value="Remove"/></td>
        </tr>
    </c:forEach>
</table>
<input type="button" onclick="location.href='meals?action=add';" value="Add new entry">
<input type="button" onclick="location.href='meals?action=restore_initial';" value="Restore initial data"/>

</body>
</html>