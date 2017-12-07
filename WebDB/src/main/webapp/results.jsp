<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Results</title>
</head>
<body>
<h1>Results page<br></h1>
<form name="clTable" action="/del" method="post">
    <table border="1">
        <tr>
            <td><b>ID</b></td>
            <td><b>Name</b></td>
            <td><b>Age</b></td>
            <td><b>Delete</b></td>
        </tr>
        <c:forEach items="${clientsList}" var="o">
            <tr>
                <td><c:out value="${o.getId()}"/></td>
                <td><c:out value="${o.getName()}"/></td>
                <td><c:out value="${o.getAge()}"/></td>
                <td><input type="checkbox" name="toDelete" value=id="${o.getId()}"></td>
            </tr>
        </c:forEach>
    </table>
    &nbsp;
    <input type="submit" value="Delete">
</form>
<p><a href="/">Go back</a></p>

</body>
</html>
