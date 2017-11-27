<%--
  Created by IntelliJ IDEA.
  User: m.shevchenko
  Date: 27.11.2017
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Results</title>
</head>
<body>
<h1>Results page<br></h1>
<h2>Questions stats:<br></h2>
<table border="1">
    <tr>
        <td><b>Question</b></td>
        <td><b>Votes</b></td>
    </tr>
    <%-- Iterate through Map --%>
    <c:forEach var="entry" items="${resultsList}">
        <tr>
            <td><c:out value="${entry.key}"/></td>
            <td><c:out value="${entry.value}"/></td>
        </tr>
    </c:forEach>
</table>
&nbsp;
<h2>Voters stats:<br></h2>
<table border="1">
    <tr>
        <td><b>Voter name</b></td>
        <td><b>Voter age</b></td>
    </tr>
    <!-- Iterate through List -->
    <c:forEach items="${votersList}" var="o">
        <tr>
            <td><c:out value="${o.getName()}"/></td>
            <td><c:out value="${o.getAge()}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
