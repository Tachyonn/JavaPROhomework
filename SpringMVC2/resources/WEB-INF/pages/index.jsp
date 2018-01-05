<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Prog.kiev.ua</title>
</head>
<body>
<div align="center">
    <form action="/add_photo" enctype="multipart/form-data" method="POST">
        Image: <input type="file" name="photo">
        <input type="submit"/>
    </form>
</div>
<div align="center">
    <form name="MultipleUpload" action="/upload" enctype="multipart/form-data" method="POST">
        Multiple images: <input type="file" name="uploadingFiles" multiple>
        <input type="submit"/>
    </form>
</div>
<div align="center">
    <c:if test="${not empty list}">
        <h1>Your photo</h1>
        <form action="/delete" method="POST">
            <table border="1">
                <th>Select</th>
                <th>Filename</th>
                <th>Link</th>
                <c:forEach var="i" items="${list}">
                    <tr>
                        <td><input type="checkbox" name="toDelete" value="${i}"></td>
                        <td>${i}</td>
                        <td><a href="/photo/${i}">Open</a></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td>
                        <input type="submit" value="Delete"/>
                    </td>
                </tr>
            </table>
        </form>
    </c:if>
</div>
</body>
</html>
