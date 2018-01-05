<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Prog.kiev.ua</title>
</head>
<body>
<div align="center">
    <h1>Your photo</h1>
    <table border="1">
        <c:forEach var="i" items="${list}">
            <tr>
                <td><a href="/photo/${i}">${i}</a></td>
                <td><input type="submit" value="Delete Photo" onclick="window.location='/delete/${i}';" /></td>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>
